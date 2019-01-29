package com.lincolnRobotics.robotControl;

public class MainRobotLoop implements Runnable {

    public MainRobotLoop(RobotMotionSequencer robotMotionSequencer) {
        this.robotMotionSequencer = robotMotionSequencer;
    }

    public void start() {
        Thread timingThread = new Thread(this);
        timingThread.start();
    }

    @Override
    public void run() {
        waitForStart();

        robotMotionSequencer.initialize();

        while (isActive()) {
            delay();

            robotMotionSequencer.tick();

            loopTick();
        }
        afterEnd();
    }

    public boolean isActive() {
        return isActive;
    }

    public void delay() {
        try {
            Thread.sleep((int) (1000 / getSampleHertz()));
        } catch (InterruptedException ex) {
            // do nothing
        }
    }

    public void waitForStart() {
    }

    public void loopTick() {
        robotMotionSequencer.tick();
    }


    public void afterEnd() {
    }

    public void stop() {
        isActive = false;
    }

    public int getSampleHertz() {
        return sampleHertz;
    }

    public void setSampleHertz(int sampleHertz) {
        this.sampleHertz = sampleHertz;
    }

    protected RobotMotionSequencer robotMotionSequencer;
    private boolean isActive = true;
    private int sampleHertz = 60;
}
