/*
 * Copyright 2016 Robert Steele, bsteele.com
 * All rights reserved.
 */
package com.bsteele.lincolnSim;

import com.bsteele.lincolnControl.RgbColor;
import com.bsteele.lincolnControl.RobotAutonomousControl;
import com.bsteele.lincolnControl.RobotInstruction;
import com.bsteele.lincolnControl.TerminationException;

import java.util.logging.Level;
import java.util.logging.Logger;

/** Robot control sequencer to run a sequence of robot motion instructions
 * to the simulated robot.
 * <p>
 *     The implementation uses two threads.  This allows the setup to return
 *     immediately but still do work over time.  The first thread runs the autonomous
 *     control robot instruction sequence from the robot controls, suspending until a
 *     requested instruction has completed.  A termination exception is thrown if the
 *     instruction sequence has been terminated.   The second thread is used to simulate
 *     the given instruction.  This may take a number of sample periods before it's
 *     completed... just as it would with a real robot.
 * </p>
 * @author bob
 */
public final class RobotSimulationControlSequencer
        implements RobotInstruction,
        RestartEventHandler {

    RobotSimulationControlSequencer(RobotAutonomousControl robotAutonomousControl, RobotModel robotModel) {
        this.robotAutonomousControl = robotAutonomousControl;
        this.robotModel = robotModel;
        start();
    }

    @Override
    public void motor(MotorRequest request, double value, MotorRequestEnd requestEnd) {
        System.out.println(String.format("motor( %s, %f, %s )", request.toString(), value, requestEnd.toString()));
        this.leftPower = value;
    }

    @Override
    public void moveSteering(MotorRequest request, double steeringDegrees, double value, MotorRequestEnd requestEnd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveTank(MotorRequest request, double leftPower, double rightPower, double rotations, MotorRequestEnd requestEnd)
            throws TerminationException {
        System.out.println(String.format("moveTank( %s, %f, %f, %f, %s )", request.toString(), leftPower, rightPower, rotations,
                requestEnd.toString()));
        this.leftPower = leftPower;
        delay(Math.abs(rotations) * 0.5);
    }

    @Override
    public RgbColor measureColor() {
        System.out.println("measureColor()");
        return new RgbColor(200, 0, 50);
    }

    @Override
    public double measureReflectedLightIntensity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double measureAmbienLightIntensity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void calibrateReflectedLightIntensity(Intensity intensity) {
        System.out.println(String.format("calibrateReflectedLightIntensity( %s )", intensity.toString()));
    }

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


    @Override
    public void onRestartEvent(RestartEvent event) {
        stop();
        //  fixme: reset the model state here... well at least do a better job
        robotModel.setLocation(200, 200);// way temp
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
