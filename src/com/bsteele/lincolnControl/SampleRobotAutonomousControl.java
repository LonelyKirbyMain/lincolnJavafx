/*
 * Copyright 2016 Robert Steele, bsteele.com
 * All rights reserved.
 */
package com.bsteele.lincolnControl;

/**
 * @author bob
 */
public class SampleRobotAutonomousControl
        implements RobotAutonomousControl {
    /**
     * Control a robot in autonomous mode
     *
     * @param robot the robot to use in this control
     */
    @Override
    public void run(RobotInstruction robot) {
        //  move to location
        try {
            //  use some motion instructions to get motion
            robot.moveTank(RobotInstruction.MotorRequest.onForRotations, 0.7, 0.7, 4, RobotInstruction.MotorRequestEnd.coast);
            robot.moveTank(RobotInstruction.MotorRequest.onForRotations, -0.4, -0.7, 2.3, RobotInstruction.MotorRequestEnd.coast);
            robot.moveTank(RobotInstruction.MotorRequest.onForRotations, 0.7, 0.7, 4, RobotInstruction.MotorRequestEnd.brake);

            //  sample the color
            robot.calibrateReflectedLightIntensity(RobotInstruction.Intensity.reset);
            RgbColor color = robot.measureColor();
            if (color.getRed() > color.getBlue()) {
                //	assume it's the red ball
                System.out.println("it's red");
            } else {
                //	assume it's the blue ball
                System.out.println("it's blue");
            }
        } catch (TerminationException e) {
            //  terminate as requested
        }
    }
}
