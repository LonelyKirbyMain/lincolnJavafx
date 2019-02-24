package com.lincolnRobotics.robotAutonomous;


import com.lincolnRobotics.robotControl.RobotMotionSequencer;

import java.util.TreeSet;

/**
 * Manage a list of sequencer classes for use in the UI and their application to robot control.
 * <p>If you add a new sequencer, it's best if it's listed here.</p>
 */
public class RobotMotionSequencerSet {

    /**
     * Register a RobotMotionSequencer class so it can be known to the UI at run time.
     * @param robotMotionSequencerClass the class
     */
    private static void register(Class<? extends RobotMotionSequencer> robotMotionSequencerClass) {
        robotMotionSequencerSet.add(robotMotionSequencerClass);
    }

    public static TreeSet<Class<? extends RobotMotionSequencer>> getRobotMotionSequencerSet() {
        return robotMotionSequencerSet;
    }

    /**
     * Return the default sequencer
     * @return the default sequencer
     */
    public static Class<? extends RobotMotionSequencer> defaultRobotMotionSequencerClass() {
        return SampleRobotMotionSequencer.class;
    }


    private static  Class<? extends RobotMotionSequencer> defaultRobotMotionSequencer =
            SampleRobotMotionSequencer.class;

    private static TreeSet<Class<? extends RobotMotionSequencer>> robotMotionSequencerSet =
            new TreeSet<>((o1, o2) -> {
                return o1.getSimpleName().compareTo(o2.getSimpleName());
            });
    static {
        register(SampleRobotMotionSequencer.class);
        register(RobotMotionTest1.class);
        register(RobotMotionTest2.class);
    }
}
