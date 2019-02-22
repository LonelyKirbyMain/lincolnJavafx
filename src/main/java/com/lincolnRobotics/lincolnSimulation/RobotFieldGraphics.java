package com.lincolnRobotics.lincolnSimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RobotFieldGraphics {

    public static final void render(
            GraphicsContext graphicsContext) {
        //  playing field
        double w = graphicsContext.getCanvas().getWidth();
        double h = graphicsContext.getCanvas().getHeight();

        graphicsContext.setFill(Color.BEIGE);
        graphicsContext.fillRect(0, 0, w, h);

        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(w / 2 - 125, h / 2 - 125, 50, 50);
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(w / 2 + 125, h / 2 - 125, 50, 50);
    }
}
