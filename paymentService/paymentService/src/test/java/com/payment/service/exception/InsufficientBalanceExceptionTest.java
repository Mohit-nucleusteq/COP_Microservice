package com.payment.service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InsufficientBalanceExceptionTest {

	@Test
	public void testBadRequestException() {
		String errorMessage = "Insufficient balance";
		NotFoundException notFoundException = new NotFoundException(errorMessage);
		assertEquals(errorMessage, notFoundException.getMessage());
	}
}
