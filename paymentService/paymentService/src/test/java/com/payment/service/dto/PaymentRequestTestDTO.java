package com.payment.service.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class PaymentRequestTestDTO {

	@Test
	public void testGetterAndSetter() {
		PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
		assertNull(paymentRequestDTO.getAmount());
		Double amount = 111.0;
		paymentRequestDTO.setAmount(amount);
		assertEquals(amount, paymentRequestDTO.getAmount());
	}

	@Test
	public void testEqualAndHashCode() {
		PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
		Double amount = 111.0;
		paymentRequestDTO.setAmount(amount);
		assertEquals(paymentRequestDTO, paymentRequestDTO);
		assertEquals(paymentRequestDTO.hashCode(), paymentRequestDTO.hashCode());

		PaymentRequestDTO paymentRequestDTO1 = new PaymentRequestDTO();

		paymentRequestDTO1.setAmount(amount);
		assertEquals(paymentRequestDTO, paymentRequestDTO1);
		assertEquals(paymentRequestDTO.hashCode(), paymentRequestDTO1.hashCode());

		paymentRequestDTO1 = new PaymentRequestDTO();

		Double amount1 = 1151.0;
		paymentRequestDTO1.setAmount(amount1);
		assertNotEquals(paymentRequestDTO, paymentRequestDTO1);
		assertNotEquals(paymentRequestDTO.hashCode(), paymentRequestDTO1.hashCode());
		
		paymentRequestDTO = new PaymentRequestDTO();
		paymentRequestDTO1 = new PaymentRequestDTO();
		
		assertEquals(paymentRequestDTO, paymentRequestDTO1);
		assertEquals(paymentRequestDTO.hashCode(), paymentRequestDTO1.hashCode());
	}

	@Test
	public void testToString() {
		PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
		Double amount = 111.0;
		paymentRequestDTO.setAmount(amount);
		
		assertEquals("PaymentRequestDTO(amount=111.0)", paymentRequestDTO.toString());
	}
}
