package com.cart.services.service;

import static org.assertj.core.api.Assertions.fail;
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

import com.cart.services.dbo.CartItem;
import com.cart.services.exception.BadRequestException;
import com.cart.services.exception.NotFoundException;
import com.cart.services.repository.CartRepository;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddToCartItem() throws BadRequestException {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProductName("Mobile");
        cartItem.setQuantity(1);
        when(cartRepository.save(cartItem)).thenReturn(cartItem);
        when(sequenceGenerator.generateSequence("cart_sequence")).thenReturn(1L);
        cartService.addToCart(cartItem);
        verify(cartRepository, times(1)).save(cartItem);
    }

    @Test
    public void testEmptyCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.setProductName("");
        String message = "Item cannot be null or empty";
        try {
            cartService.addToCart(cartItem);
            fail("Fails to raise exception");
        } catch(BadRequestException ex) {
            assertEquals(message, ex.getMessage());
        }
    }

    @Test
    public void testUpdateCartItem() throws NotFoundException {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProductName("Mobile");
        cartItem.setQuantity(1);
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cartItem));
        cartService.updateCartItem(1L, cartItem);
        verify(cartRepository, times(1)).save(cartItem);
    }

    @Test
    public void testItemNotFound() throws NotFoundException {
    	CartItem cartItem = new CartItem();
    	cartItem.setId(2L);
    	cartItem.setProductName("Product Name");
    	cartItem.setQuantity(1);
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        String errorMessage = String.format("Items not found in database for id {}", 1);
        try {
            cartService.updateCartItem(1L, cartItem);
            fail("Fails to raise exception");
        } catch(NotFoundException ex) {
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testDeleteCartItem() throws NotFoundException {
    	CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProductName("Mobile");
        cartItem.setQuantity(1);
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cartItem));
        doNothing().when(cartRepository).deleteById(1L);
        cartService.deleteCartItem(1L);
    }

    @Test
    public void testItemIdNotFound() throws NotFoundException {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        String errorMessage = String.format("Items not found in database for id {}", 1);
        try {
            cartService.deleteCartItem(1L);
            fail("Fails to raise exception");
        } catch(NotFoundException ex) {
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testGetAllProduct() {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProductName("Mobile");
        cartItem.setQuantity(1);
        List<CartItem> itemList = new ArrayList<CartItem>();
        itemList.add(cartItem);
        when(cartRepository.findAll()).thenReturn(itemList);
        List<CartItem> actualItemList = cartService.getAllCartItems();
        assertEquals(itemList, actualItemList);
    }
}
