package com.cart.services.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CartItemTest {

	@Test
	public void testGetterAndSetter() {
		CartItem cartItem = new CartItem();
		assertNull(cartItem.getId());
		cartItem.setId(1L);
		assertEquals(1L, cartItem.getId());

		assertNull(cartItem.getProductName());
		cartItem.setProductName("Product name");
		assertEquals("Product name", cartItem.getProductName());

		assertNull(cartItem.getQuantity());
		cartItem.setQuantity(1);
		assertEquals(1, cartItem.getQuantity());
	}

	@Test
	public void testEqualAndHashCode() {
		Long id = 1L;
		String productName = "mobile";
		Integer quantity = 1;
		CartItem cartItem1 = setUpCartData(id, productName, quantity);
		assertEquals(cartItem1, cartItem1);
		assertEquals(cartItem1.hashCode(), cartItem1.hashCode());
		assertNotEquals(cartItem1, new Object());

		CartItem cartItem2 = setUpCartData(id, productName, quantity);
		assertEquals(cartItem1, cartItem2);
		assertEquals(cartItem1.hashCode(), cartItem2.hashCode());

		cartItem2 = setUpCartData(2L, productName, quantity);
		assertNotEquals(cartItem1, cartItem2);
		assertNotEquals(cartItem1.hashCode(), cartItem2.hashCode());

		cartItem2 = setUpCartData(id, productName + " ", quantity);
		assertNotEquals(cartItem1, cartItem2);
		assertNotEquals(cartItem1.hashCode(), cartItem2.hashCode());

		cartItem2 = setUpCartData(id, productName, 2);
		assertNotEquals(cartItem1, cartItem2);
		assertNotEquals(cartItem1.hashCode(), cartItem2.hashCode());

		cartItem1 = new CartItem();
		cartItem2 = new CartItem();
		assertEquals(cartItem1, cartItem2);
		assertEquals(cartItem1.hashCode(), cartItem2.hashCode());
	}

	@Test
	public void testToString() {
		Long id = 1L;
		String productName = "mobile";
		Integer quantity = 1;
		CartItem cartItem = setUpCartData(id, productName, quantity);
		assertEquals("CartItem(id=1, productName=mobile, quantity=1)", cartItem.toString());
	}

	private CartItem setUpCartData(Long id, String productName, Integer quantity) {
		CartItem cartItem = new CartItem();
		cartItem.setId(id);
		cartItem.setProductName(productName);
		cartItem.setQuantity(quantity);
		return cartItem;
	}
}
