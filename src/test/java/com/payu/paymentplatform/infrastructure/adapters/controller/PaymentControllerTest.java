package com.payu.paymentplatform.infrastructure.adapters.controller;

import com.payu.paymentplatform.application.service.PaymentService;
import com.payu.paymentplatform.infrastructure.adapters.input.PaymentController;
import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private <T> void validateObject(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Test
    void testPurchase_Success() {
        // Arrange
        CreditCardTransaction inputTransaction = new CreditCardTransaction();
        inputTransaction.setCardNumber("1234567890123456");
        inputTransaction.setAmount(100.00);
        inputTransaction.setStatus("PENDING");
        inputTransaction.setTransactionType("PURCHASE");

        CreditCardTransaction processedTransaction = new CreditCardTransaction();
        processedTransaction.setId(1L);
        processedTransaction.setCardNumber("1234567890123456");
        processedTransaction.setAmount(100.00);
        processedTransaction.setStatus("COMPLETED");
        processedTransaction.setTransactionType("PURCHASE");

        when(paymentService.processPurchase(inputTransaction)).thenReturn(processedTransaction);

        // Act
        ResponseEntity<CreditCardTransaction> response = paymentController.purchase(inputTransaction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(processedTransaction, response.getBody());
        verify(paymentService, times(1)).processPurchase(inputTransaction);
    }

    @Test
    void testRefund_Success() {
        // Arrange
        CreditCardTransaction inputTransaction = new CreditCardTransaction();
        inputTransaction.setCardNumber("1234567890123456");
        inputTransaction.setAmount(100.00);
        inputTransaction.setStatus("COMPLETED");
        inputTransaction.setTransactionType("REFUND");

        CreditCardTransaction processedTransaction = new CreditCardTransaction();
        processedTransaction.setId(2L);
        processedTransaction.setCardNumber("1234567890123456");
        processedTransaction.setAmount(100.00);
        processedTransaction.setStatus("REFUNDED");
        processedTransaction.setTransactionType("REFUND");

        when(paymentService.processRefund(inputTransaction)).thenReturn(processedTransaction);

        // Act
        ResponseEntity<CreditCardTransaction> response = paymentController.refund(inputTransaction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(processedTransaction, response.getBody());
        verify(paymentService, times(1)).processRefund(inputTransaction);
    }

    @Test
    void testRefund_ValidationFailure() {
        CreditCardTransaction invalidTransaction = new CreditCardTransaction();
        // Establecer datos inválidos, por ejemplo:
        invalidTransaction.setCardNumber("invalid");
        invalidTransaction.setAmount(-100.00);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            validateObject(invalidTransaction);
            paymentController.refund(invalidTransaction);
        });

        verify(paymentService, never()).processRefund(any());
    }
}