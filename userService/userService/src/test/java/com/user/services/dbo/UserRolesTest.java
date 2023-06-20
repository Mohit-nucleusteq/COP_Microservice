package com.user.services.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class UserRolesTest {

	@Test
	public void testGetterAndSetter() {

		UserRoles userRoles = new UserRoles();
		assertNull(userRoles.getId());
		Integer id = 1;
		userRoles.setId(id);
		assertEquals(id, userRoles.getId());

		assertNull(userRoles.getUserId());
		Long userId = 1L;
		userRoles.setUserId(userId);
		assertEquals(userId, userRoles.getUserId());

		assertNull(userRoles.getUserEmail());
		String userEmail = "email";
		userRoles.setUserEmail(userEmail);
		assertEquals(userEmail, userRoles.getUserEmail());

		assertNull(userRoles.getUserRole());
		String userRole = "role";
		userRoles.setUserRole(userRole);
		assertEquals(userRole, userRoles.getUserRole());

	}

	@Test
	public void testEqualAndHashCode() {
		Integer id = 1;
		Long userId = 1L;
		String userEmail = "email";
		String userRole = "role";
		UserRoles userRoles1 = setUpUserRoleData(id, userId, userEmail, userRole);
		assertEquals(userRoles1, userRoles1);
		assertEquals(userRoles1.hashCode(), userRoles1.hashCode());

		UserRoles userRoles2 = setUpUserRoleData(id, userId, userEmail, userRole);
		assertEquals(userRoles1, userRoles2);
		assertEquals(userRoles1.hashCode(), userRoles2.hashCode());
		assertNotEquals(userRoles1, new Object());
		
		userRoles2 = setUpUserRoleData(2, userId, userEmail, userRole);
		assertNotEquals(userRoles1, userRoles2);
		assertNotEquals(userRoles1.hashCode(), userRoles2.hashCode());

		userRoles2 = setUpUserRoleData(id, 2L, userEmail, userRole);
		assertNotEquals(userRoles1, userRoles2);
		assertNotEquals(userRoles1.hashCode(), userRoles2.hashCode());
		
		userRoles2 = setUpUserRoleData(id, userId, userEmail + " ", userRole);
		assertNotEquals(userRoles1, userRoles2);
		assertNotEquals(userRoles1.hashCode(), userRoles2.hashCode());
		
		userRoles2 = setUpUserRoleData(id, userId, userEmail, userRole + " ");
		assertNotEquals(userRoles1, userRoles2);
		assertNotEquals(userRoles1.hashCode(), userRoles2.hashCode());
		
		userRoles1 = new UserRoles();
		userRoles2 = new UserRoles();
		assertEquals(userRoles1, userRoles2);
		assertEquals(userRoles1.hashCode(), userRoles2.hashCode());
	}

	@Test
	public void testToString() {
		UserRoles userRoles = new UserRoles();
		Integer id = 1;
		Long userId = 1L;
		String userEmail = "email";
		String userRole = "role";
		userRoles.setId(id);
		userRoles.setUserId(userId);
		userRoles.setUserEmail(userEmail);
		userRoles.setUserRole(userRole);
		assertEquals("UserRoles(id=" + id + ", userId=" + userId + ", userEmail=" + userEmail + ", userRole=" + userRole
				+ ")", userRoles.toString());
	}
	private UserRoles setUpUserRoleData(Integer id, Long userId, String userEmail, String userRole) {
		UserRoles userRoles = new UserRoles();
		userRoles.setId(id);
		userRoles.setUserId(userId);
		userRoles.setUserEmail(userEmail);
		userRoles.setUserRole(userRole);
		return userRoles;
	}
}
