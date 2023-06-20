package com.user.services.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.user.services.dto.GenerateTokenOutDto;
import com.user.services.exception.UnauthorizedException;
import com.user.services.model.UserDetails;
import com.user.services.repository.UserDetailsRepository;
import com.user.services.util.ErrorConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {

	/**
	 * The Logger object.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

	@Autowired
	private UserDetailsRepository detailsRepository;

    /**
     * The Gson Object.
     */
    private static final Gson GSON = new Gson();

	public UserDetails verifyToken(String authToken) throws UnauthorizedException {
		String token = removeBearerPrefixFromToken(authToken);
		String secret = "secret-key";
		try {
			Claims claims = Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
			String tokenData = claims.get("data").toString().replace(" ", "");
			return GSON.fromJson(tokenData, UserDetails.class);
		} catch (SignatureException e) {
			LOGGER.error("Signature is not valid for the token {}, throwing SignatureException", token);
			throw new UnauthorizedException(ErrorConstants.INVALID_TOKEN);
		} catch (ExpiredJwtException exception) {
			LOGGER.error("The token {} is expired, throwing ExpiredJwtException", token);
			throw new UnauthorizedException(ErrorConstants.EXPIRED_TOKEN);
		} catch (MalformedJwtException e) {
			LOGGER.error("Invalid algorithm & token type {}, throwing MalformedJwtException", token);
			throw new UnauthorizedException(ErrorConstants.MALFORMEND_EXCEPTION_MESSAGE);
		} catch (UnsupportedJwtException e) {
			LOGGER.error("Missing required fields in token {}, throwing UnsupportedJwtException", token);
			throw new UnauthorizedException(ErrorConstants.UNSUPPORTED_EXCEPTION_MESSAGE);
		}
	}

	private String removeBearerPrefixFromToken(final String authToken) throws UnauthorizedException {
		String token = authToken.split(" ")[1];
		if (token.length() > 1) {
			return token;
		} else {
			LOGGER.error("Invalid Token: {}, size must be greater one, Throwing UnauthorizedException", authToken);
			throw new UnauthorizedException(String.format(ErrorConstants.INVALID_TOKEN));
		}
	}

	public GenerateTokenOutDto generateAccessToken(final String userEmail) {
		GenerateTokenOutDto generateTokenOutDto = new GenerateTokenOutDto();
		String tokenIssuer = "www.test.com";
		UserDetails userDetails = detailsRepository.getUserDetailsByEmail(userEmail);

		Map<String, Object> claims = new HashMap<>();
		LocalDateTime tokenIssuedDate = LocalDateTime.now();
		LocalDateTime tokenExpiration = LocalDateTime.now().plusDays(1L);
		claims.put("data", userDetails);
		String refreshTokenSecret = "secret-key";

		Key tokenKey = new SecretKeySpec(refreshTokenSecret.getBytes(StandardCharsets.UTF_8),
				SignatureAlgorithm.HS256.getJcaName());
		String accessToken = Jwts.builder().setClaims(claims).setIssuer(tokenIssuer)
				.setSubject(Long.toString(userDetails.getUserId()))
				.setIssuedAt(Date.from(tokenIssuedDate.atZone(ZoneOffset.UTC).toInstant()))
				.setExpiration(Date.from(tokenExpiration.atZone(ZoneOffset.UTC).toInstant()))
				.signWith(SignatureAlgorithm.HS256, tokenKey).compact();
		generateTokenOutDto.setAccessToken(accessToken);
		return generateTokenOutDto;
	}

}
