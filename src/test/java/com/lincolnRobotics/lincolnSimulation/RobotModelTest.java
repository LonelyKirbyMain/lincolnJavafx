package com.lincolnRobotics.lincolnSimulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Sample JUnit test for the simulation's RobotModel
 * <p>Note that none of the tests are very complex.  This is intentional.
 * You do not want to be confused by the by the testing process.
 * All results should be obvious.</p>
 * <p>The JUnit test is a great way to develop the class under test.
 * Write the class, write it's test, run the test.  If the class passes,
 * you're good.  If it doesn't, you have a very simple environment to debug in.
 * </p>
 * <p>Right click (or control click or command click on the mouse and choose "Run 'theMethodToTest'.
 * Notice now that the test is in the runner configuration window and can be run or
 * debugged with a click on the icons.</p>
 */
public class RobotModelTest {

    @Test
    public void setDisplayLocation() {

        double maxFieldWidth = 100;
        double maxFieldHeight = 200;
        RobotModel robotModel = new RobotModel(maxFieldWidth, maxFieldHeight);

        //  see that the robot is not allowed to be positioned outside of the field
        robotModel.setDisplayLocation(0, 0);
        assertEquals(0, robotModel.getX(), 1e-6);
        assertEquals(0, robotModel.getY(), 1e-6);
        robotModel.setDisplayLocation(-140, -5000);
        assertEquals(0, robotModel.getX(), 1e-6);
        assertEquals(0, robotModel.getY(), 1e-6);
        robotModel.setDisplayLocation(maxFieldWidth, maxFieldHeight);
        assertEquals(maxFieldWidth, robotModel.getX(), 1e-6);
        assertEquals(maxFieldHeight, robotModel.getY(), 1e-6);
        robotModel.setDisplayLocation(1400, 5000);
        assertEquals(maxFieldWidth, robotModel.getX(), 1e-6);
        assertEquals(maxFieldHeight, robotModel.getY(), 1e-6);
    }

    @Test
    public void getRotation() {
        RobotModel robotModel = new RobotModel(100, 100);

        for (double rot = -1.5 * Math.PI * 2; rot < 1.5 * Math.PI * 2; rot += Math.PI * 0.1) {
            robotModel.setRotation(rot);
            assertEquals(rot, robotModel.getRotation(), 1e-6);
        }
    }

    @Test
    public void getWidthCm() {
        RobotModel robotModel = new RobotModel(100, 100);
        assertTrue(robotModel.getWidthCm() > 0);
    }

    @Test
    public void setWidthCm() {
        RobotModel robotModel = new RobotModel(100, 100);
        robotModel.setWidthCm(45);
        assertEquals(45, robotModel.getWidthCm(), 1e-6);
        robotModel.setWidthCm(25);
        assertEquals(25, robotModel.getWidthCm(), 1e-6);
    }

    @Test
    public void getHeightCm() {
        RobotModel robotModel = new RobotModel(100, 100);
        assertTrue(robotModel.getLengthCm() > 0);
    }

    @Test
    public void setHeightCm() {
        RobotModel robotModel = new RobotModel(100, 100);
        robotModel.setLengthCm(45);
        assertEquals(45, robotModel.getLengthCm(), 1e-6);
        robotModel.setLengthCm(25);
        assertEquals(25, robotModel.getLengthCm(), 1e-6);
    }
}