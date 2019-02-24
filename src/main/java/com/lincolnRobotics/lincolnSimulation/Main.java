package com.lincolnRobotics.lincolnSimulation;


import com.lincolnRobotics.robotAutonomous.RobotMotionSequencerSet;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;

/**
 * The JavaFx application main routine.
 * JavaFx was chosen to minimize the impact on the Java code
 * in the lincolnControl package.
 * <p>To enable logging: </p>
 * <p>-Djava.util.logging.config.file=logging.properties</p>
 */
public class Main extends Application {

    /**
     * Start the JavaFx simulation of the Lincoln simulation robot.
     * <p>
     * JavaFx is a Java user interface (UI) framework.
     * </p>
     * in intellij idea:
     * <p>
     * for simulation running:
     * configuration pulldown: Edit Configurations...
     * click + sign in upper left corner
     * select Maven
     * change name to something like: sim
     * Parameters tab:
     * Command Line: compile javadoc:javadoc exec:java
     * Runner tab:
     * VM Options: -Djava.util.logging.config.file=logging.properties
     * click OK in the lower right corner
     * <p>
     * for JUnit tests:
     * configuration pulldown: Edit Configurations...
     * click + sign in upper left corner
     * select JUnit
     * change name to something like: test all
     * Configuration tab:
     * test kind pulldown: All in package
     * Search for tests: In whole project
     * click OK in the lower right corner
     *
     * @param primaryStage the primary JavaFx stage for this application
     * @throws Exception any java exception thrown by the application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //  load the simulation user interface
        FXMLLoader loader = new FXMLLoader();
        //  Intellij idea note: if the resource is not found:
        //  file, project structure, modules, java app module (lincolnJavafx), sources
        //  verify resource folders includes src/main/resources
        loader.setLocation(getClass().getResource("/sim.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Lincoln Robot Simulation");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //  connect the autonomous controller to the simulation sequencer
        robotSimulationJavaFxController = loader.getController();

        //  let the javaFx controller know so it can create the sequencer selection list
        robotSimulationJavaFxController.setRobotAutonomousClasses(RobotMotionSequencerSet.getRobotMotionSequencerSet());
    }

    public void stop() throws Exception {
        robotSimulationJavaFxController.stop();
    }

    /**
     * The initial Java method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private RobotSimulationJavaFxController robotSimulationJavaFxController;

}
