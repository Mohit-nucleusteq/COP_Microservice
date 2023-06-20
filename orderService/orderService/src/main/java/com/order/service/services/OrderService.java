package com.order.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.service.dbo.Order;
import com.order.service.exception.NotFoundException;
import com.order.service.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    public Order placeOrder(Order order) {
        order.setId(sequenceGenerator.generateSequence(order.SEQUENCE_NAME));
        return orderRepository.save(order);
    }

    public void cancelOrder(Long orderId) throws NotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(!orderOptional.isPresent()) {
            throw new NotFoundException("Order is not present");
        }
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrdersByBuyerId(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    public Optional<Order> getOrderById(Long orderId) throws NotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(!orderOptional.isPresent()) {
            throw new NotFoundException("Order is not present");
        }
        return orderRepository.findById(orderId);
    }
}
