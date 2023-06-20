package com.user.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDetails {

	private Long userId;

	private String userName;

	private String email;

	private String userRole;
}
