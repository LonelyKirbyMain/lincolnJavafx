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
        logger.fine(String.format("sim moveTank( %s, steering: speedPercent: %d, rotations: %d, brake: %b )",
                steeringControl.toString(), powerPercent, rotations, brakeAtEnd));
        speedPercent = limit100(powerPercent);
        steeringPercent = limit100(steering);
    }

    @Override
    public void moveTank(MotionControl tankControl, int powerLeftPercent, int powerRightPercent, int rotations, boolean brakeAtEnd)
            throws TerminationException {
        logger.fine(String.format("sim moveTank( %s, powerLeft: %d, powerRight: %d, rotations: %d, brake: %b )",
                tankControl.toString(), powerLeftPercent, powerRightPercent, rotations, brakeAtEnd));

        powerLeftPercent = limit100(powerLeftPercent);
        powerRightPercent = limit100(powerRightPercent);
        wheelRotations = rotations;

        speedPercent = (powerLeftPercent + powerRightPercent) / 2;
        double steeringScale = Math.abs(powerLeftPercent) + Math.abs(powerRightPercent);
        if (steeringScale > 0) {
            steeringPercent = 100 * (powerRightPercent - powerLeftPercent) / steeringScale;
        } else {
            steeringPercent = 0;
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

        double speed = fullSpeed * speedPercent / 100;
        double dRot = Math.abs(speed / wheelDiameter);
        wheelRotations -= dRot;
        
        if (wheelRotations <= 0) {
            //  no more motions authorized
            wheelRotations = 0;
            speedPercent = 0;
        } else {
            //  create local copy of the raw positions
            double theta = robotModel.getRotation();
            double x = robotModel.getX();
            double y = robotModel.getY();

            //  update the positions
            double steering = fullSteering * steeringPercent / 100;
            double dTheta = steering / samplesPerSecond;

            theta += dTheta;
            double dx = speed * Math.cos(theta);
            double dy = speed * Math.sin(theta);

            //  update the local positions
            x += dx;
            y += dy;

            //  output the new values
            robotModel.setDisplayLocation(x, y);
            robotModel.setRotation(theta);
        }


        logger.fine(String.format("sim: pos(%6.1f, %6.1f), theta: %7.2f",
                robotModel.getX(), robotModel.getY(), robotModel.getRotation()));
    }

    @Override
    public boolean isDone() {
        return speedPercent == 0 && wheelRotations == 0;
    }

    @Override
    public RobotType getRobotType() {
        return RobotType.simulation;
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
     * rotations of the "wheel"
     */
    private double wheelRotations;
    /**
     * maximum speedPercent allowed in pixels per tick
     */
    private static double fullSpeed = 0.3;
    /**
     * maximum speedPercent allowed in pixels per tick
     */
    private static double fullSteering = 0.25 * Math.PI;
    /**
     * robot wheel diameter
     */
    private static double wheelDiameter = 3;

    /**
     * Samples per second of the main loop
     */
    private static int samplesPerSecond = 60;


    //  note: if you copy this logger initialization, manually update the class name to your class
    private static final Logger logger = Logger.getLogger(SimulationRobotMotion.class.getName());
}
