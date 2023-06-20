package com.payment.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.payment.service.dbo.Wallet;

public interface WalletRepository extends MongoRepository<Wallet, Long>{

}
