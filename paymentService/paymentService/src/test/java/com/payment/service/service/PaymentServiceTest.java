package com.payment.service.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.payment.service.dbo.Wallet;
import com.payment.service.exception.InsufficientBalanceException;
import com.payment.service.exception.NotFoundException;
import com.payment.service.repository.WalletRepository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(12345.0);
        wallet.setCardNumber("XXXXXXXXXXX2341");
        wallet.setIsDefault(false);
        wallet.setId(1L);
        wallet.setUserId(1L);
        when(sequenceGenerator.generateSequence("wallet_sequence")).thenReturn(1L);
        paymentService.addWallet(wallet);
        verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    public void testDeleteWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(12345.0);
        wallet.setCardNumber("XXXXXXXXXXX2341");
        wallet.setIsDefault(false);
        wallet.setId(1L);
        wallet.setUserId(1L);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        doNothing().when(walletRepository).deleteById(1L);
        paymentService.addWallet(wallet);
    }

    @Test
    public void testWalletIdNotFound() throws NotFoundException {
        Wallet wallet = new Wallet();
        wallet.setBalance(12345.0);
        wallet.setCardNumber("XXXXXXXXXXX2341");
        wallet.setIsDefault(false);
        wallet.setId(1L);
        wallet.setUserId(1L);
        when(walletRepository.findById(1L)).thenReturn(Optional.empty());
        String errorMessage = String.format("wallet is not found for id ", 1L);
        try {
            paymentService.deleteWallet(1L);
            fail("Fails to raise exception");
        } catch(NotFoundException ex) {
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void testMakePayment() throws InsufficientBalanceException {
         Wallet wallet = new Wallet();
        wallet.setBalance(12345.0);
        wallet.setCardNumber("XXXXXXXXXXX2341");
        wallet.setIsDefault(false);
        wallet.setId(1L);
        wallet.setUserId(1L);
        String expectedMessage = String.format("Payment done successfully. Balance in your account is %s", 9688.0);
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        when(walletRepository.save(wallet)).thenReturn(wallet);
        String actualMessage = paymentService.makePayment(1L, 2657.0);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testInsufficientBalance() throws InsufficientBalanceException {
         Wallet wallet = new Wallet();
        wallet.setBalance(12.0);
        wallet.setCardNumber("XXXXXXXXXXX2341");
        wallet.setIsDefault(false);
        wallet.setId(1L);
        wallet.setUserId(1L);
        String expectedMessage = "Insufficient balance in the wallet";
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        try {
             paymentService.makePayment(1L, 2657.0);
             fail("Failed to raise exception");
        } catch(InsufficientBalanceException ex) {
             assertEquals(expectedMessage, ex.getMessage());
        }
    }

    @Test
    void testSetDefaultWallet() {
    	Query query = new Query(Criteria.where("userId").is(123L).and("isDefault").is(false));
        Wallet defaultWallet = new Wallet();
        when(mongoTemplate.findOne(query, Wallet.class)).thenReturn(defaultWallet);
        when(walletRepository.save(any(Wallet.class))).thenReturn(defaultWallet);

        Wallet result = paymentService.setDefaultWallet(456L, 123L);

        assertSame(defaultWallet, result);
        assertTrue(defaultWallet.getIsDefault());
    }
}
