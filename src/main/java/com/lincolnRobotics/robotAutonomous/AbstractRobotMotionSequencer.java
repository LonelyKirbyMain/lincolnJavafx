package com.lincolnRobotics.robotAutonomous;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;

import java.util.logging.Logger;

/**
 * An abstract robot motion sequencer.
 * <p>This class is designed to do most of the usual method processing
 * for a robot motion sequencer implementation.
 * Only the tick method need be implemented by any given subclass.</p>
 * <p>An abstract super class is similar to an interface but it can
 * also supply a number of data members and methods to help make implementing
 * the subclasses easier.</p>
 */
abstract public class AbstractRobotMotionSequencer implements RobotMotionSequencer {

    @Override
    public void initialize() {
        state = 0;
        logger.info("initialize()");
        robotMotion.restart();
    }

    /**
     * This method will be called every cycle of the main loop, i.e. tick of the clock.
     * Overload this method to control the robot as per your autonomous mode.
     */
    @Override
    abstract public void tick();

    @Override
    public void stop() {
        robotMotion.stop();
        logger.info("sequencer stop");
    }

    @Override
    public void restart() {
        robotMotion.restart();
        state = 0;
        logger.info("sequencer restart");
    }

    /**
     * Process the substate by reseting it on a state change and keeping count of ticks when the state hasn't changed.
     */
    protected void processSubstate() {
        if (lastState != state) {
            subState = 0;
            logger.fine(getClass().getSimpleName() + ": state: " + state);
            lastState = state;
        } else
            subState++;
    }

    /**
     * Return the robot motion control
     *
     * @return the robot motion control
     */
    @Override
    public RobotMotion getRobotMotion() {
        return robotMotion;
    }

    @Override
    public void setRobotMotion(RobotMotion robotMotion) {
        this.robotMotion = robotMotion;
    }

    protected int state = 0;
    protected int subState = 0;
    private int lastState = -1;
    protected RobotMotion robotMotion;

    //  note: if you copy this logger initialization, manually update the class name to your class
    protected Logger logger = Logger.getLogger(AbstractRobotMotionSequencer.class.getName());
}
