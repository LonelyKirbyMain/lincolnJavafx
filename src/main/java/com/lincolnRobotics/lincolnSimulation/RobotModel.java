package com.lincolnRobotics.lincolnSimulation;

/**
 * Robot simulation model used for maintaining robot state and
 * rendering on the simulated playing field.
 */
class RobotModel {
    /**
     * Initiate the simulation robot model with the given width and height.
     *
     * @param maxFieldWidth  width in pixels of the simulation field image
     * @param maxFieldHeight height in pixels of the simulation field image
     */
    RobotModel(double maxFieldWidth, double maxFieldHeight) {
        this.maxFieldWidth = maxFieldWidth;
        this.maxFieldHeight = maxFieldHeight;
        reset();
    }

    /**
     * Set the location (position on the field) of the robot model
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    final void setDisplayLocation(double x, double y) {
        this.x = Math.max(0, Math.min(x, getMaxFieldWidth()));
        this.y = Math.max(0, Math.min(y, getMaxFieldHeight()));
    }

    /**
     * Return the x coordinate of the robot model
     *
     * @return x coordinate
     */
    final double getX() {
        return x;
    }

    /**
     * Return the y coordinate of the robot model
     *
     * @return y coordinate
     */
    final double getY() {
        return y;
    }

    /**
     * Return the rotation of the robot model in radians (multiples of pi)
     *
     * @return y coordinate
     */
    final double getRotation() {
        return rotation;
    }

    final public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * Return the width in pixels of the simulation image
     *
     * @return width in pixels
     */
    final double getMaxFieldWidth() {
        return maxFieldWidth;
    }

    /**
     * Return the height in pixels of the simulation image
     *
     * @return height in pixels
     */
    final double getMaxFieldHeight() {
        return maxFieldHeight;
    }

    /**
     * Reset the robot model to a know condition.
     */
    final void reset() {
        setDisplayLocation(maxFieldWidth / 2, maxFieldHeight / 2);
        setRotation(-Math.PI / 4);
    }


    public double getWidthCm() {
        return widthCm;
    }

    public void setWidthCm(double widthCm) {
        this.widthCm = widthCm;
    }

    public double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(double heightCm) {
        this.heightCm = heightCm;
    }

    private double x = 100;
    private double y = 100;
    private double widthCm = 45;
    private double heightCm = 45;
    private double rotation = 0; //  rotation in radians
    private final double maxFieldWidth;
    private final double maxFieldHeight;
}
