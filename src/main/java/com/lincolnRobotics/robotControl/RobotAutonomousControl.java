package com.lincolnRobotics.robotControl;

/** Interface to run robot motion in autonomous mode.
 * <p>Used primarily to isolate the controller from the specific robot being controlled.
 * This allows the controller to function properly in simulation, Lejos and FTC robots.</p>
 */
public interface RobotAutonomousControl {
	/**
	 * Run robot motion controls on the robot that has been given in the argument.
	 * @param robotMotion the instance of robot to control
	 */
	void run( RobotMotion robotMotion );
}
