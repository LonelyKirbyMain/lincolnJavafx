package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;

import java.util.logging.Logger;

/**
 * A sample robot motion sequencer.
 * Note that most of it's functionality comes from it's super class.
 */
public class SampleRobotMotionSequencer extends AbstractRobotMotionSequencer {

    public SampleRobotMotionSequencer() {
        //  initialize the logger to our own so it can be controlled by the logging.properties file
        logger = Logger.getLogger(SampleRobotMotionSequencer.class.getName());
    }

    /**
     * This method will be called every cycle of the main loop, i.e. tick of the clock.
     * Overload this method to control the robot as per your autonomous mode.
     */
    @Override
    public void tick() {
        processSubstate();

        try {
            switch (state) {
                default:
                    state = 0;  //  error!
                case 0:
                    logger.info("sequencer start");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            0, 35, 4, false);
                    state++;
                    break;
                case 1:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("right turn isDone");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            35, 35, 4, false);
                    state++;
                    break;
                case 2:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("straight isDone");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                            -35, -35, 4, false);
                    state++;
                    break;
                case 3:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("reverse isDone");
                    state++;
                    break;
                case 4:
                    logger.info("sequencer done");
                    state++;
                    break;
                case 5:
                    //  idle
                    break;
            }
        } catch (TerminationException ex) {
            ex.printStackTrace();
        }
    }
}
