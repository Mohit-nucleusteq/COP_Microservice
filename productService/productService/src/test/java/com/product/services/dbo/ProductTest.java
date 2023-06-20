package com.product.services.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ProductTest {

	@Test
	public void testGetterAndSetter() {
	Product product = new Product();
	assertNull(product.getProductId());
	Long productId = 8L;
	product.setProductId(productId);
	assertEquals(productId, product.getProductId());

	assertNull(product.getProductName());
	String productName = "testProductName";
	product.setProductName(productName);
	assertEquals(productName, product.getProductName());

	assertNull(product.getDescription());
	String description = "testDescription";
	product.setDescription(description);
	assertEquals(description, product.getDescription());

	assertNull(product.getQuantity());
	Long quantity = 9L;
	product.setQuantity(quantity);
	assertEquals(quantity, product.getQuantity());

	assertNull(product.getPrice());
	Double price = 6.97;
	product.setPrice(price);
	assertEquals(price, product.getPrice());

	assertNull(product.getManufacturedBy());
	String manufacturedBy = "testManufacturedBy";
	product.setManufacturedBy(manufacturedBy);
	assertEquals(manufacturedBy, product.getManufacturedBy());

	assertNull(product.getDiscount());
	Double discount = 6.97;
	product.setDiscount(discount);
	assertEquals(discount, product.getDiscount());

	}
	@Test
	public void testToStringMethod() {
	Product product = new Product();
	Long productId = 1L;
	product.setProductId(productId);

	String productName = "testProductName";
	product.setProductName(productName);

	String description = "testDescription";
	product.setDescription(description);

	Long quantity = 3L;
	product.setQuantity(quantity);

	Double price = 0.12;
	product.setPrice(price);

	String manufacturedBy = "testManufacturedBy";
	product.setManufacturedBy(manufacturedBy);

	Double discount = 2.19;
	product.setDiscount(discount);

	assertEquals("Product(productId=" + productId + ", productName=" + productName + ", description=" + description
			+ ", quantity=" + quantity + ", price=" + price + ", manufacturedBy=" + manufacturedBy + ", discount="
			+ discount + ")", product.toString());
	}

	@Test
	public void testEqualAndHashCode() {
		Long productId = 1L;
		String productName = "testProductName";
		String description = "testDescription";
		Long quantity = 3L;
		Double price = 0.126;
		String manufacturedBy = "testManufacturedBy";
		Double discount = 2.19;
		Product product1 = setUpData(productId, productName, description, quantity, price, manufacturedBy, discount);

		assertEquals(product1, product1);
		assertEquals(product1.hashCode(), product1.hashCode());
		assertNotEquals(product1, new Object());

		Product product2 = setUpData(productId, productName, description, quantity, price, manufacturedBy, discount);
		assertEquals(product1, product2);
		assertEquals(product1.hashCode(), product2.hashCode());

		product2 = setUpData(2L, productName, description, quantity, price, manufacturedBy, discount);
		assertNotEquals(product1, product2);
		assertNotEquals(product1.hashCode(), product2.hashCode());

		product2 = setUpData(productId, productName + " ", description, quantity, price, manufacturedBy, discount);
		assertNotEquals(product1, product2);
		assertNotEquals(product1.hashCode(), product2.hashCode());

		product2 = setUpData(productId, productName, description + " ", quantity, price, manufacturedBy, discount);
		assertNotEquals(product1, product2);
		assertNotEquals(product1.hashCode(), product2.hashCode());

		product2 = setUpData(productId, productName, description, 5L, price, manufacturedBy, discount);
		assertNotEquals(product1, product2);
		assertNotEquals(product1.hashCode(), product2.hashCode());

		product2 = setUpData(productId, productName, description, quantity, 7.5, manufacturedBy, discount);
		assertNotEquals(product1, product2);
		assertNotEquals(product1.hashCode(), product2.hashCode());

		product2 = setUpData(productId, productName, description, quantity, price, manufacturedBy + " ", discount);
		assertNotEquals(product1, product2);
		assertNotEquals(product1.hashCode(), product2.hashCode());

		product2 = setUpData(productId, productName, description, quantity, price, manufacturedBy, 50.2);
		assertNotEquals(product1, product2);
		assertNotEquals(product1.hashCode(), product2.hashCode());
		
		product1 = new Product();
		product2 = new Product();

		assertEquals(product1, product2);
		assertEquals(product1.hashCode(), product2.hashCode());
	}
	private Product setUpData(Long productId, String productName, String description, Long quantity, Double price,
			String manufacturedBy, Double discount) {
		Product product = new Product();
		product.setDescription(description);
		product.setDiscount(discount);
		product.setManufacturedBy(manufacturedBy);
		product.setPrice(price);
		product.setProductId(productId);
		product.setProductName(productName);
		product.setQuantity(quantity);
		return product;
	}
}
