package com.lincolnRobotics.lincolnSimulation;

import com.lincolnRobotics.robotControl.MainRobotLoop;
import com.lincolnRobotics.robotControl.RestartEvent;
import com.lincolnRobotics.robotControl.RestartEventHandler;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;

public class SimulationMainRobotLoop
        extends MainRobotLoop
        implements RestartEventHandler {
    public SimulationMainRobotLoop(RobotMotionSequencer robotMotionSequencer) {
        super(robotMotionSequencer);
    }

    @Override
    public void onRestartEvent(RestartEvent event) {
        robotMotionSequencer.stop();
        stop();
    }
}
