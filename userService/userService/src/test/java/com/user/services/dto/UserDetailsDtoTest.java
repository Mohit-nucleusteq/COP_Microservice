package com.user.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class UserDetailsDtoTest {

	@Test
	public void testGetterAndSetter() {
		
		UserDetailsDto userDetailsDto = new UserDetailsDto();

		assertNull(userDetailsDto.getUserId());
		Long userId = 1L;
		userDetailsDto.setUserId(userId);
		assertEquals(userId, userDetailsDto.getUserId());

		assertNull(userDetailsDto.getUserName());
		String userName = "userName";
		userDetailsDto.setUserName(userName);
		assertEquals(userName, userDetailsDto.getUserName());
		
		assertNull(userDetailsDto.getEmail());
		String email = "email";
		userDetailsDto.setEmail(email);
		assertEquals(email, userDetailsDto.getEmail());
		
		assertNull(userDetailsDto.getPassword());
		String password = "password";
		userDetailsDto.setPassword(password);
		assertEquals(password, userDetailsDto.getPassword());
	}
	
	@Test
	public void testEqualAndHashCode() {
		Long userId = 1L;
		String userName = "userName";
		String email = "email";
		String password = "password";
		UserDetailsDto userDetailsDto1 = setUpData(userId, userName, email, password);
		assertEquals(userDetailsDto1, userDetailsDto1);
		assertEquals(userDetailsDto1.hashCode(), userDetailsDto1.hashCode());
		assertNotEquals(userDetailsDto1, new Object());
		
		UserDetailsDto userDetailsDto2 = setUpData(userId, userName, email, password);
		assertEquals(userDetailsDto1, userDetailsDto2);
		assertEquals(userDetailsDto1.hashCode(), userDetailsDto2.hashCode());
		
		userDetailsDto2 = setUpData(2L, userName, email, password);
		assertNotEquals(userDetailsDto1, userDetailsDto2);
		assertNotEquals(userDetailsDto1.hashCode(), userDetailsDto2.hashCode());
		
		userDetailsDto2 = setUpData(userId, userName + " ", email, password);
		assertNotEquals(userDetailsDto1, userDetailsDto2);
		assertNotEquals(userDetailsDto1.hashCode(), userDetailsDto2.hashCode());
		
		userDetailsDto2 = setUpData(userId, userName, email + " ", password);
		assertNotEquals(userDetailsDto1, userDetailsDto2);
		assertNotEquals(userDetailsDto1.hashCode(), userDetailsDto2.hashCode());
		
		userDetailsDto2 = setUpData(userId, userName, email, password + " ");
		assertNotEquals(userDetailsDto1, userDetailsDto2);
		assertNotEquals(userDetailsDto1.hashCode(), userDetailsDto2.hashCode());
		
		userDetailsDto1 = new UserDetailsDto();
		userDetailsDto2 = new UserDetailsDto();
		assertEquals(userDetailsDto1, userDetailsDto2);
		assertEquals(userDetailsDto1.hashCode(), userDetailsDto2.hashCode());
	}

	@Test
	public void testToString() {
		Long userId = 1L;
		String userName = "userName";
		String email = "email";
		String password = "password";
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setEmail(email);
		userDetailsDto.setPassword(password);
		userDetailsDto.setUserId(userId);
		userDetailsDto.setUserName(userName);
		
		assertEquals("UserDetailsDto(userId=" + userId + ", userName=" + userName + ", email=" + email + ", password="
				+ password + ")", userDetailsDto.toString());
	}
	private UserDetailsDto setUpData(Long userId, String userName, String email, String password) {
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setEmail(email);
		userDetailsDto.setPassword(password);
		userDetailsDto.setUserId(userId);
		userDetailsDto.setUserName(userName);
		return userDetailsDto;
	}
}
