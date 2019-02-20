package com.lincolnRobotics.lincolnSimulation;


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
        RobotSimulationJavaFxController robotSimulationJavaFxController = loader.getController();

        //  add all autonomous sequencers to the selection list
        try {
            //  create a set of classes where the classes are listed in order of their simple class name.
            TreeSet<Class<? extends RobotMotionSequencer>> robotMotionSequencers = new TreeSet<>((o1, o2) -> {
                return o1.getSimpleName().compareTo(o2.getSimpleName());
            });

            //  add all concrete classes that extend RobotMotionSequencer
            for (Class klass : getAllClassesFromPackage("com.lincolnRobotics.robotAutonomous")) {
                //  skip abstract classes
                if (Modifier.isAbstract(klass.getModifiers()))
                    continue;

                //  add RobotMotionSequencer's to the list
                if (RobotMotionSequencer.class.isAssignableFrom(klass))
                    robotMotionSequencers.add(klass);
            }

            //  let the javaFx controller know so it can create the sequencer selection list
            robotSimulationJavaFxController.setRobotAutonomousClasses(robotMotionSequencers);

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

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
     * <p>Lincoln students note:  This is very advanced stuff.  Read it only for the amusment value.</p>
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

}
