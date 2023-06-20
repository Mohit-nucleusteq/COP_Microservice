package com.product.services.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.services.dbo.Product;
import com.product.services.dto.ProductInDto;
import com.product.services.dto.ProductOutDto;
import com.product.services.service.ProductService;
import com.product.services.validator.ProductValidator;

public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	private MockMvc mockMvc;

	@Mock
	private ProductValidator productValidator;

	private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testGetAllProducts() throws Exception {
		ProductOutDto expectedProductOutDto = new ProductOutDto();
		List<Product> productDetails = new ArrayList<>();
		Long count = 1L;
		Product product = new Product();
		product.setDescription("Test Description");
		product.setDiscount(1.0);
		product.setManufacturedBy("Test manufacture");
		product.setPrice(1.2);
		product.setProductId(1L);
		product.setProductName("Phone");
		product.setQuantity(1L);
		productDetails.add(product);
		expectedProductOutDto.setCount(count);
		expectedProductOutDto.setProductDetails(productDetails);
		when(productService.getAllProducts()).thenReturn(expectedProductOutDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fetchProduct")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
				.header("authorization", "authorization");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(expectedProductOutDto),
				mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testFetchProductbyId() throws Exception {
		Product product = new Product();
		product.setDescription("Test Description");
		product.setDiscount(1.0);
		product.setManufacturedBy("Test manufacture");
		product.setPrice(1.2);
		product.setProductId(1L);
		product.setProductName("Phone");
		product.setQuantity(1L);
		doNothing().when(productValidator).validateProductId(1L);
		when(productService.getProductById(1L)).thenReturn(product);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fetchProduct/1")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
				.header("authorization", "authorization");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(product), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testAddProductData() throws JsonProcessingException, Exception {
		Product product = new Product();
		product.setDescription("Test Description");
		product.setDiscount(1.0);
		product.setManufacturedBy("Test manufacture");
		product.setPrice(1.2);
		product.setProductId(1L);
		product.setProductName("Phone");
		product.setQuantity(1L);

		when(productService.addNewProduct(product)).thenReturn(product);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("authorization", "authorization").content(OBJECTMAPPPER.writeValueAsBytes(product)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(product), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testUpdateProduct() throws Exception {
		ProductInDto productInDto = new ProductInDto();
		productInDto.setDescription("Test Description");
		productInDto.setDiscount(1.0);
		productInDto.setManufacturedBy("Test manufacture");
		productInDto.setPrice(1.2);
		productInDto.setProductName("Phone");
		productInDto.setQuantity(1L);

		Product product = new Product();
		product.setDescription("Test Description");
		product.setDiscount(1.0);
		product.setManufacturedBy("Test manufacture");
		product.setPrice(1.2);
		product.setProductId(1L);
		product.setProductName("Phone");
		product.setQuantity(1L);
		when(productService.updateProduct(1L, productInDto)).thenReturn(product);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/1")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("authorization", "authorization").content(OBJECTMAPPPER.writeValueAsBytes(productInDto)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(product), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testDeleteProduct() throws Exception {

		String expectedMessage = String.format("Product details deleted successfully for id %s", 1);
		doNothing().when(productValidator).validateProductId(1L);
		when(productService.deleteProduct(1L)).thenReturn(expectedMessage);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/1")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
				.header("authorization", "authorization");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(expectedMessage,
				mvcResult.getResponse().getContentAsString());
	}
}
