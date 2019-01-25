/*
 * Copyright 2016 Robert Steele, bsteele.com
 * All rights reserved.
 */
package com.bsteele.lincolnSim;

import com.lincolnRobotics.robotControl.*;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Robot control sequencer to run a sequence of robot motion instructions
 * to the simulated robot.
 * <p>
 * The implementation uses two threads.  This allows the setup to return
 * immediately but still do work over time.  The first thread runs the autonomous
 * control robot instruction sequence from the robot controls, suspending until a
 * requested instruction has completed.  A termination exception is thrown if the
 * instruction sequence has been terminated.   The second thread is used to simulate
 * the given instruction.  This may take a number of sample periods before it's
 * completed... just as it would with a real robot.
 * </p>
 *
 * @author bob
 */
public final class RobotSimulationControlSequencer
        implements RobotMotion,
        RestartEventHandler {

    /**
     * Start simulating a robot by emulating robot command sequences
     * from the control on the given robot.
     *
     * @param robotAutonomousControl
     * @param robotModel
     */
    RobotSimulationControlSequencer(RobotAutonomousControl robotAutonomousControl, RobotModel robotModel) {
        this.robotAutonomousControl = robotAutonomousControl;
        this.robotModel = robotModel;
        start();
    }

//    @Override
//    public void motor(MotorRequest request, double value, MotorRequestEnd requestEnd) {
//        System.out.println(String.format("motor( %s, %f, %s )", request.toString(), value, requestEnd.toString()));
//        this.leftPower = value;
//    }

    @Override
    public void moveSteering(MotionControl request, int steering, int power, int rotations, boolean brakeAtEnd) {
        throw new UnsupportedOperationException("sim moveSteering() Not supported yet.");
    }

    @Override
    public void moveTank(MotionControl request, int leftPower, int rightPower, int rotations, boolean requestEnd)
            throws TerminationException
    {
        System.out.println(String.format("sim moveTank( %s, %d, %d, %d, %b )",
                request.toString(), leftPower, rightPower, rotations, requestEnd));
        this.leftPower = leftPower/100.0;
        delay(Math.abs(rotations) * 0.5);
    }

    /**
     * Acquire the sensor value for the given sensor identified.
     *
     * @param sensorId the selected sensor
     * @return the value from the sensor
     */
    public float getSensor(int sensorId) {
        return 0.0f;
    }

    /**
     * Indicate that the last motion command has completed.
     *
     * @return true if the motion command has completed.
     */
    public boolean isDone(){
        return true;
    }

//    @Override
//    public RgbColor measureColor() {
//        System.out.println("measureColor()");
//        return new RgbColor(200, 0, 50);
//    }

//    @Override
//    public double measureReflectedLightIntensity() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public double measureAmbienLightIntensity() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public void calibrateReflectedLightIntensity(Intensity intensity) {
//        System.out.println(String.format("calibrateReflectedLightIntensity( %s )", intensity.toString()));
//    }

    private void delay(double seconds) throws TerminationException {
        delay = seconds * sampleHertz;
        synchronized (RobotSimulationControlSequencer.this) {
            try {
                RobotSimulationControlSequencer.this.wait();
                if (!doRun)
                    throw new TerminationException("Control sequencing has been terminated.");
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            System.out.println(seconds + "s delay done");
        }
    }

    private final Runnable target = new Runnable() {
        @Override
        public void run() {
            while (doRun) {
                try {
                    Thread.sleep((int) (1000 / sampleHertz));
                } catch (InterruptedException ex) {
                    // do nothing
                }
                if (delay > 0) {
                    delay--;
                    //System.out.println("tick: " + delay);
                    if (delay <= 0)
                        synchronized (RobotSimulationControlSequencer.this) {
                            //System.out.println();
                            RobotSimulationControlSequencer.this.notify();
                        }
                }

                robotModel.setLocation(
                        robotModel.getX() + leftPower * Math.sin(robotModel.getRot()),
                        robotModel.getY() + leftPower * Math.cos(robotModel.getRot()));
            }
            //  get the  autonomous control thread to terminate, if it hasn't
            synchronized (RobotSimulationControlSequencer.this) {
                //System.out.println();
                RobotSimulationControlSequencer.this.notifyAll();
            }
        }

    };
    private Thread timingThread;

    private final Runnable autonomousControl = new Runnable() {
        @Override
        public void run() {
            robotAutonomousControl.run(RobotSimulationControlSequencer.this);
            doRun = false;
        }

    };
    private Thread autonomousControlThread;

    private void start() {
        doRun = true;
        timingThread = new Thread(target);
        timingThread.start();
        autonomousControlThread = new Thread(autonomousControl);
        autonomousControlThread.start();
    }

    public void stop() {
        doRun = false;
        //  wait for end of threads
        while (timingThread.isAlive() || autonomousControlThread.isAlive()) {
            try {
                Thread.sleep((int) (2000 / sampleHertz));
            } catch (InterruptedException ex) {
                //  do nothing
            }
        }
    }

    /**
     * Restart the simulation.
     *
     * @param event
     */
    @Override
    public void onRestartEvent(RestartEvent event) {
        stop();
        robotModel.reset();
        start();
    }

    private final RobotAutonomousControl robotAutonomousControl;
    private final RobotModel robotModel;
    private double leftPower;
    private static final double sampleHertz = 60;
    private double delay = 0;
    private boolean doRun = true;
    private static final Logger logger = Logger.getLogger(RobotSimulationControlSequencer.class.getName());
}
