package com.user.services.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserNotFoundExceptionTest {

	@Test
	public void testException() {
		UserNotFoundException userNotFoundException = new UserNotFoundException("userNotFound exception");
		assertEquals("userNotFound exception", userNotFoundException.getMessage());
	}
}
