package com.thirdparty.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GetTransactionRequest {
    @NotBlank
    private LocalDate date;
    @NotBlank
    private String customerNumber;
}
