package com.payu.paymentplatform.domain.ports.output;

import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;

public interface ExternalServicePort {

     boolean validateWithAntiFraudService(CreditCardTransaction transaction);

     boolean processWithBank(CreditCardTransaction transaction);
}
