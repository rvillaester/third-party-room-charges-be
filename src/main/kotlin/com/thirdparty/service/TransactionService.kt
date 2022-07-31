package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.*
import com.thirdparty.toDDBItem
import com.thirdparty.toMap
import com.thirdparty.toObjList
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class TransactionService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(request: GetTransactionRequest): GetTransactionResponse {
        println("Payload: $request")
        var attributes: Map<String, String> = request.toMap(objectMapper);
        attributes += Pair("type", "transaction")
        val data: List<Map<String, String>> = dynamoDB.fetch(attributes)
        val transactions = data.toObjList<Transaction>(objectMapper)
        return GetTransactionResponse(transactions.sortedBy { "" + it.date + it.time })
    }

    fun create(request: CreateTransactionRequest): CreateTransactionResponse {
        println("Payload: $request")
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