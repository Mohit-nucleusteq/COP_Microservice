package com.user.services.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.services.dbo.UserEntity;
import com.user.services.dto.SignInDto;
import com.user.services.dto.SignUpOutDto;
import com.user.services.dto.UserDetailsDto;
import com.user.services.exception.UnauthorizedException;
import com.user.services.repository.UserRepository;
import com.user.services.util.EncodeDecodeUtils;

/**
 * This class is used to handle all the business logic related to users.
 *
 */
@Service
public class UserService {

	/**
	 * The UserRepository object.
	 */
	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private TokenService tokenService;

	/**
	 * This method is used for user signup.
	 * 
	 * @param userEntity - userDetails for signup.
	 * @return - return the response details for signed-up user.
	 * @throws Exception
	 */
	public SignUpOutDto userSignUp(UserEntity userEntity) throws Exception {
		userEntity.setCreatedDt(Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
		String encodedPassword = EncodeDecodeUtils.getEncodedString(userEntity.getPassword());
		userEntity.setPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		SignUpOutDto signUpOutDto = new SignUpOutDto();
		signUpOutDto.setAddressLine1(savedUserEntity.getAddressLine1());
		signUpOutDto.setBizName(savedUserEntity.getBizName());
		signUpOutDto.setIsBusiness(savedUserEntity.getIsBusiness());
		signUpOutDto.setCity(savedUserEntity.getCity());
		signUpOutDto.setCountry(savedUserEntity.getCountry());
		signUpOutDto.setCreatedDt(savedUserEntity.getCreatedDt());
		signUpOutDto.setEmail(savedUserEntity.getEmail());
		signUpOutDto.setState(savedUserEntity.getState());
		signUpOutDto.setUserId(savedUserEntity.getUserId());
		signUpOutDto.setUserName(savedUserEntity.getUserName());
		return signUpOutDto;
	}

	
//	public GenerateTokenOutDto generateAccessToken(final SignInDto signInDto) throws UnauthorizedException {
//		UserEntity userDetails = userRepository.findByEmail(signInDto.getEmail()).get();
//		String decodedPassword = EncodeDecodeUtils.getDecodedString(userDetails.getPassword());
//		if(!decodedPassword.equals(signInDto.getPassword())) {
//			throw new UnauthorizedException("Unauthrozied access");
//		}
//		GenerateTokenOutDto generateTokenOutDto = tokenService.generateAccessToken(signInDto.getEmail());
//		return generateTokenOutDto;
//	}


//	public UserDetails validateAndAuthenticateToken(String authToken) throws UnauthorizedException {
//		UserDetails userDetails = tokenService.verifyToken(authToken);
//		return userDetails;
//	}


	public UserEntity updateUserDetails(UserDetailsDto userDetailsDto) {
		UserEntity existingUserDetails = userRepository.findById(userDetailsDto.getUserId()).get();
		existingUserDetails.setUserName(userDetailsDto.getUserName());
		existingUserDetails.setEmail(userDetailsDto.getEmail());
		String pwd = EncodeDecodeUtils.getEncodedString(userDetailsDto.getPassword());
		existingUserDetails.setPassword(pwd);
		return existingUserDetails;
	}


	public String deleteUser(Long userId) {
		UserEntity userEntity = userRepository.findById(userId).get();
		userRepository.delete(userEntity);
		return String.format("User with id %s has been deleted successfully", userId);
	}


	public SignUpOutDto fetchUserDetails(SignInDto signInDto) throws UnauthorizedException {
		UserEntity userDetails = userRepository.findByEmail(signInDto.getEmail()).get();
		String decodedPassword = EncodeDecodeUtils.getDecodedString(userDetails.getPassword());
		if(!decodedPassword.equals(signInDto.getPassword())) {
			throw new UnauthorizedException("Unauthrozied access");
		}
		SignUpOutDto signUpOutDto = new SignUpOutDto();
		signUpOutDto.setUserId(userDetails.getUserId());
		signUpOutDto.setAddressLine1(userDetails.getAddressLine1());
		signUpOutDto.setBizName(userDetails.getBizName());
		signUpOutDto.setCity(userDetails.getCity());
		signUpOutDto.setCountry(userDetails.getCountry());
		signUpOutDto.setCreatedDt(userDetails.getCreatedDt().truncatedTo(java.time.temporal.ChronoUnit.SECONDS));
		signUpOutDto.setEmail(userDetails.getEmail());
		signUpOutDto.setState(userDetails.getState());
		signUpOutDto.setUserName(userDetails.getUserName());
		signUpOutDto.setIsBusiness(userDetails.getIsBusiness());
		return signUpOutDto;
	}
}
