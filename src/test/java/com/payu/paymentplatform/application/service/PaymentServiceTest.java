//package com.payu.paymentplatform.application.service;
//
//import com.payu.paymentplatform.domain.ports.input.CreditCardTransactionRepository;
//import com.payu.paymentplatform.domain.ports.output.ExternalServicePort;
//import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class PaymentServiceTest {
//
//    @InjectMocks
//    private PaymentService paymentService;
//
//    @Mock
//    private CreditCardTransactionRepository repository;
//
//    @Mock
//    private ExternalServicePort externalService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void processPurchase_ValidationFailed() {
//        CreditCardTransaction transaction = new CreditCardTransaction();
//        transaction.setAmount(null); // This will cause validation to fail
//
//        when(repository.save(any(CreditCardTransaction.class))).thenReturn(transaction);
//
//        CreditCardTransaction result = paymentService.processPurchase(transaction);
//
//        assertEquals("VALIDATION_FAILED", result.getStatus());
//        verify(repository, times(1)).save(transaction);
//        verify(externalService, never()).validateWithAntiFraudService(any());
//        verify(externalService, never()).processWithBank(any());
//    }
//
//    @Test
//    void processPurchase_FraudRejected() {
//        CreditCardTransaction transaction = new CreditCardTransaction();
//        transaction.setAmount(100.0);
//        transaction.setCardNumber("1234567890123456");
//
//        when(externalService.validateWithAntiFraudService(transaction)).thenReturn(false);
//        when(repository.save(any(CreditCardTransaction.class))).thenReturn(transaction);
//
//        CreditCardTransaction result = paymentService.processPurchase(transaction);
//
//        assertEquals("FRAUD_REJECTED", result.getStatus());
//        verify(repository, times(1)).save(transaction);
//        verify(externalService, times(1)).validateWithAntiFraudService(transaction);
//        verify(externalService, never()).processWithBank(any());
//    }
//
//    @Test
//    void processPurchase_BankFailed() {
//        CreditCardTransaction transaction = new CreditCardTransaction();
//        transaction.setAmount(100.0);
//        transaction.setCardNumber("1234567890123456");
//
//        when(externalService.validateWithAntiFraudService(transaction)).thenReturn(true);
//        when(externalService.processWithBank(transaction)).thenReturn(false);
//        when(repository.save(any(CreditCardTransaction.class))).thenReturn(transaction);
//
//        CreditCardTransaction result = paymentService.processPurchase(transaction);
//
//        assertEquals("BANK_FAILED", result.getStatus());
//        verify(repository, times(1)).save(transaction);
//        verify(externalService, times(1)).validateWithAntiFraudService(transaction);
//        verify(externalService, times(1)).processWithBank(transaction);
//    }
//
//    @Test
//    void processPurchase_Completed() {
//        CreditCardTransaction transaction = new CreditCardTransaction();
//        transaction.setAmount(100.0);
//        transaction.setCardNumber("1234567890123456");
//
//        when(externalService.validateWithAntiFraudService(transaction)).thenReturn(true);
//        when(externalService.processWithBank(transaction)).thenReturn(true);
//        when(repository.save(any(CreditCardTransaction.class))).thenReturn(transaction);
//
//        CreditCardTransaction result = paymentService.processPurchase(transaction);
//
//        assertEquals("COMPLETED", result.getStatus());
//        verify(repository, times(1)).save(transaction);
//        verify(externalService, times(1)).validateWithAntiFraudService(transaction);
//        verify(externalService, times(1)).processWithBank(transaction);
//    }
//
//    @Test
//    void processRefund() {
//        CreditCardTransaction transaction = new CreditCardTransaction();
//        transaction.setAmount(100.0);
//        transaction.setCardNumber("1234567890123456");
//
//        when(externalService.validateWithAntiFraudService(transaction)).thenReturn(true);
//        when(externalService.processWithBank(transaction)).thenReturn(true);
//        when(repository.save(any(CreditCardTransaction.class))).thenReturn(transaction);
//
//        CreditCardTransaction result = paymentService.processRefund(transaction);
//
//        assertEquals("COMPLETED", result.getStatus());
//        assertEquals("REFUND", result.getTransactionType());
//        verify(repository, times(1)).save(transaction);
//        verify(externalService, times(1)).validateWithAntiFraudService(transaction);
//        verify(externalService, times(1)).processWithBank(transaction);
//    }
//}
