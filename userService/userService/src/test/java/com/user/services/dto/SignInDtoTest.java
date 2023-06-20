package com.user.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class SignInDtoTest {

	@Test
	public void testGetterAndSetter() {
		SignInDto signInDto = new SignInDto();
		
		assertNull(signInDto.getEmail());
		signInDto.setEmail("email");
		assertEquals("email", signInDto.getEmail());
		
		assertNull(signInDto.getPassword());
		signInDto.setPassword("pass");
		assertEquals("pass", signInDto.getPassword());
	}
	
	@Test
	public void testEqualAndHashCode() {
		SignInDto signInDto1 = new SignInDto();

		signInDto1.setEmail("email");
		signInDto1.setPassword("pass");

		assertEquals(signInDto1, signInDto1);
		assertEquals(signInDto1.hashCode(), signInDto1.hashCode());
		assertNotEquals(signInDto1, new Object());
		
		SignInDto signInDto2 = new SignInDto();

		signInDto2.setEmail("email");
		signInDto2.setPassword("pass");
		
		assertEquals(signInDto1, signInDto2);
		assertEquals(signInDto1.hashCode(), signInDto2.hashCode());
		
		signInDto2.setEmail("email.com");
		assertNotEquals(signInDto1, signInDto2);
		assertNotEquals(signInDto1.hashCode(), signInDto2.hashCode());
		
		signInDto2.setPassword("password");
		assertNotEquals(signInDto1, signInDto2);
		assertNotEquals(signInDto1.hashCode(), signInDto2.hashCode());
		
		signInDto1 = new SignInDto();
		signInDto2 = new SignInDto();
		assertEquals(signInDto1, signInDto2);
		assertEquals(signInDto1.hashCode(), signInDto2.hashCode());
	}
	
	@Test
	public void testToString() {
		SignInDto signInDto1 = new SignInDto();

		signInDto1.setEmail("email");
		signInDto1.setPassword("pass");
		assertEquals("SignInDto(email=email, password=pass)", signInDto1.toString());
	}
}
