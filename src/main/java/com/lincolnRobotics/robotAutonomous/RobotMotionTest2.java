package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * A sample robot motion test for robot rotation.
 * Note that most of it's functionality comes from it's super class.
 */
public class RobotMotionTest2 extends AbstractRobotMotionSequencer {

    public RobotMotionTest2() {
        //  initialize the logger to our own so it can be controlled by the logging.properties file
        logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
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
                    logger.info("sequencer rotate right");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            50, 0, 4, false);
                    state++;
                    break;
                case 1:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("sequencer rotate left");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            0, 50, 4, false);
                    state = 0;      //  restart
                    break;
            }
        } catch (TerminationException ex) {
            ex.printStackTrace();
        }
    }
}
