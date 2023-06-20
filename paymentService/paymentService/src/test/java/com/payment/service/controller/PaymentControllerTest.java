package com.payment.service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
import com.payment.service.dbo.Wallet;
import com.payment.service.dto.PaymentRequestDTO;
import com.payment.service.service.PaymentService;

public class PaymentControllerTest {

	@InjectMocks
	private PaymentController paymentController;

	@Mock
	private PaymentService paymentService;

	private MockMvc mockMvc;

	private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
	}

	@Test
	public void testAddWallet() throws JsonProcessingException, Exception {
		Wallet wallet = new Wallet();
		wallet.setBalance(12345.0);
		wallet.setCardNumber("XXXXXXXXXXX2341");
		wallet.setIsDefault(false);
		wallet.setId(1L);
		wallet.setUserId(1L);

		when(paymentService.addWallet(wallet)).thenReturn(wallet);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/payment/wallet")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(OBJECTMAPPPER.writeValueAsBytes(wallet)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(wallet), mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testDeleteWallet() throws Exception {
		doNothing().when(paymentService).deleteWallet(1L);
		String expectedMessage = String.format("Wallet details removed successfully for %d", 1L);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/payment/wallet/1")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(expectedMessage,
				mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testMakePayment() throws JsonProcessingException, Exception {
		PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
		paymentRequestDTO.setAmount(1290.0);
		String expectedMessage = String.format("Payment done successfully. Balance in your account is %s", 1425360.45);

		when(paymentService.makePayment(1L, 1290.0)).thenReturn(expectedMessage);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/payment/1")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(OBJECTMAPPPER.writeValueAsBytes(paymentRequestDTO)))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(expectedMessage, mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testSetDefaultWallet() throws Exception {
		Wallet wallet = new Wallet();
		wallet.setBalance(455656.0);
		wallet.setCardNumber("XXXXXXXXXXXXX5368");
		wallet.setIsDefault(true);
		wallet.setId(1L);
		wallet.setUserId(1L);
		when(paymentService.setDefaultWallet(1L, 1L)).thenReturn(wallet);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/payment/wallet/1/default/1")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals(OBJECTMAPPPER.writeValueAsString(wallet), mvcResult.getResponse().getContentAsString());
	}
}
