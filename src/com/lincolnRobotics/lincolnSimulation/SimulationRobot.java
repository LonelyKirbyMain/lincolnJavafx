package com.lincolnRobotics.lincolnSimulation;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;

public class SimulationRobot implements RobotMotion {
    SimulationRobot(RobotModel robotModel) {
        this.robotModel = robotModel;
    }


    @Override
    public void moveSteering(MotionControl steeringControl, int steering, int power, int rotations, boolean brakeAtEnd) throws TerminationException {

    }

    @Override
    public void moveTank(MotionControl tankControl, int powerLeft, int powerRight, int rotations, boolean brakeAtEnd) throws TerminationException {
        System.out.println(String.format("sim moveTank( %s, %d, %d, %d, %b )",
                tankControl.toString(), powerLeft, powerRight, rotations, brakeAtEnd));
        this.powerLeft = powerLeft / 100.0;
    }

    @Override
    public float getSensor(int sensorId) {
        return 0;
    }

    @Override
    public void stop() {

    }

    @Override
    public void tick() {
        robotModel.setLocation(robotModel.getX() + powerLeft, robotModel.getY());
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public RobotType getRobotType() {
        return RobotType.simulation;
    }


    private RobotModel robotModel;
    private double powerLeft;
}
