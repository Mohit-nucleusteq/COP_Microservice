package com.user.services.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnauthorizedExceptionTest {

	@Test
	public void testException() {
		UnauthorizedException unauthorizedException = new UnauthorizedException("Unauthorise exception");
		assertEquals("Unauthorise exception", unauthorizedException.getMessage());
	}
}
