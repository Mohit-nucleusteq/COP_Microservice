package com.product.services.model;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ErrorResponse {

	private Instant timestamp;

	private String status;

	private List<String> errorMessages;

}
