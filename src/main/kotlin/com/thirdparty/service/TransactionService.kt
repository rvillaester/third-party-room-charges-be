package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.*
import com.thirdparty.toDDBItem
import com.thirdparty.toMap
import com.thirdparty.toTransactionResponse
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class TransactionService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(request: GetTransactionRequest): GetTransactionResponse {
        var attributes: Map<String, String> = request.toMap(objectMapper);
        attributes.plus(Pair("type", "transaction"))
        val data: List<Map<String, String>> = dynamoDB.fetch(attributes)
        return data.toTransactionResponse(objectMapper)
    }

    fun create(request: CreateTransactionRequest): CreateTransactionResponse {
        val transactionId: String = "T-".plus(UUID.randomUUID().toString())
        val transaction = Transaction(
                transactionId,
                request.detail,
                request.amount,
                request.receiptNumber,
                request.customerNumber,
                request.hotelId,
                request.partnerId,
                LocalDate.now(),
                LocalTime.now()
        )
        dynamoDB.putItem(transaction.toDDBItem(objectMapper))
        return CreateTransactionResponse(transactionId)
    }

}