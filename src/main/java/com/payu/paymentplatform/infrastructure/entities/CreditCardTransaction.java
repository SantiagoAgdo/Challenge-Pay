package com.payu.paymentplatform.infrastructure.entities;

import com.payu.paymentplatform.infrastructure.utils.EncryptedStringConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class CreditCardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Card number is required")
    @Convert(converter = EncryptedStringConverter.class)
    private String cardNumber;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "PURCHASE|REFUND", message = "Transaction type must be either PURCHASE or REFUND")
    private String transactionType;
}