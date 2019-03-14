package com.lincolnRobotics.lincolnSimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class RobotFieldGraphics {

    /**
     * Determines what (other than the robot) is rendered by the simulation.
     * It is a reasonable approximation of the Rover Ruckus field.
     * It has a grey background, the lander, depots in each color, 3 places for sampling for each color, and two craters
     * It does not include any minerals or the lander legs.
     * It is fully 2D and has none of the depth the real field does.
     */

    public static final void render(

            GraphicsContext graphicsContext) {
        //  playing field
        double w = graphicsContext.getCanvas().getWidth();
        double h = graphicsContext.getCanvas().getHeight();

        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(0, 0, w, h);

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(w / 2 + 200, h / 2 - 300, 10, 100);
        graphicsContext.fillRect(w / 2 + 200, h / 2 - 210, 110, 10);

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(w / 2 - 210, h / 2 + 200, 10, 100);
        graphicsContext.fillRect(w / 2 - 300, h / 2 + 200, 100, 10);

        graphicsContext.setFill(Color.DARKGREY);
        graphicsContext.fillArc(w/2+125, h/2+125, 350, 350, 90, 90, ArcType.ROUND);
        graphicsContext.fillArc(w/2-475, h/2-475, 350, 350, 270, 90, ArcType.ROUND);

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(w / 2 - 125 , h / 2 - 125, 10, 10);
        graphicsContext.fillRect(w / 2 - 75 , h / 2 - 175, 10, 10);
        graphicsContext.fillRect(w / 2 - 175, h / 2 - 75, 10, 10);

        graphicsContext.fillRect(w / 2 - 125 , h / 2 + 125, 10, 10);
        graphicsContext.fillRect(w / 2 - 75 , h / 2 + 175, 10, 10);
        graphicsContext.fillRect(w / 2 - 175, h / 2 + 75, 10, 10);

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(w / 2 + 125 , h / 2 - 125, 10, 10);
        graphicsContext.fillRect(w / 2 + 75 , h / 2 - 175, 10, 10);
        graphicsContext.fillRect(w / 2 + 175, h / 2 - 75, 10, 10);

        graphicsContext.fillRect(w / 2 + 125 , h / 2 + 125, 10, 10);
        graphicsContext.fillRect(w / 2 + 75 , h / 2 + 175, 10, 10);
        graphicsContext.fillRect(w / 2 + 175, h / 2 + 75, 10, 10);

        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillPolygon(new double[]{w/2 - 75, w/2, w/2 + 75, w/2},
                new double[]{h/2, h/2 + 75, h/2, h/2 - 75}, 4);
    }
}