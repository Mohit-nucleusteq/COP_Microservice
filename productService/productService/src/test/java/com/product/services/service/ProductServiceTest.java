package com.product.services.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.product.services.dbo.Product;
import com.product.services.dto.ProductInDto;
import com.product.services.dto.ProductOutDto;
import com.product.services.exception.NotFoundException;
import com.product.services.repository.ProductRepository;

public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Mock
    private SequenceGenerator sequenceGenerator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllProducts() throws NotFoundException {
		ProductOutDto productOutDto = new ProductOutDto();
		List<Product> productDetailsList = new ArrayList<>();
		Product product = new Product();
		product.setDescription("Description");
		product.setDiscount(1.5);
		product.setManufacturedBy("Samsung");
		product.setPrice(5000.0);
		product.setProductId(1L);
		product.setProductName("Galaxy");
		product.setQuantity(4L);
		productDetailsList.add(product);
		productOutDto.setProductDetails(productDetailsList);
		productOutDto.setCount(1L);

		when(productRepository.findAll()).thenReturn(productDetailsList);
		when(productRepository.count()).thenReturn(1L);
		ProductOutDto actualProductOutDto = productService.getAllProducts();

		assertEquals(productOutDto, actualProductOutDto);
	}

	@Test
	public void testProductNotFoundException() throws NotFoundException {

		when(productRepository.findAll()).thenReturn(null);
		String errorMessage = "Product details are not present in database";
		try {
			productService.getAllProducts();
		} catch (NotFoundException ex) {
			assertEquals(errorMessage, ex.getMessage());
		}
	}

	@Test
	public void testGetProductById() throws NotFoundException {

		Product product = new Product();
		product.setDescription("Description");
		product.setDiscount(1.5);
		product.setManufacturedBy("Samsung");
		product.setPrice(5000.0);
		product.setProductId(1L);
		product.setProductName("Galaxy");
		product.setQuantity(4L);

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		Product actualProduct = productService.getProductById(1L);
		assertEquals(product, actualProduct);
	}

	@Test
	public void testAddNewProduct() {
		Product product = new Product();
		product.setDescription("Description");
		product.setDiscount(1.5);
		product.setManufacturedBy("Samsung");
		product.setPrice(5000.0);
		product.setProductId(1L);
		product.setProductName("Galaxy");
		product.setQuantity(4L);
		when(productRepository.save(product)).thenReturn(product);
		when(sequenceGenerator.generateSequence("product_sequence")).thenReturn(1L);
		productService.addNewProduct(product);
		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setDescription("Description");
		product.setDiscount(1.5);
		product.setManufacturedBy("Samsung");
		product.setPrice(5000.0);
		product.setProductId(1L);
		product.setProductName("Galaxy");
		product.setQuantity(4L);

		ProductInDto productInDto = new ProductInDto();
		productInDto.setDescription("Description");
		productInDto.setDiscount(1.5);
		productInDto.setManufacturedBy("Samsung");
		productInDto.setPrice(5000.0);
		productInDto.setProductName("Galaxy");
		productInDto.setQuantity(4L);

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(productRepository.save(product)).thenReturn(product);
		Product updateProduct = productService.updateProduct(1L, productInDto);
		assertEquals(product, updateProduct);
	}

	@Test
	public void testDeleteProduct() throws Exception {
		Product product = new Product();
		product.setDescription("Description");
		product.setDiscount(1.5);
		product.setManufacturedBy("Samsung");
		product.setPrice(5000.0);
		product.setProductId(1L);
		product.setProductName("Galaxy");
		product.setQuantity(4L);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		doNothing().when(productRepository).delete(product);
		String message = String.format("Product with id %s has been deleted successfully", 1L);
		String actualMessage = productService.deleteProduct(1L);
		assertEquals(message, actualMessage);
	}
}
