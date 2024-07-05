package com.payu.paymentplatform.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class CommonValidationResult {

    private CommonProcessResult commonProcessResult;
    private List<String> errors;
}
