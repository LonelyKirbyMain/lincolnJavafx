package com.bsteele.lincolnSim;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * The JavaFx controller for the use interface (UI).
 * Note that this class is very dependent on the .fxml file associated with it.
 * Many of the changes in the .fxml file will require a modification here.
 */
public class RobotSimulationJavaFxController {

    @FXML
    Canvas robotField;

    @FXML
    private Button restartButton;

    @FXML
    private void initialize() {
        robotModel = new RobotModel(robotField.getWidth(), robotField.getHeight());

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("restart");
                if (restartEventHandler != null)
                    restartEventHandler.onRestartEvent(new RestartEvent());
            }
        });

        //  called the JavaFx framework when the display is being refreshed.
        new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                GraphicsContext gc = robotField.getGraphicsContext2D();

                //  playing field
                gc.setFill(Color.BEIGE);
                gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

                //  robot
                gc.setFill(cardinal);
                gc.fillRect(robotModel.getX(), robotModel.getY(), 30, 45);

            }
        }.start();
    }

    public RobotModel getRobotModel() {
        return robotModel;
    }

    public void registerRestartEventHandler(RestartEventHandler restartEventHandler) {
        this.restartEventHandler = restartEventHandler;
    }

    private RobotModel robotModel;
    private static final Color cardinal = Color.web("be392a");
    private RestartEventHandler restartEventHandler;

}
