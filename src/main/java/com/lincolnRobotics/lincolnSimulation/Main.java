package com.lincolnRobotics.lincolnSimulation;


import com.lincolnRobotics.robotAutonomous.SampleRobotMotionSequencer;
import com.lincolnRobotics.robotControl.MainRobotLoop;
import com.lincolnRobotics.robotControl.RobotMotion;
import com.lincolnRobotics.robotControl.RobotMotionSequencer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * The JavaFx application main routine.
 * JavaFx was chosen to minimize the impact on the Java code
 * in the lincolnControl package.
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
        primaryStage.setScene(new Scene(root, 850, 900));
        primaryStage.show();

        //  connect the autonomous controller to the simulation sequencer
        RobotSimulationJavaFxController robotSimulationJavaFxController = loader.getController();
        RobotModel m = robotSimulationJavaFxController.getRobotModel();
        RobotMotion robotMotion = new SimulationRobotMotion(60, m);

        //  run the main robotMotionSequencer loop
        simulationMainRobotLoop = new MainRobotLoop(new SampleRobotMotionSequencer(robotMotion));

        //  register the restart event handler
        robotSimulationJavaFxController.registerRestartEventHandler(simulationMainRobotLoop);

        simulationMainRobotLoop.start();


        //  testing:
        try {
            ArrayList<Class<? extends RobotMotionSequencer>> robotMotionSequencers = new ArrayList<>();
            for (Class klass : getAllClassesFromPackage("com.lincolnRobotics.robotAutonomous"))
                if (RobotMotionSequencer.class.isAssignableFrom(klass))
                    robotMotionSequencers.add(klass);
            robotSimulationJavaFxController.setRobotAutonomousClasses(robotMotionSequencers);
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
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

    /**
     * The initial Java method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Load all classes from a package.
     * <p>Lincoln students note:  This is very advanced stuff.  Read it only for the amusment value.</p>
     *
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Class[] getAllClassesFromPackage(final String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Find file in package.
     *
     * @param directory
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }


    private MainRobotLoop simulationMainRobotLoop;
}
