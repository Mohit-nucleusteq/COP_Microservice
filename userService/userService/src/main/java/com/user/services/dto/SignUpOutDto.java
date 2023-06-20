package com.user.services.dto;

import java.time.Instant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class SignUpOutDto {

	private Long userId;

	private String userName;

	private String email;

	private Instant createdDt;

	private String addressLine1;

	private String city;

	private String state;

	private String country;

	private Boolean isBusiness;

	private String bizName;
}
