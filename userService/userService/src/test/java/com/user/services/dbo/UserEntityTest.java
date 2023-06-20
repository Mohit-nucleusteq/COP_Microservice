package com.user.services.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class UserEntityTest {

	@Test
	public void testGetterAndSetter() {

		UserEntity userEntity = new UserEntity();

		assertNull(userEntity.getUserId());
		Long userId = 1L;
		userEntity.setUserId(userId);
		assertEquals(userId, userEntity.getUserId());

		assertNull(userEntity.getUserName());
		String userName = "userName";
		userEntity.setUserName(userName);
		assertEquals(userName, userEntity.getUserName());

		assertNull(userEntity.getEmail());
		String email = "email";
		userEntity.setEmail(email);
		assertEquals(email, userEntity.getEmail());

		assertNull(userEntity.getPassword());
		String password = "password";
		userEntity.setPassword(password);
		assertEquals(password, userEntity.getPassword());

		assertNull(userEntity.getCreatedDt());
		Instant createdDt = Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
		userEntity.setCreatedDt(createdDt);
		assertEquals(createdDt, userEntity.getCreatedDt());

		assertNull(userEntity.getAddressLine1());
		String addressLine1 = "addressLine1";
		userEntity.setAddressLine1(addressLine1);
		assertEquals(addressLine1, userEntity.getAddressLine1());

		assertNull(userEntity.getCity());
		String city = "city";
		userEntity.setCity(city);
		assertEquals(city, userEntity.getCity());

		assertNull(userEntity.getState());
		String state = "state";
		userEntity.setState(state);
		assertEquals(state, userEntity.getState());

		assertNull(userEntity.getCountry());
		String country = "country";
		userEntity.setCountry(country);
		assertEquals(country, userEntity.getCountry());

		assertNull(userEntity.getIsBusiness());
		Boolean isBusiness = true;
		userEntity.setIsBusiness(isBusiness);
		assertEquals(isBusiness, userEntity.getIsBusiness());

		assertNull(userEntity.getBizName());
		String bizName = "bizName";
		userEntity.setBizName(bizName);
		assertEquals(bizName, userEntity.getBizName());
	}

	@Test
	public void testEqualAndHashCode() {
		Long userId = 1L;
		String userName = "userName";
		String bizName = "bizName";
		Boolean isBusiness = true;
		String country = "country";
		String state = "state";
		String addressLine1 = "addressLine1";
		String email = "email";
		String password = "password";
		String city = "city";
		Instant createdDt = Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
		UserEntity userEntity1 = setUpdata(userId, userName, bizName, isBusiness, country, state, addressLine1, email,
				password, createdDt, city);

		assertEquals(userEntity1, userEntity1);
		assertEquals(userEntity1.hashCode(), userEntity1.hashCode());
		assertNotEquals(userEntity1, new Object());

		UserEntity userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country, state, addressLine1, email,
				password, createdDt, city);
		assertEquals(userEntity1, userEntity2);
		assertEquals(userEntity1.hashCode(), userEntity2.hashCode());

		userEntity2 = setUpdata(2L, userName, bizName, isBusiness, country, state, addressLine1, email,
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());

		userEntity2 = setUpdata(userId, userName + " ", bizName, isBusiness, country, state, addressLine1, email,
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());

		userEntity2 = setUpdata(userId, userName, bizName + " ", isBusiness, country, state, addressLine1, email,
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, false, country, state, addressLine1, email,
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country + " ", state, addressLine1, email,
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country, state + " ", addressLine1, email,
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country, state, addressLine1 + " ", email,
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country, state, addressLine1, email + " ",
				password, createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country, state, addressLine1, email,
				password + " ", createdDt, city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country, state, addressLine1, email,
				password, createdDt.minusSeconds(100), city);
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity2 = setUpdata(userId, userName, bizName, isBusiness, country, state, addressLine1, email,
				password, createdDt, city + " ");
		assertNotEquals(userEntity1, userEntity2);
		assertNotEquals(userEntity1.hashCode(), userEntity2.hashCode());
		
		userEntity1 = new UserEntity();
		userEntity2 = new UserEntity();
		assertEquals(userEntity1, userEntity1);
		assertEquals(userEntity1.hashCode(), userEntity1.hashCode());

	}

	@Test
	public void testToString() {
		UserEntity userEntity = new UserEntity();
		Long userId = 1L;
		String userName = "userName";
		String bizName = "bizName";
		Boolean isBusiness = true;
		String country = "country";
		String state = "state";
		String addressLine1 = "addressLine1";
		String email = "email";
		String password = "password";
		String city = "city";
		Instant createdDt = Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
		userEntity.setAddressLine1(addressLine1);
		userEntity.setBizName(bizName);
		userEntity.setIsBusiness(isBusiness);
		userEntity.setCity(city);
		userEntity.setCountry(country);
		userEntity.setCreatedDt(createdDt);
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		userEntity.setState(state);
		userEntity.setUserName(userName);
		userEntity.setUserId(userId);
		assertEquals("UserEntity(userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", createdDt=" + createdDt + ", addressLine1=" + addressLine1 + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", isBusiness=" + isBusiness + ", bizName=" + bizName + ")", userEntity.toString());
	}

	public UserEntity setUpdata(Long userId, String userName, String bizName, Boolean isBusiness, String country,
			String state, String addressLine1, String email, String password, Instant createdDt, String city) {
		UserEntity userEntity = new UserEntity();
		userEntity.setAddressLine1(addressLine1);
		userEntity.setBizName(bizName);
		userEntity.setIsBusiness(isBusiness);
		userEntity.setCity(city);
		userEntity.setCountry(country);
		userEntity.setCreatedDt(createdDt);
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		userEntity.setState(state);
		userEntity.setUserName(userName);
		userEntity.setUserId(userId);
		return userEntity;
	}
}
