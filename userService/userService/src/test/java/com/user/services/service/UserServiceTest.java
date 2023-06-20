package com.user.services.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.user.services.dbo.UserEntity;
import com.user.services.dto.SignInDto;
import com.user.services.dto.SignUpOutDto;
import com.user.services.dto.UserDetailsDto;
import com.user.services.exception.UnauthorizedException;
import com.user.services.repository.UserRepository;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUserSignUp() throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setAddressLine1("address1");
		userEntity.setBizName("biz name");
		userEntity.setIsBusiness(true);
		userEntity.setCity("city");
		userEntity.setCountry("country");
		userEntity.setCreatedDt(Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
		userEntity.setEmail("email.com");
		userEntity.setPassword("pass");
		userEntity.setState("state");
		userEntity.setUserName("userName");
		userEntity.setUserId(1L);

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

		when(userRepository.save(userEntity)).thenReturn(userEntity);
		SignUpOutDto actualSignUpOutDto = userService.userSignUp(userEntity);
		assertEquals(signUpOutDto, actualSignUpOutDto);
	}

	@Test
	public void testUpdateUserDetails() {
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
		when(userRepository.findById(userDetailsDto.getUserId())).thenReturn(Optional.of(userEntity));
		UserEntity response = userService.updateUserDetails(userDetailsDto);
		assertEquals(userEntity, response);
	}

	@Test
	public void testdeleteUser() {
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
		when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
		doNothing().when(userRepository).delete(userEntity);
		String expectedMessage = String.format("User with id %s has been deleted successfully", 1L);
		String actualMessage = userService.deleteUser(1L);
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testFetchUserdetails() throws UnauthorizedException {
		SignInDto signInDto = new SignInDto();
		signInDto.setEmail("email");
		signInDto.setPassword("pass");

		UserEntity userEntity = new UserEntity();
		userEntity.setAddressLine1("address1");
		userEntity.setBizName("biz name");
		userEntity.setIsBusiness(true);
		userEntity.setCity("city");
		userEntity.setCountry("country");
		userEntity.setCreatedDt(Instant.now());
		userEntity.setEmail("Email");
		userEntity.setPassword("cGFzcw==");
		userEntity.setState("state");
		userEntity.setUserId(1L);
		userEntity.setUserName("username");
		SignUpOutDto signUpOutDto = new SignUpOutDto();
		signUpOutDto.setAddressLine1("address1");
		signUpOutDto.setBizName("biz name");
		signUpOutDto.setIsBusiness(true);
		signUpOutDto.setCity("city");
		signUpOutDto.setCountry("country");
		signUpOutDto.setCreatedDt(Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
		signUpOutDto.setEmail("Email");
		signUpOutDto.setState("state");
		signUpOutDto.setUserId(1L);
		signUpOutDto.setUserName("username");
		when(userRepository.findByEmail(signInDto.getEmail())).thenReturn(Optional.of(userEntity));
		SignUpOutDto actualSignUpOutDto = userService.fetchUserDetails(signInDto);
		assertEquals(signUpOutDto, actualSignUpOutDto);
	}

	@Test
	public void testUnAuthorizedAccess() throws UnauthorizedException {
		SignInDto signInDto = new SignInDto();
		signInDto.setEmail("email");
		signInDto.setPassword("pass");

		UserEntity userEntity = new UserEntity();
		userEntity.setAddressLine1("address1");
		userEntity.setBizName("biz name");
		userEntity.setIsBusiness(true);
		userEntity.setCity("city");
		userEntity.setCountry("country");
		userEntity.setCreatedDt(Instant.now());
		userEntity.setEmail("Email");
		userEntity.setPassword("pass");
		userEntity.setState("state");
		userEntity.setUserId(1L);
		userEntity.setUserName("username");
		when(userRepository.findByEmail(signInDto.getEmail())).thenReturn(Optional.of(userEntity));
		UnauthorizedException unauthorizedException = new UnauthorizedException("Unauthrozied access");
		try {
			userService.fetchUserDetails(signInDto);
			fail("Fails to throw exception");
		} catch(UnauthorizedException ex) {
			assertEquals(unauthorizedException.getMessage(), ex.getMessage());
		}
	}
}
