package com.product.services.dto;

import java.util.List;

import com.product.services.dbo.Product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductOutDto {

	private List<Product> productDetails;

	private Long count;
}
