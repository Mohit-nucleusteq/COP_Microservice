package com.product.services.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    @Test
    public void testGetterAndSetter() {

        Instant timestamp = Instant.now();
        String status = "status";
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("errorMessages");
        ErrorResponse errorResponse = new ErrorResponse(timestamp, status, errorMessages);
        
        Instant timeStamp1 = Instant.now();
        errorResponse.setTimestamp(timeStamp1);
        assertEquals(timeStamp1, errorResponse.getTimestamp());

        String status1 = "status1";
        errorResponse.setStatus(status1);
        assertEquals(status1, errorResponse.getStatus());

        errorMessages.add("errorMessages1");
        errorResponse.setErrorMessages(errorMessages);
        assertEquals(errorMessages, errorResponse.getErrorMessages());
    }

    @Test
    public void testToString() {
        
        Instant timestamp = Instant.now();
        String status = "status";
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("errorMessages");
        ErrorResponse errorResponse2 = new ErrorResponse(timestamp, status, errorMessages);
        
        assertEquals("ErrorResponse(timestamp="+timestamp+", status=status, errorMessages=[errorMessages])",
                errorResponse2.toString());
    }

    @Test
    public void testEqualsAndHashcode() {

        Instant timestamp = Instant.now();
        String status = "status";

        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("errorMessages");

        ErrorResponse errorResponse1 = new ErrorResponse(timestamp, status, errorMessages);
        assertEquals(errorResponse1, errorResponse1);
        assertEquals(errorResponse1.hashCode(), errorResponse1.hashCode());

        assertNotEquals(errorResponse1, new Object());

        ErrorResponse errorResponse2 = new ErrorResponse(timestamp, status, errorMessages);
        assertEquals(errorResponse1, errorResponse2);
        assertEquals(errorResponse1.hashCode(), errorResponse2.hashCode());

        errorResponse2 = new ErrorResponse(Instant.MIN, status, errorMessages);
        assertNotEquals(errorResponse1, errorResponse2);
        assertNotEquals(errorResponse1.hashCode(), errorResponse2.hashCode());

        errorResponse2 = new ErrorResponse(timestamp, status+" ", errorMessages);
        assertNotEquals(errorResponse1, errorResponse2);
        assertNotEquals(errorResponse1.hashCode(), errorResponse2.hashCode());

        List<String> errorMessages2 = new ArrayList<String>();
        errorMessages2.add("errorMessages 2");
        errorResponse2 = new ErrorResponse(timestamp, status, errorMessages2);
        assertNotEquals(errorResponse1, errorResponse2);
        assertNotEquals(errorResponse1.hashCode(), errorResponse2.hashCode());

    }
}
