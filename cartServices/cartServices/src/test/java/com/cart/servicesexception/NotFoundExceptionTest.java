package com.cart.servicesexception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cart.services.exception.NotFoundException;

public class NotFoundExceptionTest {

	@Test
	public void testBadRequestException() {
		String errorMessage = "Not found";
		NotFoundException notFoundException = new NotFoundException(errorMessage);
		assertEquals(errorMessage, notFoundException.getMessage());
	}
}
