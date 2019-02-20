package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;
import com.lincolnRobotics.robotControl.TerminationException;

import java.util.logging.Logger;

/**
 * A sample robot motion sequencer.
 */
public class SampleRobotMotionSequencer implements RobotMotionSequencer {

    @Override
    public void initialize() {
        state = 0;
    }

    /**
     * This method will be called every cycle of the main loop, i.e. tick of the clock.
     * Overload this method to control the robot as per your autonomous mode.
     */
    @Override
    public void tick() {
        try {
            switch (state) {
                default:
                    state = 0;  //  error!
                case 0:
                    logger.info("sequencer start");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, 0, 35, 4, false);
                    state++;
                    break;
                case 1:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("right turn isDone");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, 35, 35, 4, false);
                    state++;
                    break;
                case 2:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("straight isDone");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, -35, -35, 4, false);
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

    @Override
    public void stop() {
        robotMotion.stop();
        logger.info("sequencer stop");
    }

    @Override
    public void restart() {
        robotMotion.restart();
        state = 0;
    }

    @Override
    public RobotMotion getRobotMotion() {
        return robotMotion;
    }

    @Override
    public void setRobotMotion(RobotMotion robotMotion) {
        this.robotMotion = robotMotion;
    }

    protected int state = 0;
    protected RobotMotion robotMotion;

    //  note: if you copy this logger initialization, manually update the class name to your class
    private static final Logger logger = Logger.getLogger(SampleRobotMotionSequencer.class.getName());
}
