package com.lincolnRobotics.lincolnSimulation;


import com.lincolnRobotics.robotControl.RestartEvent;
import com.lincolnRobotics.robotControl.RestartEventHandler;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

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
    ComboBox sequencerSelection;

    @FXML
    private void initialize() {
        robotModel = new RobotModel(robotField.getWidth(), robotField.getHeight());
        robotView = new RobotView(robotModel);

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("restart");
                if (restartEventHandler != null)
                    restartEventHandler.onRestartEvent(new RestartEvent());
            }
        });

        sequencerSelection.setOnAction((e) -> {
            System.out.println("selected:  "+sequencerSelection.getSelectionModel().getSelectedItem());
        });

        //  called the JavaFx framework when the display is being refreshed.
        new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                GraphicsContext graphicsContext2D = robotField.getGraphicsContext2D();

                //  playing field
                double w = graphicsContext2D.getCanvas().getWidth();
                double h = graphicsContext2D.getCanvas().getHeight();

                graphicsContext2D.setFill(Color.BEIGE);
                graphicsContext2D.fillRect(0, 0, w, h);

                graphicsContext2D.setFill(Color.RED);
                graphicsContext2D.fillRect(w / 2 - 125, h / 2 - 125, 50, 50);
                graphicsContext2D.setFill(Color.BLUE);
                graphicsContext2D.fillRect(w / 2 + 125, h / 2 - 125, 50, 50);

                robotView.render(graphicsContext2D);
            }
        }.start();
    }

    public void setRobotAutonomousClasses(ArrayList<Class<? extends RobotMotionSequencer>> RobotMotionSequencerClasses) {

        sequencerSelection.getItems().clear();
        ObservableList<String> list = sequencerSelection.getItems();
        for (Class klass : RobotMotionSequencerClasses) {
            System.out.println(klass.getSimpleName());
            list.add(klass.getSimpleName());
        }
    }

    private class RobotView {
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
            gc.fillRect(robotModel.getX(), canvasH - robotModel.getY(), rw / 5, rh);

            gc.restore(); // back to original state (before rotation)
        }

        private final RobotModel robotModel;
        private static final double pixelsPerCm = 2;
    }

    private double radiansToDegrees(double radians) {
        radians %= twoPi;
        if (radians < 0)
            radians += twoPi;
        return 360 * radians / twoPi;
    }

    public RobotModel getRobotModel() {
        return robotModel;
    }

    public void registerRestartEventHandler(RestartEventHandler restartEventHandler) {
        this.restartEventHandler = restartEventHandler;
    }

    private RobotModel robotModel;
    private RobotView robotView;
    private static final Color cardinal = Color.web("be392a");
    private static final Color green = Color.web("00b000");
    private RestartEventHandler restartEventHandler;
    private static final double twoPi = 2 * Math.PI;
}
