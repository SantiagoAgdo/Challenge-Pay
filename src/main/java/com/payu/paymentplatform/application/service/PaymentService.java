package com.payu.paymentplatform.application.service;

import com.payu.paymentplatform.domain.models.CommonProcessResult;
import com.payu.paymentplatform.domain.models.CommonValidationResult;
import com.payu.paymentplatform.domain.ports.input.CreditCardTransactionRepository;
import com.payu.paymentplatform.domain.ports.output.ExternalServicePort;
import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;
import com.payu.paymentplatform.infrastructure.validations.PurchaseValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private CreditCardTransactionRepository repository;

    @Autowired
    private ExternalServicePort externalService;

    public CreditCardTransaction processPurchase(CreditCardTransaction transaction) {

        CommonValidationResult validationResult = PurchaseValidation.validate().apply(transaction);

        if (CommonProcessResult.PROCESS_FAILED.equals(validationResult.getCommonProcessResult())) {
            transaction.setStatus("VALIDATION_FAILED");
            return repository.save(transaction);
        }

        boolean isFraudValid = externalService.validateWithAntiFraudService(transaction);
        if (!isFraudValid) {
            transaction.setStatus("FRAUD_REJECTED");
            return repository.save(transaction);
        }

        boolean isProcessed = externalService.processWithBank(transaction);
        transaction.setStatus(isProcessed ? "COMPLETED" : "BANK_FAILED");
        return repository.save(transaction);
    }

    public CreditCardTransaction processRefund(CreditCardTransaction transaction) {
        transaction.setTransactionType("REFUND");
        return processPurchase(transaction);
    }
}
