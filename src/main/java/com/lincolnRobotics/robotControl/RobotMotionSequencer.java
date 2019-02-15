package com.lincolnRobotics.robotControl;

/**
 * Interface used to abstract robot motion sequencing for the main loop.
 * <p>
 * Implementations of this interface are intended to control the robot
 * in autonomous mode by sequencing commands to the robot each pass
 * through the main loop or "tick" of the loop clock when appropriate.
 * Typically a new, subsequent robot command will be given after
 * the completion of the prior command.
 * </p>
 */
public interface RobotMotionSequencer {
    /**
     * Initialize the sequencer for processing in the main loop.
     */
    public void initialize();

    /**
     * Run the sequencer through a new iteration of the loop.
     */
    public void tick();

    /**
     * Stop the sequencer immediately and stop motion in the robot under control.
     */
    public void stop();

    /**
     * Restart the sequencer from the beginning.  In simulations, the robot position and velocity should be reset.
     */
    public void restart();

    /**
     * Supply the robot motion under the sequencer's control.
     * @return the robot motion under the sequencer's control
     */
    public RobotMotion getRobotMotion();
}
