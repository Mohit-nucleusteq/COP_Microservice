package com.user.services.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class EncodeDecodeUtils {

    /**
     * Added private constructor to resolve checkstyle errors.
     */
    private EncodeDecodeUtils() {
    }

    /**
     * This method returns decoded string.
     * @param value - string that needs to be decoded
     * @return decoded string
     */
    public static String getDecodedString(final String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }

    /**
     * This method returns encoded string.
     * @param value - string that needs to be encoded
     * @return encoded string
     */
    public static String getEncodedString(final String value) {
        return new String(Base64.getEncoder().encode(value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }
}
