package com.lincolnRobotics.robotControl;

public interface RobotMotionSequencer {
    public void initialize();

    public void tick();

    public void stop();

    public RobotMotion getRobotMotion();
}
