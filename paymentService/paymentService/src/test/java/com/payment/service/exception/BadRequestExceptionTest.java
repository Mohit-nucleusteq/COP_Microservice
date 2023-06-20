package com.payment.service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BadRequestExceptionTest {

	@Test
	public void testBadRequestException() {
		String errorMessage = "Bad request";
		BadRequestException badRequestException = new BadRequestException(errorMessage);
		assertEquals(errorMessage, badRequestException.getMessage());
	}
}
