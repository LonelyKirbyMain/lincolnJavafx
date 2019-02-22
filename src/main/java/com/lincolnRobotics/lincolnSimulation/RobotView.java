package com.lincolnRobotics.lincolnSimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

class RobotView {
    RobotView(RobotModel robotModel) {
        this.robotModel = robotModel;
    }

    void render(GraphicsContext gc) {
        gc.save(); // saves the current state on stack, including the current transform
        double canvasH = gc.getCanvas().getHeight();  //  used to deal with upside down y axis

        //  rotate
        double rw = robotModel.getWidthCm() * pixelsPerCm;
        double rh = robotModel.getLengthCm() * pixelsPerCm;
        Rotate rot = new Rotate(radiansToDegrees(robotModel.getRotation()),
                robotModel.getX() + rw / 2,
                canvasH - (robotModel.getY() - rh / 2));
        gc.setTransform(rot.getMxx(), rot.getMyx(), rot.getMxy(), rot.getMyy(), rot.getTx(), rot.getTy());


        //  robotMotionSequencer
        gc.setFill(cardinal);
        gc.fillRect(robotModel.getX(), canvasH - robotModel.getY(), rw, rh);
        gc.setFill(green);
        double frontBarWidth = rw / 5;
        gc.fillRect(robotModel.getX() + rw - frontBarWidth, canvasH - robotModel.getY(), frontBarWidth, rh);

        gc.restore(); // back to original state (before rotation)
    }


    private double radiansToDegrees(double radians) {
        radians %= twoPi;
        if (radians < 0)
            radians += twoPi;
        return -360 * radians / twoPi;
    }

    private static final double twoPi = 2 * Math.PI;
    private final RobotModel robotModel;
    private static final double pixelsPerCm = 2;
    private static final Color cardinal = Color.web("be392a");
    private static final Color green = Color.web("00b000");
}
