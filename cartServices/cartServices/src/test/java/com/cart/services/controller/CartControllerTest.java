package com.cart.services.controller;

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

import com.cart.services.dbo.CartItem;
import com.cart.services.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CartControllerTest {

	@InjectMocks
	private CartController cartController;

	private MockMvc mockMvc;

	@Mock
	private CartService cartService;

	private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
	}

	@Test
	public void testAddToCart() throws JsonProcessingException, Exception {
		CartItem cartItem = new CartItem();
		cartItem.setId(1L);
		cartItem.setProductName("Mobile");
		cartItem.setQuantity(1);

		when(cartService.addToCart(cartItem)).thenReturn(cartItem);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cart/addProduct")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(OBJECTMAPPPER.writeValueAsBytes(cartItem)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(cartItem), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testUpdateCartItem() throws JsonProcessingException, Exception {
		CartItem cartItem = new CartItem();
		cartItem.setId(1L);
		cartItem.setProductName("Mobile");
		cartItem.setQuantity(1);

		when(cartService.updateCartItem(1L, cartItem)).thenReturn(cartItem);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/cart/1")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(OBJECTMAPPPER.writeValueAsBytes(cartItem)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(cartItem), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testDeleteItem() throws JsonProcessingException, Exception {

		String expectedMessage = "Cart item deleted successfully for id 1";
		doNothing().when(cartService).deleteCartItem(1L);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cart/1")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(expectedMessage,
				mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testGetAllCartItems() throws JsonProcessingException, Exception {
		CartItem cartItem = new CartItem();
		cartItem.setId(1L);
		cartItem.setProductName("Mobile");
		cartItem.setQuantity(1);
		List<CartItem> itemList = new ArrayList<CartItem>();
		itemList.add(cartItem);
		when(cartService.getAllCartItems()).thenReturn(itemList);

				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/getCartProduct")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
				.header("authorization", "authorization");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(itemList),
				mvcResult.getResponse().getContentAsString());
	}
}
