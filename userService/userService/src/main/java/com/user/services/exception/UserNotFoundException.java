package com.user.services.exception;

/**
 * This class contains UserNotFoundException exception which return 404
 * Not_Found HttpStatus.
 *
 */

public class UserNotFoundException extends Exception {

    /**
     * Serial Version Id.
     */
    private static final long serialVersionUID = 3132190833373606031L;

    /**
     * @param message contains the message due to which exception occured
     * */
    public UserNotFoundException(final String message) {
        super(message);
      }
}
