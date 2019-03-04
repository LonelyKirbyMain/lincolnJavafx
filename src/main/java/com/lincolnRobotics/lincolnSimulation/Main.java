package com.lincolnRobotics.lincolnSimulation;


import com.lincolnRobotics.robotAutonomous.RobotMotionSequencerSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The JavaFx application main routine.
 * JavaFx was chosen to minimize the impact on the Java code
 * in the lincolnControl package.
 * <p>Note: Make sure to set the JAVA_HOME environment variable to the proper JDK.
 * Java and the JDK should be either version 8 or 11.  From the command line run: java -version" to verify your JDK.
 * If it reports as a JRE, you will need to download the JDK 11 from https://jdk.java.net (11.0.2 at this time)
 * or Java 8 from https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
 * (currently JDK 8u201).
 * You will need to adjust the pom.xml file accordingly
 * in &lt;project&gt;
 * &lt;build&gt;
 * &lt;plugins&gt;
 * &lt;plugin&gt;
 * &lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;
 * &lt;artifactId&gt;maven-compiler-plugin&lt;/artifactId&gt;
 * &lt;configuration&gt;
 * &lt;release&gt;.  Note that for version 8, the release tag is commented out.</p>
 * <p>The Java version needs to be set in File, Project Structure, Project Settings, Project, Project SDK.
 * Also the project language level should be at least 8.</p>
 * <p>To run and compile project in Idea:</p>
 * <ul>
 * <li>Project view (left pane)</li>
 * <li>open lincolnJavafx</li>
 * <li>drill down: main, java, com.lincolnRobotics, lincolnSimulation, Main</li>
 * <li>in right pane, left click</li>
 * <li>run "Main.main()"</li>
 * <li>exit running application</li>
 * <li>from run pull down select: (i.e. Main)</li>
 * <li>select Edit Configurations</li>
 * <li>select Application</li>
 * <li>select Main</li>
 * <li>Before launch:</li>
 * <li>+</li>
 * <li>Run Maven goal</li>
 * <li>command line: install</li>
 * <li>+</li>
 * <li>Run Maven goal</li>
 * <li>command line: javadoc:javadoc</li>
 * <li>OK</li>
 * </ul>
 * Open the javadoc with a browser at: ${projectDirectory}/target/site/apidocs/index.html
 * <p>To run the project outside of Intellij Idea, navigate in the command line to the
 * project directory and run:  mvn exec:java</p>
 * <p>The project javaDocs can be found in ${project_directory}/target/apidocs/index.html</p>
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
