package com.product.services.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductInDto {

	@NotEmpty(message = "Product name can not be null or empty")
	private String productName;

	@NotEmpty(message = "Product description can not be null or empty")
	private String description;

	@NotNull(message = "Product quantity can not be null or empty")
	private Long quantity;

	@NotNull(message = "Product price can not be null or empty")
	private Double price;

	@NotEmpty(message = "Manufacturer can not be null or empty")
	private String manufacturedBy;

	@NotNull(message = "Discount price can not be null or empty")
	private Double discount;

}
