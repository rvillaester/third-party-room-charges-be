package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.*
import com.thirdparty.toDDBItem
import com.thirdparty.toMap
import com.thirdparty.toTransactions
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class TransactionService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(request: GetTransactionRequest): GetTransactionResponse {
        var attributes: Map<String, String> = request.toMap(objectMapper);
        attributes += Pair("type", "transaction")
        val data: List<Map<String, String>> = dynamoDB.fetch(attributes)
        val transactions = data.toTransactions(objectMapper)
        return GetTransactionResponse(transactions)
    }

    fun create(request: CreateTransactionRequest): CreateTransactionResponse {
        val transactionId: String = "T-".plus(UUID.randomUUID().toString())
        val transaction = Transaction(
                transactionId,
                request.detail,
                request.amount,
                request.receiptNumber,
                request.walletId,
                request.hotelId,
                request.partnerId,
                LocalDate.now(),
                LocalTime.now()
        )
        dynamoDB.putItem(transaction.toDDBItem(objectMapper))
        return CreateTransactionResponse(transactionId)
    }

}