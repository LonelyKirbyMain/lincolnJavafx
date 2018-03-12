/*
 * Copyright 2016 Robert Steele, bsteele.com
 * All rights reserved.
 */
package com.bsteele.lincolnControl;

import com.bsteele.lincolnControl.RgbColor;

/**  The robot instruction set for autonomous control.
 *  This is just a tiny sample generated from the Lego software model.
 *
 * @author bob
 */
public interface RobotInstruction {

    enum MotorRequest {
        off,
        on,
        onForSeconds,
        onForDegrees,
        onForRotations
    }

    enum MotorRequestEnd {
        brake,
        coast;
    }

    public enum Intensity {
        minimum,
        maximum,
        reset;
    }
//    public enum PlayEnd
//    {
//	once,
//	repeat;
//    }

    void motor(MotorRequest request, double value, MotorRequestEnd requestEnd)
            throws TerminationException;

    void moveSteering(MotorRequest request, double steeringDegrees, double value,
                      MotorRequestEnd requestEnd)
            throws TerminationException;

    void moveTank(MotorRequest request, double leftPower, double rightPower, double rotations,
                  MotorRequestEnd requestEnd)
            throws TerminationException;

    RgbColor measureColor()
            throws TerminationException;

    double measureReflectedLightIntensity()
            throws TerminationException;

    double measureAmbienLightIntensity()
            throws TerminationException;

    void calibrateReflectedLightIntensity(Intensity intensity)
            throws TerminationException;

    //	and others
}


