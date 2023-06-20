package com.user.services.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class UserDetailsTest {

	@Test
	public void testGetterAndSetter() {
		
		UserDetails userDetails = new UserDetails(1L, "userName", "email", "userRole");

		Long userId = 1L;
		userDetails.setUserId(userId);
		assertEquals(userId, userDetails.getUserId());

		String userName = "userName";
		userDetails.setUserName(userName);
		assertEquals(userName, userDetails.getUserName());
		
		String email = "email";
		userDetails.setEmail(email);
		assertEquals(email, userDetails.getEmail());
		
		String userRole = "userRole";
		userDetails.setUserRole(userRole);
		assertEquals(userRole, userDetails.getUserRole());
	}
	
	@Test
	public void testEqualAndHashCode() {
		Long userId = 1L;
		String userName = "userName";
		String email = "email";
		String userRole = "userRole";
		UserDetails userDetails1 = setUpData(userId, userName, email, userRole);
		assertEquals(userDetails1, userDetails1);
		assertEquals(userDetails1.hashCode(), userDetails1.hashCode());
		assertNotEquals(userDetails1, new Object());
		
		UserDetails userDetails2 = setUpData(userId, userName, email, userRole);
		assertEquals(userDetails1, userDetails2);
		assertEquals(userDetails1.hashCode(), userDetails2.hashCode());
		
		userDetails2 = setUpData(2L, userName, email, userRole);
		assertNotEquals(userDetails1, userDetails2);
		assertNotEquals(userDetails1.hashCode(), userDetails2.hashCode());
		
		userDetails2 = setUpData(userId, userName + " ", email, userRole);
		assertNotEquals(userDetails1, userDetails2);
		assertNotEquals(userDetails1.hashCode(), userDetails2.hashCode());
		
		userDetails2 = setUpData(userId, userName, email + " ", userRole);
		assertNotEquals(userDetails1, userDetails2);
		assertNotEquals(userDetails1.hashCode(), userDetails2.hashCode());
		
		userDetails2 = setUpData(userId, userName, email, userRole + " ");
		assertNotEquals(userDetails1, userDetails2);
		assertNotEquals(userDetails1.hashCode(), userDetails2.hashCode());
		
		userDetails1 = new UserDetails(1L, "userName", "email", "userRole");
		userDetails2 = new UserDetails(1L, "userName", "email", "userRole");
		assertEquals(userDetails1, userDetails2);
		assertEquals(userDetails1.hashCode(), userDetails2.hashCode());
	}

	@Test
	public void testToString() {
		Long userId = 1L;
		String userName = "userName";
		String email = "email";
		String userRole = "userRole";
		UserDetails userDetails = new UserDetails(1L, "userName", "email", "userRole");
		userDetails.setEmail(email);
		userDetails.setUserRole(userRole);
		userDetails.setUserId(userId);
		userDetails.setUserName(userName);
		
		assertEquals("UserDetails(userId=" + userId + ", userName=" + userName + ", email=" + email + ", userRole="
				+ userRole + ")", userDetails.toString());
	}
	private UserDetails setUpData(Long userId, String userName, String email, String userRole) {
		UserDetails userDetails = new UserDetails(1L, "userName", "email", "userRole");
		userDetails.setEmail(email);
		userDetails.setUserRole(userRole);
		userDetails.setUserId(userId);
		userDetails.setUserName(userName);
		return userDetails;
	}
}
