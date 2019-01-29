package com.lincolnRobotics.javaIntroduction;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This class is to show a coding beginner a number of Java features
 * that will be required for robotMotionSequencer control.
 * <p>The concepts learned here will be useful for both
 * the Lincoln high school robotics API as well as
 * for the more primitive FTC level coding.</p>
 */
public class HelloWorld {

    /**
     * By convention, Java applications run from a main method
     * exactly as defined here.
     * <p>
     * Look at the source code to see a number of simple Java samples
     * to explain a number of principles that you will need for FTC robotMotionSequencer
     * control.
     * </p>
     *
     * @param args command line arguments for the Java application
     */
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.runAllLessons();
    }

    /**
     * The default construct initializes class members.
     */
    HelloWorld() {
        title = "no title yet";
    }

    /**
     * Run all lessons in the hello world class.
     */
    public void runAllLessons() {
        //  call all the lessons
        lesson0();
        lesson1();
        lesson2();
        lesson3();
        lesson4();
        lesson5();
        lesson6();
        lesson7();
    }

    /**
     * An overview of Java and the traditional Hello World application.
     */
    private void lesson0() {
        /*
        a warning
            this is all complex stuff
            virtually no one knows it all
            you will likely be lost many times in the woods before you feel at home
            this is all normal
            the best programmers learn to know when to make assumptions and when not to
                when they know they need to know more, they investigate
                once they know, the knowledge is integrated into their internal view of the software
        software
            processing input to output by explicit instructions
                value based computations
                step by step
                using existing resources
                your work will be on top of many layers
                    hardware, operating system, java, FTC code, Lincoln code
                interactive development environment (aka IDE)
                    Android Studio (modified version of Intellij IDEA)
                    the IDE is a really complicated tool
                    can be very dangerous
                    your challenge is to use it anyhow.
            your code is "compiled"  (ie. translated) into a format the machine understands
                input: Java code
                output: .apk file to be run by Android OS

        These lessons are only the minimum model for robotMotionSequencer software
            That is: only the parts of Java required for FTC

        standard stuff (you'll learn the details later)
            IDE (will be given to you)
            project (will be given to you)
            initial code (will be given to you)
            you'll be told where to start your modifications. Please stay there.
            learn by doing
            many times hovering over IDE commands will describe the command

        These lecture notes are being written in the Java language
        and run in IDE to get you used to the environment.
         */


        //  the program generally flows from top to bottom,
        //  until you control the flow with flow control statements (more later)
        // 	this is a comment.  this is implemented with the slash-slash (//) at the line beginning
        //  it always ends at the end of the line

        /*	this is a
        multi-line comment.
        it starts with a slash-star.  no spaces.
        it only ends at a star-slash. no spaces.
        */

        System.out.println("Hello World");  //	magic: printing utility you'll learn about later

         /*	expected output:

			Hello World

		*/

        //  a break point will stop execution so you can see what's happening?
        System.out.println("How are you?");

        /*	expected output:

			How are you?

		*/


        /*
        Java is a strongly typed language.
            This means all variables have a type.
            Types are enforced at compile time . (before the code can be run)
            Different types store different kinds of values.

        Java data types
            primitive types
                boolean  ie. true false
                numbers
                    int  +- 2**31   -2...  to +2...
                    double  decimal numbers 10**304 10**-304
                    char  (smallish size int like type known to represent a character)
                    +others
            String
                basically an sequence of char's
                actually a class with special relationship to Java
                more on this later

            class
                user configurable structures for doing complex things
                complex grouping of values (will explain later)
                typically a collection of data and operations (methods) that work on that data
                this is the base concept for object oriented programming
                note: it took me a year and a half to understand and internalize the object model.

        Variables
            holders of typed values
            have names
            have a type
            have values

        Literals
            are predetermined, constant values for variables:
            true
            5
            3.6
            "this is a string"
         */
    }

    /**
     * A lesson on the basic use of variables.
     */
    private void lesson1() {

        //  a local String variable
        String myLocalTitle = "Hello World";//  a local variable of type String
        //  this will only be in existence during this lesson, that is the method lesson1()
        System.out.println(myLocalTitle);   //  print the title to the console

        /*	expected output:

			Hello World

		*/

        //  modify the class title
        title = myLocalTitle + " Example";
        //  the class level variable will be remembered past the end of this lesson (method)
        System.out.println(title);

        /*	expected output:

			Hello World Example

		*/


        //  a local integer variable
        int count = 12;      //	a counter of type int

        //	counter now has the value 12
        System.out.println("count: " + count);


        //  break points stop prior to the execution of the current line

        count = 13;

        //	counter now has the value 13

        System.out.println("count: " + count);



        /*
        variable name
            should briefly but accurately indicate the value's use in the code
            variables have to start with a letter [a-zA-Z]
                follow by
                    letters
                    digits
                    underscore
                    no spaces, no dots, no special characters
            reserved names
                used by java, not legal for variable names
                example reserved names:
                    int char double boolean  String
                    if then else for switch
                    public private static final class enum interface
                    type instanceof
            conventions
                Strongly suggest variables start with a lowercase letter
                Strongly suggest methods start with a lowercase letter
                Other constructs start with an upper case letter (classes, interfaces, enums, etc)
                camelCase for compound names
                    each subsequent word of the name phrase should be capitalized
                    underscores are not used
                    examples:
                        robotPosition
                        HardwarePushbot
                        colorSensor
                        hardwareMap
                constant variables are in all capital letters
                    underscores are used to separate words
                    example: SPEED_OF_LIGHT
                    personally I disagree with this convention: speedOfLight
                        when writing code, you shouldn't care if values change over time
            names are case sensitive
                example:   firstName is not the same as firstname
                compiler will typically complain
                i suggest a heavy use of copy/paste
                notMatterHowComplexTheNameOfTheVariable is, copy/paste is quick, easy, and accurate
        */


        //  boolean
        //  logical value of true or false
        boolean isReady = true;     //  declaration and assignment can be done on the same line
        System.out.print("isReady: ");
        System.out.println(isReady);
        isReady = false;
        System.out.println("isReady: " + isReady);
        isReady = !isReady;         //  negate the ready value

        String myOutput = "isReady: " + isReady;
        System.out.println(myOutput);


        //  int
        //  signed integer numbers
        System.out.println("count: " + count);
        int studentsInTheClassroom = 13;
        System.out.println("studentsInTheClassroom: $" + studentsInTheClassroom);
        int daysOfSchoolThisYear = 104;
        System.out.println("daysOfSchoolThisYear: $" + daysOfSchoolThisYear);

        //  double
        //  decimal numbers
        double myX = 1.23;
        System.out.println("myX: " + myX);
        double pizzaFund = 13.38;
        System.out.println("pizza fund : $" + pizzaFund);

        //  String
        //  list of characters
        String s = "hi there!";
        System.out.println("s: " + s);


        //  bad variable name
        int n12345_this_is_anOddButLegalName234_but_I_suggestYou_dont_use_it = 12;
        System.out.println("n12345_this_is_anOddButLegalName234_but_I_suggestYou_dont_use_it: "
                + n12345_this_is_anOddButLegalName234_but_I_suggestYou_dont_use_it);


        //  this is an example of a camel case name
        //  the capital letter in the middle of the name indicates a new word in the compound name
        System.out.println();
        int wheelTach = 0;
        System.out.println("wheelTach: " + wheelTach);

    }


    /**
     * Lesson for expressions and operator precedence.
     */
    private void lesson2() {

        //  Lesson 2
        /*

        assignability
            double assigned by int: ok, it's automatically converted
            int assigned by double: not ok, "loss of precision"
        value
            always the current value, ie. the result of the most recent assignment

        expression
            unary expressions
                -myCount
                myY++
                !isReady
            boolean expression
                myX > 0
                isReady && myY >= 20.0
            binary expressions
                3 * myX
                3 * myCount + 5
                3 * ( myCount + 5 )
        operator precedence
            Level	Operator	Description	Associativity
            16	[]
                .
                ()	access array element
                access object member
                parentheses	left to right
            15	++
                --	unary post-increment
                unary post-decrement	not associative
            14	++
                --
                +
                -
                !
                ~	unary pre-increment
                unary pre-decrement
                unary plus
                unary minus
                unary logical NOT
                unary bitwise NOT	right to left
            13	()
                new	cast
                object creation	right to left
            12	* / %	multiplicative	left to right
            11	+ -
                +	additive
                string concatenation	left to right
            10	<< >>
                >>>	shift	left to right
            9	< <=
                > >=
                instanceof	relational	not associative
            8	==
                !=	equality	left to right
            7	&	bitwise AND	left to right
            6	^	bitwise XOR	left to right
            5	|	bitwise OR	left to right
            4	&&	logical AND	left to right
            3	||	logical OR	left to right
            2	?:	ternary	right to left
            1	 =   +=   -=
                *=   /=   %=
                &=   ^=   |=
                <<=  >>= >>>=	assignment	right to left
        */

        {
            int precedence = 3 * 5 + 2;
            System.out.println("precedence: " + precedence);
            precedence = 2 + 3 * 5;
            System.out.println("precedence: " + precedence);
            precedence = (3 * 5) + 2;
            System.out.println("precedence: " + precedence);
            precedence = 3 * (5 + 2);
            System.out.println("precedence: " + precedence);
        }
        {
            int i = 1;
            int j = 2;
            int k = 3;

            i = j = k = 14;
            System.out.println("i: " + i);
            //  evaluates right to left:  i = ( j = (k = 14) );
        }
        {
            //  boolean expressions are evaluated left to right
            //  unnecessary evaluations are not executed
            int i = 0;
            if (true || i++ > 0) {
                System.out.println("boolean expression is true, i: " + i);
                //  the increment was not evaluated since the or expression will always be true
                //  from the first term
            }
            System.out.println("after boolean expression evaluation, i: " + i);
        }

        int count = 0;

        //  modify the value of count
        count = count + 1;
        System.out.println("count: " + count);

        //  modify the value of count again
        count++;        //  increments the count
        System.out.println("count: " + count);


        //  only the most recent value is available
        int wheelTach = 12;
        wheelTach = 13;
        wheelTach = 14;
        wheelTach = 15;     //  only the last value assigned will be seen
        System.out.println("wheelTach: " + wheelTach);

        //  boolean test
        System.out.println("wheelTach > 0 : " + (wheelTach > 0));


        //  boolean test
        boolean isReady = true;
        System.out.println("wheelTach > 20 : " + (wheelTach > 20));
        boolean hasCompleted = isReady && wheelTach > 20;
        System.out.println("hasCompleted: " + hasCompleted);

    }

    /**
     * Lesson for basic flow control.
     */
    private void lesson3() {
        int wheelTach = 0;

        /*
            block scope ie. { }
                groups declarations and executions together
                declarations and executions are exclusive
        */
        {
            //  this code will execute just like normal
            int i = 12;
            System.out.println("i: " + i);
        }
        {
            //  this code will execute just like normal,
            //  but the variable i here has nothing to do with the integer i in the block above
            int i = 1;
            System.out.println("i: " + i);
        }

        /*
            flow control
        */

        //  if statement
        System.out.println();
        if (wheelTach > 0)
            System.out.println("wheelTach is greater than 0");


        //  if statement with block
        System.out.println();
        if (wheelTach > 0) {
            System.out.println("wheelTach is greater than 0");
            System.out.println("both statements in the block are executed if wheelTach is greater than 0");
        }


        //  if then else statement
        System.out.println();
        if (wheelTach > 20)
            System.out.println("wheelTach is greater than 20");
        else
            System.out.println("wheelTach is not greater than 20");
        if (wheelTach > 20) {
            //  behavior is identical if in a statement block
            System.out.println("wheelTach is greater than 20");
        } else {
            //  behavior is identical if in a statement block
            System.out.println("wheelTach is not greater than 20");
        }


        //  if then else if then else statement
        System.out.println();
        if (wheelTach > 20)
            System.out.println("wheelTach is greater than 20");
        else if (wheelTach > 30)
            System.out.println("wheelTach is greater than 30");
        else
            System.out.println("wheelTach is not greater than 30");


        //  use switch to avoid too many if else if clauses
        System.out.println();
        switch (wheelTach) {
            case 0:
                System.out.println("wheelTach zero");
                break;
            case 15:
            case 16:
            case 17:
                System.out.println("wheelTach around 16");
                break;
            case 20:
                System.out.println("wheelTach is 20");
                break;
            default:
                System.out.println("wheelTach is not one of the switch values");
                break;
        }


        //  switch statements even work on strings!
        String myName = "bob";
        switch (myName) {
            case "bob":
            case "fred":
                System.out.println("I know: " + myName);
                break;
            default:
                System.out.println("Who is : " + myName + "?");
                break;
        }


        //  while loop
        {
            int i = 0;
            while (i < 5)       //  make sure your loop will terminate
            {
                System.out.println("i in the while loop: " + i);
                i++;    //  if you don't include this, java will loop forever!
            }
        }

        //  for loop
        //  note: it's very typical that software counts starting at zero
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println("i is now: " + i);
        }
        //  note that 5 is never printed!

        //  software always counts from zero!
        //  I'll explain:
        //      when piece of memory is given to the software to use,
        //      a number is use to find the memory
        //      this is something like a street address
        //      this number is called a pointer
        //      values are found at offset locations from the initial location
        //      the first piece of memory to be used has no offset from the pointer
        //      that is: pointer + offset implies the first one is at zero.
        //      programmers naturally count all things starting from zero

    }

    /**
     * Lesson for debugger.
     */
    private void lesson4() {
        /*
            debugger
                a very valuable tool
                will save you hours of time
                study values
                study flow control
                use evaluate expression for value presentation
        */
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            System.out.print(": ");
            int sum = 0;
            for (int j = i; j < 10; j++) {
                sum += j;
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.print("= ");
            System.out.print(sum);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Lesson for arrays.
     */
    private void lesson5() {
        /*

        data structures
            the is only the minimum here
            there are whole college classes on the subject

        arrays
            a list of values that can be found by index number
        */

        int arrayOfIntegers[] = {
                0,
                12,
                23
        };
        for (int i = 0; i < 3; i++) {
            System.out.println("arrayOfIntegers[" + i + "] = " + arrayOfIntegers[i]);
        }
        for (int i = 0; i < arrayOfIntegers.length; i++) {
            System.out.println("arrayOfIntegers[" + i + "] = " + arrayOfIntegers[i]);
        }
        //  NOTE:  arrays always start at index 0
        //  this means that array[array.length] never exists
        //  the last item in the array is: array[array.length-1]
        //  this is why < is used in the for loop above

        //  arrays are a fixed size
        //  their length is defined when declared
        try {
            System.out.println("arrayOfIntegers[12] = " + arrayOfIntegers[12]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("exception thrown: ArrayIndexOutOfBoundsException at index: " + exception.getMessage());
        }


        /*
        arrayList
            an array that will grow as required
        */
        {
            ArrayList<String> arrayListOfStrings = new ArrayList<>();   //  for the moment, you can ignore this syntax
            arrayListOfStrings.add("zero");
            arrayListOfStrings.add("one");
            arrayListOfStrings.add("two");
            arrayListOfStrings.add("three");
            for (int i = 0; i < arrayListOfStrings.size(); i++) {
                System.out.println("arrayListOfStrings.get(" + i + ") = " + arrayListOfStrings.get(i));
            }
            arrayListOfStrings.add("four");
            arrayListOfStrings.add("five");
            for (int i = 4; i < arrayListOfStrings.size(); i++) {
                System.out.println("arrayListOfStrings.get(" + i + ") = " + arrayListOfStrings.get(i));
            }
            System.out.println("arrayListOfStrings.size() = " + arrayListOfStrings.size());
            arrayListOfStrings.add("six");
            System.out.println("arrayListOfStrings.size() = " + arrayListOfStrings.size());
        }

        /*
        sets
            a list with only one entry per value
        sorted sets
            TreeSet


        function concept?
        example of method use within a safe class context
            before exposure to class concept
            */
    }

    /**
     * Lesson for classes.
     */
    private void lesson6() {
        /*
        class
        what is a class?
            a collection of values and operations (methods) that work on those values
            what is a constructor
            this
            primitives vs objects
            what is an object aka class instance
            method aka a class function
            enums
                    strings

        inheritance
            reserved word: super

        interface
            null
        naming conventions

        upper case first char for class names
			lower case first char for class instances
			ALL_CAPS for constants (I don't like this!)
            Math.PI
            public static final int WHEELS_PER_ROBOT = 4;	//	convention
            public static final int wheelsPerRobot = 4;		//	bob's convention
            camelCaseConvention


            utility classes good to know
            String
            Math
            Random
            Sleep

            ide long learning curve

            pass by value vs reference
            null reference

        */
        {
            System.out.println();
            RobotInfo myExample = new RobotInfo();
            myExample.exampleMethod();
            System.out.println(myExample.toString());    //  notice default value
            myExample.setXPosition(654);
            System.out.println(myExample.toString());
            System.out.println("not a good idea: " + myExample.sharingIntegersWithAllIsNotAGoodIdea);
        }
        {
            System.out.println();
            RobotInfo2019 exampleSubClass = new RobotInfo2019();
            exampleSubClass.exampleMethod();      //  super class can use subclass methods
            exampleSubClass.superMethod();
            System.out.println(exampleSubClass.toString());    //  notice default value
            exampleSubClass.setXPosition(345);                 //  sub class can use the super class methods
            System.out.println(exampleSubClass.toString());
            System.out.println();
        }

        System.out.flush();
    }

    /**
     * Sample use of the java logger.
     * <p>
     * The logger can be used to display values to debug code.
     * The logger configuration and be changed dynamically between runs without requiring a re-compile.
     * Note: The config file needs to be specified in the command line VM options with:
     * <code>-Djava.util.logging.config.file=logging.properties</code>.
     */
    private void lesson7() {
        logger.info("logger name: " + logger.getName());
        logger.info("logger java.util.logging.config.file: " + System.getProperty("java.util.logging.config.file"));
        logger.fine("logger fine level here");
        logger.finer("logger finer level here");
        logger.finest("logger finest level here");
    }

    /**
     * This is very simple robotMotionSequencer information class used
     * to describe what a class is and how to use it.
     */
    private static class RobotInfo {
        /**
         * The class constructor is called every time a new instance
         * of the class is make.
         */
        RobotInfo() {
            System.out.println("RobotInfo constructor");
        }

        /**
         * This is an example method to demonstrate the use of sub class methods
         * from a super class
         */
        void exampleMethod() {
            System.out.println("RobotInfo exampleMethod();");
        }

        /**
         * Return the X axis position of the robotMotionSequencer.
         * <p>
         * This method is an example of an accessor get method.
         * This is a standard design pattern that is strongly encouraged.
         * It hides the immediate value behind a method interface.
         * This is very useful when the details of the class implementation
         * change but you don't want to upset all the uses of this method.
         * </p>
         *
         * @return the value of the X position
         */
        public int getXPosition() {
            //  the best way to get private information
            //  this allows the subclass to change over time without complications
            return xPosition;
        }

        /**
         * Set (change) the X axis position of the robotMotionSequencer.
         * <p>
         * This method is an example of an accessor set method.
         * This is a standard design pattern that is strongly encouraged.
         * It hides the immediate value behind a method interface.
         * This is very useful when the details of the class implementation
         * change but you don't want to upset all the uses of this method.
         * </p>
         *
         * @param xPosition the new value for X position
         */
        public void setXPosition(int xPosition) {
            this.xPosition = xPosition;
        }

        /**
         * Get the y position of the robotMotionSequencer.
         */
        public int getYPosition() {
            return yPosition;
        }

        /**
         * Set the y position of the robotMotionSequencer.
         */
        public void setYPosition(int yPosition) {
            this.yPosition = yPosition;
        }

        /**
         * Most all classes override this method to provide a simple printing of the class instance.
         *
         * @return a string representing the instance
         */
        @Override
        public String toString() {
            return "my X position = " + xPosition;
        }

        /**
         * the x position of the robotMotionSequencer
         */
        private int xPosition;  //  not available to any super classes

        /**
         * the y position of the robotMotionSequencer
         */
        private int yPosition;  //  not available to any super classes
        /**
         * An example of a value that can only be shared with sub classes.
         */
        protected int integerToShareWithSuper = 3;
        /**
         * An example of data and be shared with other classes in the package.
         */
        int integerSharedWithThePackage = 14;
        /**
         * This is data that can be shared immediately with all classes
         * and is a practice that should not be used.  Make the value private
         * and use accessors (set and get methods) to control the use of the value.
         */
        public int sharingIntegersWithAllIsNotAGoodIdea = 123;
    }

    /**
     * A robotMotionSequencer information class specific to the 2019 robotMotionSequencer challenge.
     */
    private static class RobotInfo2019 extends RobotInfo {
        /**
         * The class constructor is called every time a new instance
         * of the class is made.
         * <p>Note that the super class constructor
         * is called first.  The sub class adds features to the super class.
         * This implies that the sub class contains a super class instance
         * within it.  It does!
         * </p>
         */
        RobotInfo2019() {
            System.out.println("RobotInfo2019 constructor was called");
        }

        /**
         * This sub class method demonstrates sub class access
         * to super class resources... when allowed.
         */
        void superMethod() {
            System.out.println("RobotInfo2019 superMethod() was called");

            //System.out.println("xPosition: "+xPosition);    //  compile time error

            System.out.println("getxPosition(): " + getXPosition());

            //  not the best style but ok
            System.out.println("integerToShareWithSuper: " + integerToShareWithSuper);

            //  not good practice but it does happen
            System.out.println("integerSharedWithThePackage: " + integerSharedWithThePackage);

            //  don't do this... even if it's legal
            System.out.println("sharingIntegersWithAllIsNotAGoodIdea: " + sharingIntegersWithAllIsNotAGoodIdea);
        }

        /**
         * Most all classes override this method to provide a simple printing of the class instance.
         *
         * @return a string representing the instance
         */
        @Override
        public String toString() {
            return "i'm a 2019 robotMotionSequencer and " + super.toString();
        }
    }

    /**
     * The title to be used for the hello world lessons.
     * <p>
     * This is a class member which means that the data is available to all methods
     * of the class.  The access modifier private means that methods of other classes
     * do not have access to the data.
     * </p>
     */
    private String title = "no title yet";

    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());


}
