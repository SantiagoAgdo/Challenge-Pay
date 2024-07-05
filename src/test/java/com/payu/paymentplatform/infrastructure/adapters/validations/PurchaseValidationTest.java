package com.payu.paymentplatform.infrastructure.adapters.validations;

import com.payu.paymentplatform.domain.models.CommonProcessResult;
import com.payu.paymentplatform.domain.models.CommonValidationResult;
import com.payu.paymentplatform.infrastructure.entities.CreditCardTransaction;
import com.payu.paymentplatform.infrastructure.validations.PurchaseValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseValidationTest {

    @Test
    void testValidate() {

        CreditCardTransaction inputTransaction = new CreditCardTransaction();
        inputTransaction.setCardNumber("1234567890123456");
        inputTransaction.setAmount(100.00);
        inputTransaction.setStatus("PENDING");
        inputTransaction.setTransactionType("PURCHASE");

        PurchaseValidation validation = PurchaseValidation.validate();

        CommonValidationResult validationResult = validation.apply(inputTransaction);

        assertAll("Validation result",
                () -> assertEquals(CommonProcessResult.PROCESS_SUCCESSFULLY_COMPLETED, validationResult.getCommonProcessResult()),
                () -> assertTrue(validationResult.getErrors().isEmpty()));
    }
}
