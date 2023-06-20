package com.user.services.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.user.services.dbo.UserEntity;
import com.user.services.dto.SignInDto;
import com.user.services.dto.SignUpOutDto;
import com.user.services.dto.UserDetailsDto;
import com.user.services.service.UserService;
import com.user.services.validator.UserValidator;

public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	private MockMvc mockMvc;

	@Mock
	private UserValidator userValidator;

	private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testUserSignUp() throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setAddressLine1("address1");
		userEntity.setBizName("biz name");
		userEntity.setIsBusiness(true);
		userEntity.setCity("city");
		userEntity.setCountry("country");
		userEntity.setCreatedDt(Instant.now());
		userEntity.setEmail("email.com");
		userEntity.setPassword("pass");
		userEntity.setState("state");
		userEntity.setUserId(1L);
		userEntity.setUserName("userName");

		SignUpOutDto signUpOutDto = new SignUpOutDto();
		signUpOutDto.setAddressLine1("address1");
		signUpOutDto.setBizName("biz name");
		signUpOutDto.setIsBusiness(true);
		signUpOutDto.setCity("city");
		signUpOutDto.setCountry("country");
		signUpOutDto.setCreatedDt(Instant.now());
		signUpOutDto.setEmail("email.com");
		signUpOutDto.setState("state");
		signUpOutDto.setUserId(1L);
		signUpOutDto.setUserName("userName");
		OBJECTMAPPPER.registerModule(new JavaTimeModule());
		when(userService.userSignUp(userEntity)).thenReturn(signUpOutDto);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/signUp")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(OBJECTMAPPPER.writeValueAsBytes(userEntity)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(signUpOutDto), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testUpdateUserDetails() throws Exception {

		UserDetailsDto userDetailsDto = new UserDetailsDto();
		userDetailsDto.setEmail("Email");
		userDetailsDto.setPassword("Password");
		userDetailsDto.setUserName("username");
		userDetailsDto.setUserId(1L);

		UserEntity userEntity = new UserEntity();
		userEntity.setAddressLine1("address1");
		userEntity.setBizName("biz name");
		userEntity.setIsBusiness(true);
		userEntity.setCity("city");
		userEntity.setCountry("country");
		userEntity.setCreatedDt(Instant.now());
		userEntity.setEmail("Email");
		userEntity.setPassword("Password");
		userEntity.setState("state");
		userEntity.setUserId(1L);
		userEntity.setUserName("username");

		OBJECTMAPPPER.registerModule(new JavaTimeModule());
		doNothing().when(userValidator).validateUser(userDetailsDto.getEmail());
		when(userService.updateUserDetails(userDetailsDto)).thenReturn(userEntity);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateDetails")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(OBJECTMAPPPER.writeValueAsBytes(userEntity)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(userEntity), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testFetchUserDetails() throws Exception {

		SignInDto signInDto = new SignInDto();
		signInDto.setEmail("email");
		signInDto.setPassword("password");

		SignUpOutDto signUpOutDto = new SignUpOutDto();
		signUpOutDto.setAddressLine1("address1");
		signUpOutDto.setBizName("biz name");
		signUpOutDto.setIsBusiness(true);
		signUpOutDto.setCity("city");
		signUpOutDto.setCountry("country");
		signUpOutDto.setCreatedDt(Instant.now());
		signUpOutDto.setEmail("email");
		signUpOutDto.setState("state");
		signUpOutDto.setUserId(1L);
		signUpOutDto.setUserName("userName");

		OBJECTMAPPPER.registerModule(new JavaTimeModule());
		doNothing().when(userValidator).validateUser(signInDto.getEmail());
		when(userService.fetchUserDetails(signInDto)).thenReturn(signUpOutDto);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/fetchUserDetails")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(OBJECTMAPPPER.writeValueAsBytes(signInDto)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(signUpOutDto), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testDeleteUserDetails() throws Exception {


		OBJECTMAPPPER.registerModule(new JavaTimeModule());
		doNothing().when(userValidator).validateUserId(1L);
		String expectedMessage = String.format("User with id %s has been deleted successfully", 1L);
		when(userService.deleteUser(1L)).thenReturn(expectedMessage);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteUser/1")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(expectedMessage,
				mvcResult.getResponse().getContentAsString());
	}
}
