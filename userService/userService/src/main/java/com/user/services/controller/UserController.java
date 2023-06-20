package com.user.services.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.user.services.dbo.UserEntity;
import com.user.services.dto.GenerateTokenOutDto;
import com.user.services.dto.SignInDto;
import com.user.services.dto.SignUpOutDto;
import com.user.services.dto.UserDetailsDto;
import com.user.services.exception.UnauthorizedException;
import com.user.services.exception.UserNotFoundException;
import com.user.services.model.UserDetails;
import com.user.services.service.UserService;
import com.user.services.validator.UserValidator;

@RestController
public class UserController {

	/**
	 * The UserService object.
	 */
	@Autowired
	private UserService userService;

	/**
	 * The userValidator object.
	 */
	@Autowired
	private UserValidator userValidator;

	/**
	 * The Logger object.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/** This method is used for user signup.
	 * @param userEntity - userDetails for signup.
	 * @return - return the response details for signed-up user.
	 * @throws Exception 
	 */
	@PostMapping("/signUp")
	public SignUpOutDto userSignUp(@RequestBody UserEntity userEntity) throws Exception {
		LOGGER.info("Request received to user sign-up for {}", userEntity.toString());
		SignUpOutDto userDetails = userService.userSignUp(userEntity);
		LOGGER.info("User {} is registered successfully", userDetails.toString());
		return userDetails;
	}

	@PutMapping("/updateDetails")
	public UserEntity updateUserDetails(@RequestBody UserDetailsDto userDetailsDto) throws UserNotFoundException {
		LOGGER.info("Request recieved to update the user details for {}", userDetailsDto.toString());
		userValidator.validateUser(userDetailsDto.getEmail());
		UserEntity  updatedDetails = userService.updateUserDetails(userDetailsDto);
		LOGGER.info("Successfully updated user details {}", updatedDetails);
		return updatedDetails;
	}

	@PostMapping("/fetchUserDetails")
	public SignUpOutDto fetchUserDetails(@RequestBody SignInDto signInDto) throws UserNotFoundException, UnauthorizedException {
		LOGGER.info("Request received to fetch user details for {}", signInDto.toString());
		userValidator.validateUser(signInDto.getEmail());
		SignUpOutDto userDetails = userService.fetchUserDetails(signInDto);
		LOGGER.info("Successfully fetched user details for {}", signInDto.toString());
		return userDetails;
	}

	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId) throws UserNotFoundException {
		LOGGER.info("Request received to delete the user for id {}", userId);
		userValidator.validateUserId(userId);
		String message = userService.deleteUser(userId);
		return message;
	}

//	@PostMapping("/generateUserToken")
//	public GenerateTokenOutDto userSign(@RequestBody SignInDto signInDto) throws UserNotFoundException, UnauthorizedException {
//		LOGGER.info("Request received to generate Jwt token for {}", signInDto.toString());
//		userValidator.validateUser(signInDto.getEmail());
//		GenerateTokenOutDto token = userService.generateAccessToken(signInDto);
//		LOGGER.info("Successfully generated jwt token for {}", signInDto.toString());
//		return token;
//	}
//
//	@GetMapping("/validateToken")
//	public UserDetails userDetails(@RequestHeader final Map<String, String> headers) throws UnauthorizedException {
//		LOGGER.info("Request received to validate the header token");
//		UserDetails userDetails = userService.validateAndAuthenticateToken(headers.get("authorization"));
//		LOGGER.info("Successfully reterieved data {} from token", userDetails);
//		return userDetails;
//	}
}
