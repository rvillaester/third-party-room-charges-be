package com.thirdparty.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.thirdparty.data.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService {

    @Autowired
    private lateinit var dynamoDB: DynamoDBClient

    private val objectMapper = jacksonObjectMapper()

    fun fetch(request: GetTransactionRequest): GetTransactionResponse {
        val data = dynamoDB.fetch(request)
        val transactionList = mutableListOf<Transaction>()

        data.forEach { item ->
            transactionList.add(Transaction(item))
        }

        return GetTransactionResponse(transactionList)
    }

    fun create(request: CreateTransactionRequest): CreateTransactionResponse {
        val referenceNo: String = UUID.randomUUID().toString()
        val transaction = Transaction(
            request.detail,
            request.amount,
            request.receiptNumber,
            request.customerNumber,
            request.hotelId,
            request.partnerId,
            referenceNo
        )
        dynamoDB.create(transaction)
        return CreateTransactionResponse(referenceNo)
    }

    private fun <T> convertToMap(from: T): Map<String, String> {
        return objectMapper.readValue(objectMapper.writeValueAsBytes(from),
            object : TypeReference<Map<String, String>>() {})
    }

    private fun convertToGetTransactionResponse(from: List<Map<String, String>>): GetTransactionResponse {
        val transactions: List<Transaction> = from.map {
            objectMapper.readValue(objectMapper.writeValueAsBytes(it), object : TypeReference<Transaction>() {})
        }
        return GetTransactionResponse(transactions)
    }
}