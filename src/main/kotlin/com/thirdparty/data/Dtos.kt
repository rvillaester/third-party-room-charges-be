package com.thirdparty.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.LocalTime

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
data class Transaction(
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
    val partnerId: String?,
    @NotBlank
    val referenceNo: String,
) {
    constructor(transactionItem: TransactionItem): this(
        referenceNo = transactionItem.referenceNo as String,
        //date = LocalDate.now().toString(),
        hotelId = transactionItem.hotelId,
        partnerId = transactionItem.partnerId,
        receiptNumber = transactionItem.receiptNumber,
        customerNumber = transactionItem.customerNumber,
        amount = transactionItem.amount,
        detail = transactionItem.detail,
        //time = LocalTime.now().toString()
    )
}

@DynamoDBTable(tableName = "hackathon-third-party")
data class TransactionItem(
    @DynamoDBHashKey(attributeName = "PK")
    var referenceNo: String? = null,
    var date: String? = null,
    var hotelId: String? = null,
    var partnerId: String? = null,
    var receiptNumber: String? = null,
    var customerNumber: String? = null,
    var amount: Double? = null,
    var detail: String? = null,
    var time: String? = null
) {
    constructor(transaction: Transaction) : this(
        referenceNo = transaction.referenceNo,
        date = LocalDate.now().toString(),
        hotelId = transaction.hotelId,
        partnerId = transaction.partnerId,
        receiptNumber = transaction.receiptNumber,
        customerNumber = transaction.customerNumber,
        amount = transaction.amount,
        detail = transaction.detail,
        time = LocalTime.now().toString()
    )
}
