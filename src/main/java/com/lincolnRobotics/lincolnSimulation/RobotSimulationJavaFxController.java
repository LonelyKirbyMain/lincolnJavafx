package com.lincolnRobotics.lincolnSimulation;


import com.lincolnRobotics.robotAutonomous.SampleRobotMotionSequencer;
import com.lincolnRobotics.robotControl.MainRobotLoop;
import com.lincolnRobotics.robotControl.RobotMotion;
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

import java.util.Collection;
import java.util.HashMap;

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
        robotMotion = new SimulationRobotMotion(60, robotModel);

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("restart");
                robotMotionSequencer.restart();
            }
        });

        sequencerSelection.setOnAction((e) -> {
            robotMotionSequencer.stop();
            try {
                robotMotionSequencer = classHashMap.get(sequencerSelection.getSelectionModel().getSelectedItem()).newInstance();
                robotMotionSequencer.setRobotMotion(robotMotion);
                simulationMainRobotLoop.setRobotMotionSequencer(robotMotionSequencer);
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }

        });

        //  called the JavaFx framework when the display is being refreshed.
        new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                GraphicsContext graphicsContext = robotField.getGraphicsContext2D();

                RobotFieldGraphics.render(graphicsContext);

                robotView.render(graphicsContext);
            }
        }.start();

        //  run the main robotMotionSequencer loop
        robotMotionSequencer = new SampleRobotMotionSequencer();
        robotMotionSequencer.setRobotMotion(robotMotion);
        simulationMainRobotLoop = new MainRobotLoop(robotMotionSequencer);
        simulationMainRobotLoop.start();
    }

    @FXML
    public void stop()
            throws Exception {
        simulationMainRobotLoop.stop();
    }

    public void setRobotAutonomousClasses(Collection<Class<? extends RobotMotionSequencer>> RobotMotionSequencerClasses) {

        classHashMap.clear();
        sequencerSelection.getItems().clear();
        ObservableList<String> list = sequencerSelection.getItems();
        for (Class klass : RobotMotionSequencerClasses) {
            classHashMap.put(klass.getSimpleName(), klass);
            list.add(klass.getSimpleName());
        }
    }

    private RobotModel robotModel;
    private RobotView robotView;
    private RobotMotion robotMotion;
    private RobotMotionSequencer robotMotionSequencer;
    private MainRobotLoop simulationMainRobotLoop;
    private HashMap<String, Class<? extends RobotMotionSequencer>> classHashMap = new HashMap<>();
}
