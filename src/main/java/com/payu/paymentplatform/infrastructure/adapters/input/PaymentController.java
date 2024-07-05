package com.payu.paymentplatform.infrastructure.adapters.input;

import com.payu.paymentplatform.application.service.PaymentService;
import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/purchase")
    public ResponseEntity<CreditCardTransaction> purchase(@Valid @RequestBody CreditCardTransaction transaction) {
        CreditCardTransaction processedTransaction = paymentService.processPurchase(transaction);
        return new ResponseEntity<>(processedTransaction, HttpStatus.OK);
    }

    @PostMapping("/refund")
    public ResponseEntity<CreditCardTransaction> refund(@Valid @RequestBody CreditCardTransaction transaction) {
        CreditCardTransaction processedTransaction = paymentService.processRefund(transaction);
        return new ResponseEntity<>(processedTransaction, HttpStatus.OK);
    }
}