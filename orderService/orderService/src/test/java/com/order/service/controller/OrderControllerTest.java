package com.order.service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.order.service.dbo.Order;
import com.order.service.services.OrderService;

public class OrderControllerTest {

	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderService orderService;

	private MockMvc mockMvc;

	private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void testPlaceOrder() throws JsonProcessingException, Exception {
		Order order = new Order();
		order.setBuyerId(1L);
		order.setId(1L);
		List<String> product = new ArrayList<>();
		product.add("Mobile");
		product.add("Tab");
		order.setProducts(product);
		when(orderService.placeOrder(order)).thenReturn(order);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/placeOrder").accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(OBJECTMAPPPER.writeValueAsBytes(order)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(order), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testCancelOrder() throws Exception {
		doNothing().when(orderService).cancelOrder(1L);
		String expectedMessage = "Order deleted successfully for 1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/1").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON).header("authorization", "authorization");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(expectedMessage, mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testGetOrderByBuyerId() throws Exception {
		Order order = new Order();
		order.setBuyerId(1L);
		order.setId(1L);
		List<String> product = new ArrayList<>();
		product.add("Mobile");
		product.add("Tab");
		order.setProducts(product);
		List<Order> expectedOrderList = new ArrayList<>();
		expectedOrderList.add(order);
		when(orderService.getOrdersByBuyerId(1L)).thenReturn(expectedOrderList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/buyer/1").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(expectedOrderList), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testGetOrderById() throws Exception {
		Order order = new Order();
		order.setBuyerId(1L);
		order.setId(1L);
		List<String> product = new ArrayList<>();
		product.add("Mobile");
		product.add("Tab");
		order.setProducts(product);
		when(orderService.getOrderById(1L)).thenReturn(Optional.of(order));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/1").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(order), mvcResult.getResponse().getContentAsString());
	}
}
