package com.user.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class SignUpOutDtoTest {

	@Test
	public void testGetterAndSetter() {
		SignUpOutDto signUpOutDto = new SignUpOutDto();
		assertNull(signUpOutDto.getAddressLine1());
		signUpOutDto.setAddressLine1("address1");
		assertEquals("address1", signUpOutDto.getAddressLine1());
		
		assertNull(signUpOutDto.getBizName());
		signUpOutDto.setBizName("biz name");
		assertEquals("biz name", signUpOutDto.getBizName());
		
		assertNull(signUpOutDto.getIsBusiness());
		signUpOutDto.setIsBusiness(true);
		assertEquals(true, signUpOutDto.getIsBusiness());

		assertNull(signUpOutDto.getCity());
		signUpOutDto.setCity("city");
		assertEquals("city", signUpOutDto.getCity());
		
		assertNull(signUpOutDto.getCountry());
		signUpOutDto.setCountry("country");
		assertEquals("country", signUpOutDto.getCountry());
		
		assertNull(signUpOutDto.getCreatedDt());
		signUpOutDto.setCreatedDt(Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
		assertEquals(Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS), signUpOutDto.getCreatedDt());
		
		assertNull(signUpOutDto.getEmail());
		signUpOutDto.setEmail("email.com");
		assertEquals("email.com", signUpOutDto.getEmail());
		
		assertNull(signUpOutDto.getState());
		signUpOutDto.setState("state");
		assertEquals("state", signUpOutDto.getState());
		
		assertNull(signUpOutDto.getUserId());
		signUpOutDto.setUserId(1L);
		assertEquals(1L, signUpOutDto.getUserId());
		
		assertNull(signUpOutDto.getUserName());
		signUpOutDto.setUserName("userName");
		assertEquals("userName", signUpOutDto.getUserName());
	}
	
	@Test
	public void testEqualAndHashCode() {
		SignUpOutDto signUpOutDto1 = setUpData();
		assertEquals(signUpOutDto1, signUpOutDto1);
		assertEquals(signUpOutDto1.hashCode(), signUpOutDto1.hashCode());
		assertNotEquals(signUpOutDto1, new Object());

		SignUpOutDto signUpOutDto2 = setUpData();
		assertEquals(signUpOutDto1, signUpOutDto2);
		assertEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setAddressLine1("address");
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setBizName("biz1");
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setCity("city1");
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setCountry("country1");
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setCreatedDt(Instant.now());
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setEmail("Email.com");
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setIsBusiness(false);
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setState("state1");
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setUserId(11L);
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto2 = setUpData();
		signUpOutDto2.setUserName("user name");
		assertNotEquals(signUpOutDto1, signUpOutDto2);
		assertNotEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
		
		signUpOutDto1 = new SignUpOutDto();
		signUpOutDto2 = new SignUpOutDto();
		assertEquals(signUpOutDto1, signUpOutDto2);
		assertEquals(signUpOutDto1.hashCode(), signUpOutDto2.hashCode());
	}
	
	@Test
	public void testToString() {
		SignUpOutDto signUpOutDto = new SignUpOutDto();
		String addressLine1 = "address1";
		signUpOutDto.setAddressLine1(addressLine1);
		
		String bizName = "biz name";
		signUpOutDto.setBizName(bizName);
		
		Boolean isBusiness = true;
		signUpOutDto.setIsBusiness(isBusiness);
		
		String city = "city";
		signUpOutDto.setCity(city);
		
		String country = "country";
		signUpOutDto.setCountry(country);
		
		Instant createdDt = Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
		signUpOutDto.setCreatedDt(createdDt);
		
		String email = "email.com";
		signUpOutDto.setEmail(email);
		
		String state = "state";
		signUpOutDto.setState(state);
		
		Long userId = 1L;
		signUpOutDto.setUserId(userId);
		
		String userName = "userName";
		signUpOutDto.setUserName(userName);
		
		assertEquals("SignUpOutDto(userId=" + userId + ", userName=" + userName + ", email=" + email + ", createdDt="
				+ createdDt + ", addressLine1=" + addressLine1 + ", city=" + city + ", state=" + state + ", country="
				+ country + ", isBusiness=" + isBusiness + ", bizName=" + bizName + ")", signUpOutDto.toString());
	}
	public SignUpOutDto setUpData() {
		SignUpOutDto signUpOutDto = new SignUpOutDto();
		signUpOutDto.setAddressLine1("address1");
		signUpOutDto.setBizName("biz name");
		signUpOutDto.setIsBusiness(true);
		signUpOutDto.setCity("city");
		signUpOutDto.setCountry("country");
		signUpOutDto.setCreatedDt(Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
		signUpOutDto.setEmail("email.com");
		signUpOutDto.setState("state");
		signUpOutDto.setUserId(1L);
		signUpOutDto.setUserName("userName");
		return signUpOutDto;
	}
}
