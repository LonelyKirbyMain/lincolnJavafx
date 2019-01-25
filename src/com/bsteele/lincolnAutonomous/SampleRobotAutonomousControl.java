/*
 * Copyright 2016 Robert Steele, bsteele.com
 * All rights reserved.
 */
package com.bsteele.lincolnAutonomous;


import com.lincolnRobotics.robotControl.RobotAutonomousControl;
import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;

/**
 * @author bob
 */
public class SampleRobotAutonomousControl
        implements RobotAutonomousControl
{
    /**
     * Control a robot in autonomous mode
     *
     * @param robot the robot to use in this control
     */
    @Override
    public void run(RobotMotion robot) {
        try {
            //  move to location
            //  use some motion instructions to get motion
            robot.moveTank(RobotMotion.MotionControl.onForRotations, 70, 70, 8, false);
            robot.moveTank(RobotMotion.MotionControl.onForRotations, -40, -70, 3, false);
            robot.moveTank(RobotMotion.MotionControl.onForRotations, 70, 70, 4, false);

            //  sample the color
//            robot.calibrateReflectedLightIntensity(RobotMotion.Intensity.reset);
//            RgbColor color = robot.measureColor();
//            if (color.getRed() > color.getBlue()) {
//                //	assume it's the red ball
//                System.out.println("it's red");
//            } else {
//                //	assume it's the blue ball
//                System.out.println("it's blue");
//            }

            //  we're done
            System.out.println("we're done");

        }
        catch (TerminationException e) {
            //  terminate as requested
            robot.stop();
        }
    }
}
