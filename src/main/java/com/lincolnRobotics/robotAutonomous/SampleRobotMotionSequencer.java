package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;
import com.lincolnRobotics.robotControl.TerminationException;
import com.sun.istack.internal.NotNull;

import java.util.logging.Logger;

public class SampleRobotMotionSequencer implements RobotMotionSequencer {

    public SampleRobotMotionSequencer(@NotNull RobotMotion robotMotion) {
        this.robotMotion = robotMotion;
    }

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
            robotMotion.tick();

            switch (state) {
                default:
                    state = 0;  //  error!
                case 0:
                    logger.info("sequencer start");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, 0, 35, 4, false);
                    state++;
                    break;
                case 1:
                    if (robotMotion.isDone())
                        logger.info("isDone");
                    logger.info("sequencer done");
                    state++;
                    break;
                case 2:
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
    public RobotMotion getRobotMotion() {
        return robotMotion;
    }

    protected int state = 0;
    protected final RobotMotion robotMotion;

    //  note: if you copy this logger initialization, manually update the class name to your class
    private static final Logger logger = Logger.getLogger(SampleRobotMotionSequencer.class.getName());
}
