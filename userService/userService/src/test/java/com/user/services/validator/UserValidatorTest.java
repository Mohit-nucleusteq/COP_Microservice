package com.user.services.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.user.services.dbo.UserEntity;
import com.user.services.exception.UserNotFoundException;
import com.user.services.model.UserDetails;
import com.user.services.repository.UserDetailsRepository;
import com.user.services.repository.UserRepository;

public class UserValidatorTest {

	@Mock
	private UserDetailsRepository userDetailsRepository;

	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserValidator userValidator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidateUser() {
		UserDetails userDetails = new UserDetails(1L, "userName", "email", "userRole");
		when(userDetailsRepository.getUserDetailsByEmail("email")).thenReturn(userDetails);
		assertDoesNotThrow(()-> userValidator.validateUser("email"));
	}

	@Test
	public void testUserNotFoundException() {
		when(userDetailsRepository.getUserDetailsByEmail("email")).thenReturn(null);
		try {
			userValidator.validateUser("email");
		} catch(UserNotFoundException userNotFoundException) {
			assertEquals("User with email id email is not present in database", userNotFoundException.getMessage());
		}
	}
	
	@Test
	public void testvalidateUserId() {
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
		when(userRepo.findById(1L)).thenReturn(Optional.of(userEntity));
		assertDoesNotThrow(()-> userValidator.validateUserId(1L));
	}

	@Test
	public void testvalidateUserIdUserNotFoundException() {
		when(userRepo.findById(1L)).thenReturn(Optional.empty());
		try {
			userValidator.validateUserId(1L);
		} catch(UserNotFoundException userNotFoundException) {
			assertEquals("User with id 1 is not present in database", userNotFoundException.getMessage());
		}
	}
}
