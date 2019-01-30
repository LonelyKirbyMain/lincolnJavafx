package com.lincolnRobotics.robotControl;

/**
 * Interface used to implement robotMotionSequencer controls in Lincoln High School Java.
 *
 * @author bob
 */

public interface RobotMotion {

    /**
     * Describe the type of robotMotionSequencer provided
     */
    public enum RobotType {
        /**
         * a Lejos robotMotionSequencer based on the Legos EV3 hardware
         */
        lejos,
        /**
         * Lincoln robotics simulation
         */
        simulation,
        /**
         * an TFC robotMotionSequencer
         */
        ftc;
    }

    /**
     * Describe the type of motion to be requested
     */
    public enum MotionControl {
        /**
         * no motion from the command
         */
        off,
        /**
         * motion should continue indefinitely
         */
        on,
        /**
         * interpret the arguments as units of seconds
         */
        onForSeconds,
        /**
         * interpret the arguments as units of degrees, 360 being a full rotation
         */
        onForDegrees,
        /**
         * interpret the arguments as units of wheel rotations
         */
        onForRotations;
    }

    /**
     * Emulate the lego mindstorm move steering command.
     * <p>
     * Note that the command will return immediately, after having initiated the
     * command. Use isDone() to determine if the request has been completed.
     * </p>
     *
     * @param steeringControl an enumeration value indicating the type of control
     *                        requested
     * @param steering        the "steering" input: -100 full left, 0 straight, 100
     *                        full right
     * @param powerPercent    the "power" (speed) of the motor(s) in units of percent, 0 none, 100 full, -100 full reverse
     * @param rotations       required rotation of the wheels
     * @param brakeAtEnd      true if braking at the end
     * @throws TerminationException thrown if the control identifies a failure
     */
    public void moveSteering(MotionControl steeringControl, int steering, int powerPercent, int rotations, boolean brakeAtEnd)
            throws TerminationException;

    /**
     * Emulate the lego mindstorm move tank command.
     * <p>
     * Note that the command will return immediately, after having initiated the
     * command. Use isDone() to determine if the request has been completed.
     * </p>
     *
     * @param tankControl       type of motion to be requested
     * @param powerLeftPercent  the "power" (speed) of the left motor: -100 full reverse,
     *                          0 none, 100 full forward
     * @param powerRightPercent the "power" (speed) of the right motor: -100 full reverse,
     *                          0 none, 100 full forward
     * @param rotations         required rotation of the wheels
     * @param brakeAtEnd        true if braking at the end
     * @throws TerminationException thrown if the control identifies a failure
     */
    public void moveTank(MotionControl tankControl, int powerLeftPercent, int powerRightPercent, int rotations, boolean brakeAtEnd)
            throws TerminationException;

    /**
     * Acquire the sensor value for the given sensor identified.
     *
     * @param sensorId the selected sensor
     * @return the value from the sensor
     */
    public float getSensor(int sensorId);

    /**
     * Immediately stop all motion
     */
    public void stop();

    /**
     * Indicate that the last motion command has completed.
     *
     * @return true if the motion command has completed.
     */
    public boolean isDone();

    /**
     * provide the robotMotionSequencer type provided by the implementation of the interface
     *
     * @return the type of robotMotionSequencer provided
     */
    public RobotType getRobotType();


    /**
     * Simulation time tick to simulate robot behavior.  This will be empty for a real robot.
     */
    public void tick();

}
