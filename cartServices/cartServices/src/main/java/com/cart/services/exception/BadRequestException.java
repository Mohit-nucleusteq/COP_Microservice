package com.cart.services.exception;

/**
 * This class contains bad request exception which return 400 status code.
 */
public class BadRequestException extends Exception {

    /**
     * Serial Version Id.
     */
    private static final long serialVersionUID = 3555917544093150908L;

    /**
     * @param message contains the message due to which exception occured
     * */
    public BadRequestException(final String message) {
        super(message);
      }
}
