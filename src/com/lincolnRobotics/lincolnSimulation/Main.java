package com.lincolnRobotics.lincolnSimulation;


import com.lincolnRobotics.robotAutonomous.SampleRobotMotionSequencer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The JavaFx application main routine.
 * JavaFx was chosen to minimize the impact on the Java code
 * in the lincolnControl package.
 */
public class Main extends Application {

    /**
     * Start the JavaFx simulation of the Lincoln RTC robot.
     * <p>
     * JavaFx is a Java user interface (UI) framework.
     * </p>
     *
     * @param primaryStage the primary JavaFx stage for this application
     * @throws Exception any java exception thrown by the application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //  load the simulation user interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sim.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Lincoln Robot Simulation");
        primaryStage.setScene(new Scene(root, 850, 900));
        primaryStage.show();

        //  connect the autonomous controller to the simulation sequencer
        RobotSimulationJavaFxController robotSimulationJavaFxController = loader.getController();
        RobotModel m = robotSimulationJavaFxController.getRobotModel();
        SimulationRobot robot = new SimulationRobot(m);

        //  run the main robotMotionSequencer loop
        simulationMainRobotLoop = new SimulationMainRobotLoop(new SampleRobotMotionSequencer(robot));

        //  register the restart event handler
        robotSimulationJavaFxController.registerRestartEventHandler(simulationMainRobotLoop);

        simulationMainRobotLoop.start();
    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     * <p>
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    @Override
    public void stop() throws Exception {
        if (simulationMainRobotLoop != null)
            simulationMainRobotLoop.stop();
        super.stop();
    }

    /** The initial Java method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private SimulationMainRobotLoop simulationMainRobotLoop;
}
