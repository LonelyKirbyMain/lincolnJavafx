package com.lincolnRobotics.robotControl;


public class TerminationException extends Exception {
	
	/**
     * Thrown when the control session has been terminated early
     * and the autonomous control period has ended.
     * 
     * @param message  message sent to user on early termination
     */
    public TerminationException( String message ) {
        super(message);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 197497329086422305L;

}
