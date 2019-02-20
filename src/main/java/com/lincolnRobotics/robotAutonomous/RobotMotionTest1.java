package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;
import com.lincolnRobotics.robotControl.TerminationException;

import java.util.logging.Logger;

/**
 * A sample robot motion sequencer.
 */
public class RobotMotionTest1 implements RobotMotionSequencer {

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

        //  count the ticks in a given state
        if (state != lastState) {
            lastState = state;
            substate = 0;
        } else
            substate++;

        //  execute the state
        try {
            switch (state) {
                default:
                    state = 0;  //  error!
                case 0:
                    logger.info("sequencer straight");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, 35, 35, 4, false);
                    state++;
                    break;
                case 1:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("straight isDone");
                    robotMotion.moveTank(RobotMotion.MotionControl.onForRotations, -35, -35, 4, false);
                    state++;
                    break;
                case 2:
                    if (!robotMotion.isDone())
                        break;
                    logger.info("reverse isDone");
                    state++;
                    break;
                case 3:
                    //  idle a little
                    if (substate < 10)
                        break;
                    state = 0;      //  restart
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
        state = 0;                  //  restart the state machine
        robotMotion.restart();      //  in case there is an action to be performed.
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
    protected int substate = 0;
    private int lastState = state;

    protected RobotMotion robotMotion;

    //  note: if you copy this logger initialization, manually update the class name to your class
    private static final Logger logger = Logger.getLogger(RobotMotionTest1.class.getName());
}
