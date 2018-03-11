package com.bsteele.lincolnSim;

/**
 * Robot simulation model used for maintaining state and
 * rendering on the simulated playing field.
 * <p>
 * CopyRight 2018 bsteele.com
 * User: bob
 */
class RobotModel {
    RobotModel(double width, double height) {
        maxWidth = width;
        maxHeight = height;
    }

    void setLocation(double x, double y) {
        this.x = Math.max(0, Math.min(x, maxWidth));
        this.y = Math.max(0, Math.min(y, maxHeight));
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getRot() {
        return rot;
    }

    private double x = 100;
    private double y = 100;
    private double rot = -Math.PI / 4; //  rotation in radians
    private final double maxWidth;
    private final double maxHeight;


}
