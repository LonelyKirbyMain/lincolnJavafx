
/**
 * Lincoln Robotics is a package designed to allow a simplified software environment
 * for robot control in autonomous mode.  The details of the low level in any
 * environment is hidden from the coder.
 * <p>Environments the code is designed to work in the following environments.</p>
 * <ul>
 * <li>Lincoln simulation on JavaFx</li>
 * <li>Lejos EV3 robots</li>
 * <li>First FTC robots</li>
 * </ul>
 * <p>This demands that the dependency on these environments should not leak to
 * the robot control package.</p>
 * <p>Notes for use of Intellij Idea from Jetbrains</p>
 * <ul>
 * <li>Install Java 8 or Java 11 JDK
 * <ul><li>from the command line, try:  java -version</li>
 * <li>Either version of Java can be made to work.</li>
 * <li>Note: You will need the JDK (development kit) not the JRE (runtime environment).  The JDK includes a JRE.</li>
 * <li>Java 11 from https://jdk.java.net/ does not automatically
 * include JavaFx (the graphics package for the simulation).  This can be
 * fixed for java 11 in the pom.mxl file.  I suggest at least java 11.0.2</li>
 * <li>Java 8 does include JavaFx so https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
 * can work as well.  I suggest at least jdk1.8.0_201.</li>
 * <li>It's always going to help to have the latest update.</li>
 * </ul></li>
 * <li>Install https://www.jetbrains.com/idea/
 * <ul><li>Use the community version.</li>
 * </ul></li>
 * <li>To control logging from the logging.properties file:  in the run pulldown, Edit Configurations,
 * Maven, sim, runner, VM options: -Djava.util.logging.config.file=logging.properties</li>
 * <li>See the documentation for the <a href="./lincolnSimulation/Main.html">Main class</a> for more details
 * on project setup.</li>
 * <li>If you get a crash with the message: "Caused by: java.lang.IllegalStateException: Location is not set.",
 * this means the pom.xml file has not been adjusted properly for the Java version.</li>
 * </ul>
 * <p>Potential Student Projects </p>
 * <ul>
 * <li>javadocs improved</li>
 * <li>design review of the RobotMotion interface (include imaging)</li>
 * <li>leo: field grahics, robot as well</li>
 * <li>adjust/improve sim RobotMotion interface</li>
 * <li>
 * bob: isolate robotMotion adapter</li>
 * <li>
 * ev3 RobotMotion interface implementation</li>
 * <li>
 * ftc RobotMotion interface implementation</li>
 * <li>
 * bob: attempt to remove eclipse from ev3 development</li>
 * <li>
 * other robot sequences</li>
 * <li>
 * minerals in sim</li>
 * <li>
 * 3D sim view</li>
 * <li>
 * Java logging on ftc telemetry stream</li>
 * <li>
 * wifi debugging on ftc documentation</li>
 * <li>
 * better intro for beginners</li>
 * <li>
 * document ftc setup stuff</li>
 * <li>
 * tom: adding imaging to the (simulating the image API in the simulation)</li>
 * <li>
 * tune sim accuracy to ftc environment</li>
 * <li>
 * nick: image decoding
 * <ul>
 * <li>research</li>
 * <li>design api</li>
 * <li>design api tests and test env</li>
 * <li>design development env</li>
 * <li>prototype the dev env</li>
 * <li>UI required?  no</li>
 * <li>implement the api</li>
 * <li>test api</li>
 * <li>inject project back to lincoln robotMotion code</li>
 * <li>repair sim and ev3 implementations</li>
 * <li>test robot</li>
 * </ul>
 * </li>
 * <li>Lejos EV3 robots</li>
 * </ul>
 */
package com.lincolnRobotics;
