package com.product.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.product.services.dbo.Product;

public interface ProductRepository extends MongoRepository<Product, Long>{

}
