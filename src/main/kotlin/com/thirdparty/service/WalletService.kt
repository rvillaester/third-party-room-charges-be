package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.CreateWalletRequest
import com.thirdparty.data.CreateWalletResponse
import com.thirdparty.data.DynamoDBClient
import com.thirdparty.data.Wallet
import com.thirdparty.toDDBItem
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class WalletService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun create(request: CreateWalletRequest): CreateWalletResponse {
        val id: String = "W-".plus(UUID.randomUUID().toString())
        val wallet = Wallet(
                id,
                request.roomNumber!!,
                request.validTo!!,
                request.firstname,
                request.lastname,
                LocalDate.now()
        )

        dynamoDB.putItem(wallet.toDDBItem(objectMapper))
        return CreateWalletResponse(id)
    }
}