package com.product.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.product.services.dbo.Product;

public class ProductOutDtoTest {

	@Test
	public void testGetterAndSetter() {
		ProductOutDto productOutDto = new ProductOutDto();

		assertNull(productOutDto.getProductDetails());
		List<Product> productDetails = new ArrayList<>();
		Product product = new Product();
		productDetails.add(product);
		productOutDto.setProductDetails(productDetails);
		assertEquals(productDetails, productOutDto.getProductDetails());

		Long count = 1L;
		assertNull(productOutDto.getCount());
		productOutDto.setCount(count);
		assertEquals(count, productOutDto.getCount());
		
	}
	
	@Test
	public void testToStringMethod() {
		ProductOutDto productOutDto = new ProductOutDto();
		
		List<Product> productDetails = new ArrayList<>();
		Product product = new Product();
		productDetails.add(product);
		productOutDto.setProductDetails(productDetails);
		
		Long count = 1L;
		productOutDto.setCount(count);
		
		assertEquals("ProductOutDto(productDetails=" + productDetails + ", count=" + count + ")", productOutDto.toString());
	}

	@Test
	public void testHashCodeAndEqualMethod() {

		List<Product> productDetails = new ArrayList<>();
		Long count = 1L;

		ProductOutDto productOutDto1 = setUpData(productDetails, count);
		productOutDto1.setCount(count);

		assertEquals(productOutDto1, productOutDto1);
		assertEquals(productOutDto1.hashCode(), productOutDto1.hashCode());
		assertNotEquals(productOutDto1, new Object());

		ProductOutDto productOutDto2 = setUpData(productDetails, count);

		assertEquals(productOutDto1, productOutDto2);
		assertEquals(productOutDto1.hashCode(), productOutDto2.hashCode());

		List<Product> productDetails1 = new ArrayList<>();
		Product product = new Product();
		product.setDescription("Destcription");
		product.setDiscount(1.2);
		productDetails1.add(product);

		productOutDto2 = setUpData(productDetails1, count);
		assertNotEquals(productOutDto1, productOutDto2);
		assertNotEquals(productOutDto1.hashCode(), productOutDto2.hashCode());

		productOutDto2 = setUpData(productDetails, 2L);
		assertNotEquals(productOutDto1, productOutDto2);
		assertNotEquals(productOutDto1.hashCode(), productOutDto2.hashCode());

		productOutDto1 = new ProductOutDto();
		productOutDto2 = new ProductOutDto();
		assertEquals(productOutDto1, productOutDto2);
		assertEquals(productOutDto1.hashCode(), productOutDto2.hashCode());
	}

	private ProductOutDto setUpData(List<Product> productDetails, Long count) {
		ProductOutDto productOutDto = new ProductOutDto();
		productOutDto.setProductDetails(productDetails);
		productOutDto.setCount(count);
		return productOutDto;
	}

}
