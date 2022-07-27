package com.thirdparty.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GetTransactionResponse(
    val transactions: List<Transaction> = emptyList()
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
    val detail: String?,
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
data class CreateWalletRequest(
        val firstname: String?,
        val lastname: String?,
        @NotNull
        val validTo: LocalDate?,
        @NotNull
        val hotelId: String?,
        @NotNull
        val roomNumber: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateWalletResponse(
        val walletId: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginRequest(
        val username: String?,
        val password: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginResponse(
        val status: String,
        val role: String? = null,
        val hotelId: String? = null,
        val partnerId: String? = null,
        val hotelName: String? = null,
        val partnerName: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GetWalletRequest (
    val hotelId: String,
    val active: Boolean
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GetWalletResponse (
    val wallets: List<Wallet> = emptyList()
)