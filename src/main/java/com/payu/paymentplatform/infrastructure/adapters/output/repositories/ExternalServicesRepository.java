package com.payu.paymentplatform.infrastructure.adapters.output.repositories;

import com.payu.paymentplatform.domain.ports.output.ExternalServicePort;
import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServicesRepository implements ExternalServicePort {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean validateWithAntiFraudService(CreditCardTransaction transaction) {
        try {
            String response = restTemplate.postForObject("http://localhost:8081/anti-fraud/validate", transaction, String.class);
            return "VALID".equals(response);
        } catch (Exception e) {
            // Log error
            return false;
        }
    }

    @Override
    public boolean processWithBank(CreditCardTransaction transaction) {
        try {
            String response = restTemplate.postForObject("http://localhost:8082/bank/process", transaction, String.class);
            return "SUCCESS".equals(response);
        } catch (Exception e) {
            // Log error
            return false;
        }
    }
}
