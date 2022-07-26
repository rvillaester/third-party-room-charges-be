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
    val date: LocalDate? = null,
    val customerNumber: String? = null,
    val hotelId: String? = null,
    val partnerId: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateTransactionRequest (
    @NotBlank
    val detail: Map<String, Double>?,
    @NotBlank
    val amount: Double?,
    @NotBlank
    val receiptNumber: String?,
    @NotBlank
    val customerNumber:  String?,
    @NotBlank
    val hotelId: String?,
    @NotBlank
    val partnerId: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateTransactionResponse(
    val referenceNo: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Transaction(
    @NotBlank
    val detail: Map<String, Double>?,
    @NotBlank
    val amount: Double?,
    @NotBlank
    val receiptNumber: String?,
    @NotBlank
    val customerNumber:  String?,
    @NotBlank
    val hotelId: String?,
    @NotBlank
    val partnerId: String?,
    @NotBlank
    val referenceNo: String
)
