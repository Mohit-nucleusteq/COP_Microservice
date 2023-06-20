package com.product.services.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ProductInDtoTest {

	
	@Test
	public void testGetterAndSetter() {
	ProductInDto productInDto = new ProductInDto();

	assertNull(productInDto.getProductName());
	String productInDtoName = "testProductName";
	productInDto.setProductName(productInDtoName);
	assertEquals(productInDtoName, productInDto.getProductName());

	assertNull(productInDto.getDescription());
	String description = "testDescription";
	productInDto.setDescription(description);
	assertEquals(description, productInDto.getDescription());

	assertNull(productInDto.getQuantity());
	Long quantity = 9L;
	productInDto.setQuantity(quantity);
	assertEquals(quantity, productInDto.getQuantity());

	assertNull(productInDto.getPrice());
	Double price = 6.97;
	productInDto.setPrice(price);
	assertEquals(price, productInDto.getPrice());

	assertNull(productInDto.getManufacturedBy());
	String manufacturedBy = "testManufacturedBy";
	productInDto.setManufacturedBy(manufacturedBy);
	assertEquals(manufacturedBy, productInDto.getManufacturedBy());

	assertNull(productInDto.getDiscount());
	Double discount = 6.97;
	productInDto.setDiscount(discount);
	assertEquals(discount, productInDto.getDiscount());

	}
	@Test
	public void testToStringMethod() {
	ProductInDto productInDto = new ProductInDto();

	String productName = "testProductName";
	productInDto.setProductName(productName);

	String description = "testDescription";
	productInDto.setDescription(description);

	Long quantity = 3L;
	productInDto.setQuantity(quantity);

	Double price = 0.12;
	productInDto.setPrice(price);

	String manufacturedBy = "testManufacturedBy";
	productInDto.setManufacturedBy(manufacturedBy);

	Double discount = 2.19;
	productInDto.setDiscount(discount);

	assertEquals("ProductInDto("+"productName=" + productName + ", description=" + description
			+ ", quantity=" + quantity + ", price=" + price + ", manufacturedBy=" + manufacturedBy + ", discount="
			+ discount + ")", productInDto.toString());
	}

	@Test
	public void testEqualAndHashCode() {
		String productInDtoName = "testProductInDtoName";
		String description = "testDescription";
		Long quantity = 3L;
		Double price = 0.126;
		String manufacturedBy = "testManufacturedBy";
		Double discount = 2.19;
		ProductInDto productInDto1 = setUpData(productInDtoName, description, quantity, price, manufacturedBy, discount);

		assertEquals(productInDto1, productInDto1);
		assertEquals(productInDto1.hashCode(), productInDto1.hashCode());
		assertNotEquals(productInDto1, new Object());

		ProductInDto productInDto2 = setUpData(productInDtoName, description, quantity, price, manufacturedBy, discount);
		assertEquals(productInDto1, productInDto2);
		assertEquals(productInDto1.hashCode(), productInDto2.hashCode());

		productInDto2 = setUpData(productInDtoName + " ", description, quantity, price, manufacturedBy, discount);
		assertNotEquals(productInDto1, productInDto2);
		assertNotEquals(productInDto1.hashCode(), productInDto2.hashCode());

		productInDto2 = setUpData(productInDtoName, description + " ", quantity, price, manufacturedBy, discount);
		assertNotEquals(productInDto1, productInDto2);
		assertNotEquals(productInDto1.hashCode(), productInDto2.hashCode());

		productInDto2 = setUpData(productInDtoName, description, 5L, price, manufacturedBy, discount);
		assertNotEquals(productInDto1, productInDto2);
		assertNotEquals(productInDto1.hashCode(), productInDto2.hashCode());

		productInDto2 = setUpData(productInDtoName, description, quantity, 7.5, manufacturedBy, discount);
		assertNotEquals(productInDto1, productInDto2);
		assertNotEquals(productInDto1.hashCode(), productInDto2.hashCode());

		productInDto2 = setUpData(productInDtoName, description, quantity, price, manufacturedBy + " ", discount);
		assertNotEquals(productInDto1, productInDto2);
		assertNotEquals(productInDto1.hashCode(), productInDto2.hashCode());

		productInDto2 = setUpData(productInDtoName, description, quantity, price, manufacturedBy, 50.2);
		assertNotEquals(productInDto1, productInDto2);
		assertNotEquals(productInDto1.hashCode(), productInDto2.hashCode());
		
		productInDto1 = new ProductInDto();
		productInDto2 = new ProductInDto();

		assertEquals(productInDto1, productInDto2);
		assertEquals(productInDto1.hashCode(), productInDto2.hashCode());
	}
	private ProductInDto setUpData(String productName, String description, Long quantity, Double price,
			String manufacturedBy, Double discount) {
		ProductInDto productInDto = new ProductInDto();
		productInDto.setDescription(description);
		productInDto.setDiscount(discount);
		productInDto.setManufacturedBy(manufacturedBy);
		productInDto.setPrice(price);
		productInDto.setProductName(productName);
		productInDto.setQuantity(quantity);
		return productInDto;
	}
}
