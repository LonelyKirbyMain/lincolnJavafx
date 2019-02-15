package com.lincolnRobotics.robotControl;


/**
 * The main robot loop for Lincoln robotics software in a number of environments.
 * The loop here is designed to work in the Lincoln simulation, Lejos EV3,
 * or the FTC robot.   The architecture is dictated by the FTC code.
 * The concept is to initialize the robot, wait for the proper stop,
 * loop through the list of operations
 * that should execute at every clock tick,
 * and end the infinite loop when requested.
 */
public class MainRobotLoop implements Runnable,
        RestartEventHandler {

    /**
     * Initialize the loop with the robot motion sequencer to be run in the loop.
     *
     * @param robotMotionSequencer the command sequencer to be run by this loop
     */
    public MainRobotLoop(RobotMotionSequencer robotMotionSequencer) {
        this.robotMotionSequencer = robotMotionSequencer;
    }

    /**
     * Start the execution of the loop.
     */
    public void start() {
        Thread timingThread = new Thread(this);
        timingThread.start();
    }

    /**
     * Run the main loop for the robot control
     */
    @Override
    public void run() {
        //  only run when released
        waitForStart();

        //  initialize the robot command sequencer
        robotMotionSequencer.initialize();

        //  main loop
        while (isActive()) {
            //  wait for the correct time
            delay();

            //  run the simulation model if the robot is a simulation
            robotMotionSequencer.getRobotMotion().tick();

            //  get the sequencer to issue new commands when appropriate
            robotMotionSequencer.tick();

            //  let the environment process if it needs to
            loopTick();
        }
        afterEnd();
    }

    /**
     * Respond true if the loop should continue to run.
     * @return true if the loop should continue to run
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Delay the correct duration.
     * fixme: should schedule event at correct time so the loop doesn't vary based on it's execution duration.
     */
    public void delay() {
        try {
            Thread.sleep((int) (1000 / getSampleHertz()));
        } catch (InterruptedException ex) {
            // do nothing
        }
    }

    /**
     * Default restart event handler
     *
     * @param event the restart event
     */
    @Override
    public void onRestartEvent(RestartEvent event) {
        robotMotionSequencer.restart();
    }

    /**
     * Wait for the environment to allow robot motion.
     */
    public void waitForStart() {
        //  default behavior is to begin immediately
    }

    /**
     * Give the environment the opportunity to process after each loop cycle.
     */
    public void loopTick() {
        //   This will be overloaded based on the environment.
    }

    /**
     * Allow the environment to clean up when the loop has completed.
     */
    public void afterEnd() {
    }

    /** Stop the main loop.  Typically this happens asynchronously.
     *
     */
    public void stop() {
        isActive = false;
    }

    /** Provide the sample rate of the main loop
     *
     * @return the rate in hertz
     */
    public int getSampleHertz() {
        return sampleHertz;
    }

    /**
     * Set the sample rate of the main loop
     * @param sampleHertz the new sample rate
     */
    public void setSampleHertz(int sampleHertz) {
        this.sampleHertz = sampleHertz;
    }

    protected RobotMotionSequencer robotMotionSequencer;
    private boolean isActive = true;
    private int sampleHertz = 60;

}
