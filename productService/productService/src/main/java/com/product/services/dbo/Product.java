package com.product.services.dbo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document("Product")
public class Product {

	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";

	@Id
	private Long productId;

	@NotEmpty(message = "Prodcut name can not be null or empty")
	private String productName;

	@NotEmpty(message = "Prodcut description can not be null or empty")
	private String description;

	@NotNull(message = "Prodcut quantity can not be null or empty")
	private Long quantity;

	@NotNull(message = "Prodcut price can not be null or empty")
	private Double price;

	@NotEmpty(message = "Manufacturer can not be null or empty")
	private String manufacturedBy;

	@NotNull(message = "Discount price can not be null or empty")
	private Double discount;

}
