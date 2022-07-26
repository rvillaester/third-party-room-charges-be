package com.thirdparty.service

import com.thirdparty.data.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService {

    @Autowired
    private lateinit var dynamoDB: DynamoDB

    fun fetch(): GetTransactionResponse {
        return GetTransactionResponse("ref23543543")
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
        dynamoDB.save(transaction)
        return CreateTransactionResponse(referenceNo)
    }
}