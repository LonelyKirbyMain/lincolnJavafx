package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;
import com.lincolnRobotics.robotControl.TerminationException;

import java.util.logging.Logger;

public class SampleRobotMotionSequencer implements RobotMotionSequencer {

    public SampleRobotMotionSequencer(RobotMotion robotMotion) {
        this.robotMotion = robotMotion;
    }

    @Override
    public void initialize() {
        state = 0;
    }

    @Override
    public void tick() {
        try {
            robotMotion.tick();

            switch (state) {
                default:
                    state = 0;  //  error!
                case 0:
                    logger.info("sequencer start");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, 70, 70, 4, false);
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
        logger.info("sequencer stop");
    }

    @Override
    public RobotMotion getRobotMotion() {
        return robotMotion;
    }

    private int state = 0;
    private final RobotMotion robotMotion;
    private static final Logger logger = Logger.getLogger(SampleRobotMotionSequencer.class.getName());
}
