package com.cart.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cart.services.dbo.CartItem;

public interface CartRepository extends MongoRepository<CartItem, Long> {

}
