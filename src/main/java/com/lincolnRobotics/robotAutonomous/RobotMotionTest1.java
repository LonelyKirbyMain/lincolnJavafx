package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;

import java.util.logging.Logger;

/**
 * A sample robot motion test for robot forward and reverse.
 * Note that most of it's functionality comes from it's super class.
 */
public class RobotMotionTest1 extends AbstractRobotMotionSequencer {

    public RobotMotionTest1() {
        //  initialize the logger to our own so it can be controlled by the logging.properties file
        logger = Logger.getLogger(RobotMotionTest1.class.getName());
    }

    /**
     * This method will be called every cycle of the main loop, i.e. tick of the clock.
     * Overload this method to control the robot as per your autonomous mode.
     */
    @Override
    public void tick() {

        //  count the ticks in a given state
        processSubstate();

        //  execute the state
        try {
            switch (state) {
                default:
                    state = 0;  //  error!
                case 0:
                    logger.fine("sequencer: forward");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, 35, 35, 4, false);
                    state++;
                    break;
                case 1:
                    if (!robotMotion.isDone())
                        break;
                    logger.fine("sequencer: reverse");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, -35, -35, 4, false);
                    state++;
                    break;
                case 2:
                    if (!robotMotion.isDone())
                        break;
                    state++;
                    break;
                case 3:
                    //  idle a little
                    if (subState < 25)
                        break;
                    state = 0;      //  restart
                    break;
            }
        } catch (TerminationException ex) {
            ex.printStackTrace();
        }
    }
}
