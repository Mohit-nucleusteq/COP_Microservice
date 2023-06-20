package com.order.service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NotFoundExceptionTest {

	@Test
	public void testBadRequestException() {
		String errorMessage = "Not found";
		NotFoundException notFoundException = new NotFoundException(errorMessage);
		assertEquals(errorMessage, notFoundException.getMessage());
	}
}
