package com.order.service.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.order.service.dbo.Order;
import com.order.service.exception.NotFoundException;
import com.order.service.repository.OrderRepository;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPlaceOrder() {
        // Create a sample order
        Order order = new Order();
        order.setBuyerId(1L);
        when(sequenceGenerator.generateSequence(anyString())).thenReturn(100L);

        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.placeOrder(order);

        assertEquals(100L, result.getId());
        verify(sequenceGenerator).generateSequence(anyString());
        verify(orderRepository).save(order);
    }

    @Test
    void testCancelOrder_OrderFound() throws NotFoundException {

        Order order = new Order();
        order.setId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        assertDoesNotThrow(() -> orderService.cancelOrder(1L));
        verify(orderRepository).deleteById(1L);
    }

    @Test
    void testCancelOrder_OrderNotFound() {

        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.cancelOrder(1L));

        verify(orderRepository, never()).deleteById(anyLong());
    }

    @Test
    void testGetOrdersByBuyerId() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setBuyerId(1L);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setBuyerId(1L);

        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findByBuyerId(1L)).thenReturn(orders);
        List<Order> result = orderService.getOrdersByBuyerId(1L);
        verify(orderRepository).findByBuyerId(1L);
        assertEquals(2, result.size());
        assertEquals(orders, result);
    }

    @Test
    void testGetOrderById_OrderFound() throws NotFoundException {

        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(1L);
        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

    @Test
    void testGetOrderById_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.getOrderById(1L));

        verify(orderRepository).findById(1L);
    }
}

