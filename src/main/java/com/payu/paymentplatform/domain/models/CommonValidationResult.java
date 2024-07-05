package com.payu.paymentplatform.domain.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class CommonValidationResult {

    private CommonProcessResult commonProcessResult;
    private List<String> errors;
}
