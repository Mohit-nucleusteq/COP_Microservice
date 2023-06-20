package com.order.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.order.service.dbo.Order;


public interface OrderRepository extends MongoRepository<Order, Long> {
    List<Order> findByBuyerId(Long buyerId);
}
