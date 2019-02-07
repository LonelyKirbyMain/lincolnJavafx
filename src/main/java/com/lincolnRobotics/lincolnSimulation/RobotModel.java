package com.lincolnRobotics.lincolnSimulation;

/**
 * Robot simulation model used for maintaining robot state and
 * rendering on the simulated playing field.
 */
class RobotModel {
    /**
     * Initiate the simulation robot model with the given width and height.
     *
     * @param fieldWidthPixels  width in pixels of the simulation image
     * @param fieldHeightPixels height in pixels of the simulation image
     */
    RobotModel(double fieldWidthPixels, double fieldHeightPixels) {

        fieldMaxWidth = fieldWidthPixels;
        fieldMaxHeight = fieldHeightPixels;
        scalePxPerCm = fieldWidthPixels / fieldWidthCm;

        reset();
    }

    public double getWidthCm() {
        return widthCm;
    }

    public double getLenghtCm() {
        return lenghtCm;
    }

    /**
     * Set the location (position on the field) of the robot model
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    final void setLocation(double x, double y) {
        this.x = Math.max(0, Math.min(x, getFieldMaxWidth()));
        this.y = Math.max(0, Math.min(y, getFieldMaxHeight()));
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
    final double getFieldMaxWidth() {
        return fieldMaxWidth;
    }

    /**
     * Return the height in pixels of the simulation image
     *
     * @return height in pixels
     */
    final double getFieldMaxHeight() {
        return fieldMaxHeight;
    }

    /**
     * Reset the robot model to a know condition.
     */
    final void reset() {
        setLocation(fieldMaxWidth / 2, fieldMaxHeight / 2);
        setRotation(-Math.PI / 4);
    }

    @Override
    public String toString() {
        return String.format("%s{ (%9.3f,%9.3f), "
                        + thetaCharacter + ": %9.3f "
                        + piCharacter + " = %9.1f" + degreeCharacter + "}",
                getClass().getSimpleName(), x, y, rotation, Math.toDegrees(rotation));
    }

    private static final Character piCharacter = '\u03C0';
    private static final Character degreeCharacter = '\u00B0';
    private static final Character thetaCharacter = '\u03F4';

    private static final double cmPerInch = 2.54;
    private final double widthCm = 18 * cmPerInch;
    private final double lenghtCm = getWidthCm();
    private final double fieldWidthCm = 12 * 12 * cmPerInch;
    private final double fieldLenghtCm = fieldWidthCm;
    private final double scalePxPerCm;
    private double x = 100;
    private double y = 100;
    private double rotation = 0; //  rotation in radians
    private final double fieldMaxWidth;
    private final double fieldMaxHeight;
}
