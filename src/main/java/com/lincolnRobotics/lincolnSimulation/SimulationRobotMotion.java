package com.lincolnRobotics.lincolnSimulation;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;

import java.util.logging.Logger;

/**
 * Mapping of the robot motion interface to the Lincoln
 * robotics simulation.
 */
public class SimulationRobotMotion implements RobotMotion {
    SimulationRobotMotion(RobotModel robotModel) {
        this.robotModel = robotModel;
    }


    @Override
    public void moveSteering(MotionControl steeringControl, int steering, int powerPercent, int rotations, boolean brakeAtEnd)
            throws TerminationException {
        logger.fine(String.format("sim moveTank( %s, steering: %d, speedPercent: %d, rotations: %d, brake: %b )",
                steeringControl.toString(), steering, powerPercent, rotations, brakeAtEnd));

        speedPercent = limit100(powerPercent);
        steeringPercent = limit100(steering);
    }

    @Override
    public void moveTank(MotionControl tankControl, int powerLeftPercent, int powerRightPercent, int rotations, boolean brakeAtEnd)
            throws TerminationException {
        logger.fine(String.format("sim moveTank( %s, powerLeft: %d, powerRight: %d, rotations: %d, brake: %b )",
                tankControl.toString(), powerLeftPercent, powerRightPercent, rotations, brakeAtEnd));

        this.powerLeftPercent = limit100(powerLeftPercent);
        this.powerRightPercent = limit100(powerRightPercent);

        speedPercent = (this.powerLeftPercent + this.powerRightPercent) / 2;
        double steeringScale = Math.abs(this.powerLeftPercent) + Math.abs(this.powerRightPercent);
        if (steeringScale > 0) {
            steeringPercent = 100 * (this.powerRightPercent - this.powerLeftPercent) / steeringScale;
        }
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

        switch (motionType) {
            case steering:
                break;
            default:
                applyTankMotionTick();
                break;
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public RobotType getRobotType() {
        return RobotType.simulation;
    }

    void applyTankMotionTick() {
        //  create local copy of the raw positions
        double theta = robotModel.getRotation();
        double x = robotModel.getX();
        double y = robotModel.getY();

        //  update the positions
        double steering = steeringPercent / (100 * fullSteering);

        double left = powerLeftPercent / 100;
        double right = powerRightPercent / 100;
        double deltaTheta = 0;
        if (left == right) {

        } else {
            deltaTheta = Math.atan2(right, left);
        }
        theta += deltaTheta;

        logger.fine(String.format("\tw: %f, h: %f %f", left, right, deltaTheta, theta));

        double speed = speedPercent / (100 * fullSpeed);
        double dx = speed * Math.cos(theta);
        double dy = speed * Math.sin(theta);

        //  update the local positions
        x += dx;
        y += dy;

        //  output the new values
        robotModel.setLocation(x, y);
        robotModel.setRotation(theta);
    }

    /**
     * Limit the input to the range -100 to +100
     *
     * @param n the input integer
     * @return the input limited to the range -100 to +100
     */
    private int limit100(int n) {
        return (n < -100 ? -100 : (n > 100 ? 100 : n));
    }

    /**
     * the robot model controlled by this robot motion
     */
    private RobotModel robotModel;
    /**
     * current speed in plus or minus percent of maximum
     */
    private double speedPercent;
    /**
     * current steering in plus or minus percent of maximum
     */
    private double steeringPercent;
    /**
     * maximum speedPercent allowed in pixels per tick
     */
    private static double fullSpeed = 3;
    /**
     * maximum speedPercent allowed in pixels per tick
     */
    private static double fullSteering = 0.1 * Math.PI;
    /**
     * robot wheel diameter
     */
    private static double wheelDiameter = 3;
    /**
     * Samples per second of the main loop
     */
    private static int samplesPerSecond = 60;

    public MotionType getMotionType() {
        return motionType;
    }

    public void setMotionType(MotionType motionType) {
        this.motionType = motionType;
    }

    private enum MotionType {
        tank,
        steering;
    }

    private MotionType motionType = MotionType.tank;
    private double powerLeftPercent;
    private double powerRightPercent;

    //  note: if you copy this logger initialization, manually update the class name to your class
    private static final Logger logger = Logger.getLogger(SimulationRobotMotion.class.getName());
}
