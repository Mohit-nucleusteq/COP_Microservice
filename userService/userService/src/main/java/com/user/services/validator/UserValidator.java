package com.user.services.validator;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.services.dbo.UserEntity;
import com.user.services.exception.UserNotFoundException;
import com.user.services.model.UserDetails;
import com.user.services.repository.UserDetailsRepository;
import com.user.services.repository.UserRepository;

@Component
public class UserValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

	/**
	 * The userRepository object.
	 */
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private UserRepository userRepo;

	public void validateUser(String email) throws UserNotFoundException {
		UserDetails userDetails = userDetailsRepository.getUserDetailsByEmail(email);
		if(Objects.isNull(userDetails)) {
			LOGGER.error("User with email id {} not found in database", email);
			throw new UserNotFoundException(String.format("User with email id %s is not present in database", email));
		}
		
	}

	public void validateUserId(Long userId) throws UserNotFoundException {
		Optional<UserEntity> userEntity = userRepo.findById(userId);
		if(!userEntity.isPresent()) {
			throw new UserNotFoundException(String.format("User with id %s is not present in database", userId));
		}
		
	}
}
