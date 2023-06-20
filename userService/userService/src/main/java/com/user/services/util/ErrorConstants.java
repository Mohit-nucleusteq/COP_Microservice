package com.user.services.util;

/**
 * ErrorConstants is a final class to store variables related to error constant.
 *
 */
public final class ErrorConstants {

    private ErrorConstants() {

    }

    /**
     * Invalid Token User.
     */
    public static final String INVALID_TOKEN_USER = "Invalid token of user with email id %s ";

    /**
     * Token is Invalid.
     */
    public static final String INVALID_TOKEN = "The token is invalid";

    /**
     * Token is Invalid.
     */
    public static final String EXPIRED_TOKEN = "The token is expired";

    /**
     * Token must not be null.
     */
    public static final String TOKEN_MUST_NOT_BE_NULL_OR_START_WITH_BEARER = "Token must not be null/must start with bearer.";

    /**
     * The Malformed exception message.
     */
    public static final String MALFORMEND_EXCEPTION_MESSAGE = "JWT strings must contain exactly 2 period characters";

    /**
     * The Unsupported exception message.
     */
    public static final String UNSUPPORTED_EXCEPTION_MESSAGE = "Unsigned Claims JWTs are not supported";

    /**
     * The token not belong to the user constant.
     */
    public static final String PROVIDED_TOKEN_INVALID = "Provided token does not belong to the email id %s";

}
