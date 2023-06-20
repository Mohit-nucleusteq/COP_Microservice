package com.payment.service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.payment.service.dbo.Wallet;
import com.payment.service.exception.InsufficientBalanceException;
import com.payment.service.exception.NotFoundException;
import com.payment.service.repository.WalletRepository;

@Service
public class PaymentService {

    /**
     * The WalletRepository object.
     */
    @Autowired
    private WalletRepository walletRepository;

    /**
     * The MongoTemplate object.
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * The SequenceGenerator object.
     */
    @Autowired
    private SequenceGenerator sequenceGenerator;

    /**
     * The Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    /** This method is used to add wallet details into database.
     * @param wallet - wallet details.
     * @return the added wallet details.
     */
    public Wallet addWallet(Wallet wallet) {
        wallet.setId(sequenceGenerator.generateSequence(wallet.SEQUENCE_NAME));
        return walletRepository.save(wallet);
    }

    public void deleteWallet(Long walletId) throws NotFoundException {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(!wallet.isPresent()) {
            throw new NotFoundException(String.format("wallet is not found for id ", walletId));
        }
        walletRepository.deleteById(walletId);
    }

    public String makePayment(Long walletId, double amount) throws InsufficientBalanceException {
        Wallet wallet = walletRepository.findById(walletId).get();

        if (wallet.getBalance() < amount) {
            LOGGER.error("Insufficient balance in the wallet");
            throw new InsufficientBalanceException("Insufficient balance in the wallet");
        }
        double newBalance = wallet.getBalance() - amount;
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
        return String.format("Payment done successfully. Balance in your account is %s", newBalance);
    }

    public Wallet setDefaultWallet(Long walletId, Long userId) {
        Query query = new Query(Criteria.where("userId").is(userId).and("isDefault").is(false));
        Wallet defaultWallet = mongoTemplate.findOne(query, Wallet.class);

        defaultWallet.setIsDefault(true);
        return walletRepository.save(defaultWallet);
    }

}
