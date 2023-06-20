package com.user.services.exception;

/**
 * This class contains UnauthorizedException exception which return 401 status code.
 */
public class UnauthorizedException extends Exception {

    /**
     * Serial Version Id.
     */
    private static final long serialVersionUID = 3555917544093150908L;

    /**
     * @param message contains the message due to which exception occured
     * */
    public UnauthorizedException(final String message) {
        super(message);
      }
}
