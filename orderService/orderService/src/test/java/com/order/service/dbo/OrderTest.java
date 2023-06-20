package com.order.service.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.order.service.repository.OrderRepository;
import com.order.service.services.OrderService;
import com.order.service.services.SequenceGenerator;

public class OrderTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testGetterAndSetter() {
		Order order = new Order();
		assertNull(order.getBuyerId());
		Long buyerId = 1L;
		order.setBuyerId(buyerId);
		when(sequenceGenerator.generateSequence(order.SEQUENCE_NAME)).thenReturn(1L);
		when(orderRepository.save(order)).thenReturn(order);
		Order actualOrder = orderService.placeOrder(order);
		assertEquals(order, actualOrder);
	}
}
