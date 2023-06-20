package com.payment.service.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class WalletTest {

	@Test
	public void testGetterAndSetter() {
		Wallet wallet = new Wallet();
		assertNull(wallet.getId());
		Long id = 1l;
		wallet.setId(id);
		assertEquals(id, wallet.getId());
		
		assertNull(wallet.getUserId());
		Long userId = 1l;
		wallet.setUserId(userId);
		assertEquals(userId, wallet.getUserId());
		
		assertNull(wallet.getCardNumber());
		String cardNumber = "XXXXXXXXXXXXXXXX";
		wallet.setCardNumber(cardNumber);
		assertEquals(cardNumber, wallet.getCardNumber());
		
		assertNull(wallet.getBalance());
		Double balance = 13343.0;
		wallet.setBalance(balance);
		assertEquals(balance, wallet.getBalance());

		assertNull(wallet.getIsDefault());
		Boolean isDefault = true;
		wallet.setIsDefault(isDefault);
		assertEquals(isDefault, wallet.getIsDefault());
	}

	@Test
	public void testEqualAndHashCode() {
		Long id = 1l;
		Long userId = 1l;
		String cardNumber = "XXXXXXXXXXXXXXXX";
		Double balance = 13343.0;
		Boolean isDefault = true;

		Wallet wallet1 = setWalletData(id, userId, cardNumber, balance, isDefault);
		assertEquals(wallet1, wallet1);
		assertEquals(wallet1.hashCode(), wallet1.hashCode());
		assertNotEquals(wallet1.hashCode(), new Object());

		Wallet wallet2 = setWalletData(id, userId, cardNumber, balance, isDefault);
		assertEquals(wallet1, wallet2);
		assertEquals(wallet1.hashCode(), wallet2.hashCode());

		wallet2 = setWalletData(2L, userId, cardNumber, balance, isDefault);
		assertNotEquals(wallet1, wallet2);
		assertNotEquals(wallet1.hashCode(), wallet2.hashCode());

		wallet2 = setWalletData(id, 2L, cardNumber, balance, isDefault);
		assertNotEquals(wallet1, wallet2);
		assertNotEquals(wallet1.hashCode(), wallet2.hashCode());
		
		wallet2 = setWalletData(id, userId, cardNumber + " ", balance, isDefault);
		assertNotEquals(wallet1, wallet2);
		assertNotEquals(wallet1.hashCode(), wallet2.hashCode());
		
		wallet2 = setWalletData(id, userId, cardNumber, 57.0, isDefault);
		assertNotEquals(wallet1, wallet2);
		assertNotEquals(wallet1.hashCode(), wallet2.hashCode());
		
		wallet2 = setWalletData(id, userId, cardNumber, balance, false);
		assertNotEquals(wallet1, wallet2);
		assertNotEquals(wallet1.hashCode(), wallet2.hashCode());
		
		wallet1 = new Wallet();
		wallet2 = new Wallet();
		assertEquals(wallet1, wallet2);
		assertEquals(wallet1.hashCode(), wallet2.hashCode());
	}

	@Test
	public void testToString() {
		Long id = 1l;
		Long userId = 1l;
		String cardNumber = "XXXXXXXXXXXXXXXX";
		Double balance = 13343.0;
		Boolean isDefault = true;

		Wallet wallet = setWalletData(id, userId, cardNumber, balance, isDefault);
		
		assertEquals("Wallet(id=1, userId=1, cardNumber=XXXXXXXXXXXXXXXX, balance=13343.0"
				+ ", isDefault=true)", wallet.toString());
	}

	private Wallet setWalletData(Long id, Long userId, String cardNumber, Double balance,
			Boolean isDefault) {
		Wallet wallet = new Wallet();
		wallet.setBalance(balance);
		wallet.setCardNumber(cardNumber);
		wallet.setId(id);
		wallet.setIsDefault(isDefault);
		wallet.setUserId(userId);
		return wallet;
	}
}
