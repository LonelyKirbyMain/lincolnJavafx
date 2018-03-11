package com.bsteele.lincolnControl;

/**
 * Allows robot controllers to be used through this interface.
 * This allows the controller to work in multiple environments.
 *
 * CopyRight 2018 bsteele.com
 * User: bob
 * Date: 3/11/18
 */
public interface RobotAutonomousControl {
    void run( RobotInstruction robot );
}
