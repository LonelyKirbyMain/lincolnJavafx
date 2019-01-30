package com.lincolnRobotics.lincolnSimulation;

/**
 * Robot simulation model used for maintaining robot state and
 * rendering on the simulated playing field.
 */
class RobotModel {
    /**
     * Initiate the simulation robot model with the given width and height.
     *
     * @param width  width in pixels of the simulation image
     * @param height height in pixels of the simulation image
     */
    RobotModel(double width, double height) {
        maxWidth = width;
        maxHeight = height;
        reset();
    }

    /**
     * Set the location (position on the field) of the robot model
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    final void setLocation(double x, double y) {
        this.x = Math.max(0, Math.min(x, getMaxWidth()));
        this.y = Math.max(0, Math.min(y, getMaxHeight()));
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
    final double getMaxWidth() {
        return maxWidth;
    }

    /**
     * Return the height in pixels of the simulation image
     *
     * @return height in pixels
     */
    final double getMaxHeight() {
        return maxHeight;
    }

    /**
     * Reset the robot model to a know condition.
     */
    final void reset() {
        setLocation(maxWidth / 2, maxHeight / 2);
        setRotation(-Math.PI / 4);
    }

    private double x = 100;
    private double y = 100;
    private double rotation = 0; //  rotation in radians
    private final double maxWidth;
    private final double maxHeight;
}
