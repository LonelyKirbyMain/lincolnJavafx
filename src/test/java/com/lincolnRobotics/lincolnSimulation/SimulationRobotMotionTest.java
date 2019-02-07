package com.lincolnRobotics.lincolnSimulation;

import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.TerminationException;
import org.junit.Test;

public class SimulationRobotMotionTest {

    @Test
    public void testApplyTankMotionTick() {
        RobotModel robotModel = new RobotModel(800, 800);
        robotModel.setRotation(0);
        SimulationRobotMotion simulationRobotMotion = new SimulationRobotMotion(60, robotModel);
        System.out.println(robotModel.toString());
        try {
            simulationRobotMotion.moveTank(RobotMotion.MotionControl.onForRotations,
                    80, 60, 3, true);
        } catch (TerminationException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            simulationRobotMotion.applyTankMotionTick();

            System.out.println(robotModel.toString());
        }

    }
}