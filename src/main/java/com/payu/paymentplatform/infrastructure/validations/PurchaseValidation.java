package com.payu.paymentplatform.infrastructure.validations;


import com.payu.paymentplatform.domain.models.CommonProcessResult;
import com.payu.paymentplatform.domain.models.CommonValidationResult;
import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;

import java.util.Collections;
import java.util.function.Function;

@FunctionalInterface
public interface PurchaseValidation extends Function<CreditCardTransaction, CommonValidationResult> {

    static PurchaseValidation validate() {
        return request -> {
            CommonValidationResult commonValidationResult = CommonValidationResult.builder()
                    .commonProcessResult(CommonProcessResult.PROCESS_SUCCESSFULLY_COMPLETED)
                    .errors(Collections.emptyList())
                    .build();

            if (request.getAmount() == null || request.getCardNumber() == null || request.getStatus() == null) {
                commonValidationResult.getErrors().add("Invalid transaction: Missing required fields.");
                commonValidationResult.setCommonProcessResult(CommonProcessResult.PROCESS_FAILED);
            }

            return commonValidationResult;
        };
    }

}
