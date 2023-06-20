package com.product.services.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.product.services.dbo.Product;
import com.product.services.exception.NotFoundException;
import com.product.services.repository.ProductRepository;

public class ProductValidatorTest {

	@InjectMocks
	private ProductValidator productValidator;
	
	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidId() {
		Product product = new Product();
		product.setDescription("Description");
		product.setDiscount(1.5);
		product.setManufacturedBy("Samsung");
		product.setPrice(5000.0);
		product.setProductId(1L);
		product.setProductName("Galaxy");
		product.setQuantity(4L);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		assertDoesNotThrow(()-> productValidator.validateProductId(1L));
	}

	@Test
	public void testinvalidId() {
		when(productRepository.findById(1L)).thenReturn(Optional.empty());
		String message = "Product details not found for productId 1";
		try {
			productValidator.validateProductId(1L);
		} catch (NotFoundException notFoundException) {
			assertEquals(message, notFoundException.getMessage());
		}
	}
}
