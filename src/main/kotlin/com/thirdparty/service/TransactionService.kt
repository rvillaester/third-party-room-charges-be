package com.thirdparty.service

import com.thirdparty.data.GetTransactionRequest
import com.thirdparty.data.GetTransactionResponse
import org.springframework.stereotype.Service

@Service
class TransactionService {

    fun fetch(request: GetTransactionRequest): GetTransactionResponse {
        return GetTransactionResponse("ref23543543")
    }
}