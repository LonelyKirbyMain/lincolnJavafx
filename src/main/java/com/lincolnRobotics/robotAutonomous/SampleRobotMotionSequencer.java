package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * A sample robot motion sequencer.
 * Note that most of it's functionality comes from it's super class.
 */
public class SampleRobotMotionSequencer extends AbstractRobotMotionSequencer {

    public SampleRobotMotionSequencer() {
        //  initialize the logger to our own so it can be controlled by the logging.properties file
        logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    }

    /**
     * States names for the states in the finite state machine of the command sequencer.
     */
    private enum State {
        start,
        waitForRightTurn,
        waitForForward,
        waitForReverse,
        wait,
        idle;
    }

    /**
     * This method will be called every cycle of the main loop, i.e. tick of the clock.
     * Overload this method to control the robot as per your autonomous mode.
     */
    @Override
    public void tick() {
        processSubstate();

        //  note: this is pretty dangerous in general but should work for the simulation
        if (!robotMotion.isDone())
            return;

        //  sequence through the state machine
        try {
            switch (currentState) {
                default:
                    currentState = State.start;  //  error!
                case start:
                    logger.info("sequencer start");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            0, 35, 4, false);
                    currentState = State.waitForRightTurn;
                    break;

                case waitForRightTurn:
                    logger.info("right turn isDone");

                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            35, 35, 4, false);
                    currentState = State.waitForForward;
                    break;

                case waitForForward:
                    logger.info("forward isDone");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            -35, -35, 4, false);
                    currentState = State.waitForReverse;
                    break;

                case waitForReverse:
                    logger.info("reverse isDone");
                    currentState = State.wait;
                    break;

                case wait:
                    if (subState < 10)
                        break;
                    logger.info("sequencer done");
                    currentState = State.idle;
                    break;

                case idle:
                    //  idle
                    break;
            }
        } catch (TerminationException ex) {
            ex.printStackTrace();
        }
    }

    private State currentState = State.start;
}
