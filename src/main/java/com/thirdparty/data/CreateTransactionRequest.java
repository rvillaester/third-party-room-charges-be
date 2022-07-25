package com.thirdparty.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CreateTransactionRequest {
    @NotBlank
    private String detail;
    @NotBlank
    private Double amount;
    private String receiptNumber;
    @NotBlank
    private String customerNumber;
}
