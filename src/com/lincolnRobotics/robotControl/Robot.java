package com.lincolnRobotics.robotControl;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

/**
 * An implementation of the RobotMotion interface for the Lejos Lego.
 * 
 * @author bob
 *
 */
public class Robot implements RobotMotion {

	public Robot() {
		Motor.A.setAcceleration(500);
		Motor.B.setAcceleration(500);
	}

	@Override
	public void moveSteering(MotionControl steeringControl, int steering, int power, int rotations,
			boolean brakeAtEnd) {

		// limit inputs
		steering = limit100(steering);
		power = limit100(power);

		// convert the steering to left/right rotation speed
		// steering -100 -75 -50 -25 __0 _25 _50 _75 _100
		// A left __-100 -50 __0 _50 100 100 100 100 _100
		// B right __100 100 100 100 100 _50 __0 -50 -100
		int powerLeft = steering >= 0 ? 100 : (2 * steering + 100);
		int powerRight = steering <= 0 ? 100 : (2 * -steering + 100);

		int degrees = rotations;
		final int speedGain = 5;

		switch (steeringControl) {
		case off:
		default:
			Motor.A.stop();
			Motor.B.stop();
			break;
		case onForDegrees:
			Motor.A.setSpeed(speedGain * powerLeft);
			Motor.A.rotateTo(Motor.A.getTachoCount() + (powerLeft * degrees) / 100, true);
			Motor.B.setSpeed(speedGain * powerRight);
			Motor.B.rotateTo(Motor.B.getTachoCount() + (powerRight * degrees) / 100, true);
			break;
		case onForRotations:
			degrees = rotations * 360;
			Motor.A.setSpeed(speedGain * powerLeft);
			Motor.A.rotateTo(Motor.A.getTachoCount() + (powerLeft * degrees) / 100, true);
			Motor.B.setSpeed(speedGain * powerRight);
			Motor.B.rotateTo(Motor.B.getTachoCount() + (powerRight * degrees) / 100, true);
			break;
		}
	}

	@Override
	public void moveTank(MotionControl tankControl, int powerLeft, int powerRight, int rotations, boolean brakeAtEnd) {
		switch (tankControl) {
		case off:
		default:
			Motor.A.stop();
			Motor.B.stop();
			break;
		case onForDegrees:
			Motor.A.rotateTo(Motor.A.getTachoCount() + (limit100(powerLeft) * rotations) / 100, true);
			Motor.B.rotateTo(Motor.B.getTachoCount() + (limit100(powerRight) * rotations) / 100, true);
			break;
		case onForRotations:
			Motor.A.rotateTo(Motor.A.getTachoCount() + (limit100(powerLeft) * rotations * 360) / 100, true);
			Motor.B.rotateTo(Motor.B.getTachoCount() + (limit100(powerRight) * rotations * 360) / 100, true);
			break;
		}
	}

	@Override
	public void stop() {
		Motor.A.stop();
		Motor.B.stop();
	}

	
	@Override
	public boolean isDone() {
		/*
		 * TODO: Currently assumes the command ends with a still condition.
		 */
		return !Motor.A.isMoving() && !Motor.B.isMoving();
	}

	@Override
	public float getSensor(int sensorId) {
		float samples[] = new float[12];

		sensorMode.fetchSample(samples, 0);
		return samples[sensorId];
	}
	
	@Override
	public RobotType getRobotType() {
		// TODO Auto-generated method stub
		return RobotType.lejos;
	}

	/**
	 * Limit the given integer to -100 to 100 inclusive.
	 * 
	 * @param n input integer
	 * @return the limited integer
	 */
	private final int limit100(int n) {
		if (n > 100)
			return 100;
		if (n < -100)
			return -100;
		return n;
	}

	// sensor resources initialized at construction
	private EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
	private SensorMode sensorMode = touchSensor.getTouchMode();


}
