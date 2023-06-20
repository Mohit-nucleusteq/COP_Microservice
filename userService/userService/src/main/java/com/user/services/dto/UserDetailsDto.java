package com.user.services.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class UserDetailsDto {

	private Long userId;

	private String userName;

	private String email;

	private String password;
}
