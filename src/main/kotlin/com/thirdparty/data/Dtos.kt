package com.thirdparty.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GetTransactionResponse(
        val referenceNo: String
        // add more fields
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GetTransactionRequest (
    @NotBlank
    val date: LocalDate? = null,
    @NotBlank
    val customerNumber: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateTransactionRequest (
    val detail: @NotBlank String?,
    val amount: @NotBlank Double?,
    val receiptNumber: String?,
    val customerNumber: @NotBlank String?
)
