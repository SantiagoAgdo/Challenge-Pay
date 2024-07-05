package com.payu.paymentplatform.domain.ports.input;

import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, Long> {
}
