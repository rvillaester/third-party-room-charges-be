package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.*
import com.thirdparty.toDDBItem
import com.thirdparty.toWallets
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class WalletService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun create(request: CreateWalletRequest): CreateWalletResponse {
        println("Payload: $request")
        val id: String = "W-".plus(UUID.randomUUID().toString())
        val wallet = Wallet(
                id,
                request.hotelId!!,
                request.roomNumber!!,
                request.validTo!!,
                request.firstName,
                request.lastName,
                LocalDate.now()
        )

        dynamoDB.putItem(wallet.toDDBItem(objectMapper))
        return CreateWalletResponse(id)
    }

    fun fetch(request: GetWalletRequest): GetWalletResponse {
        println("Payload: $request")
        val operator = if(request.active) ">=" else "<"
        val filterExpression = "#type = :type and #validTo $operator :validTo and #hotelId = :hotelId"
        val nameMap = mapOf("#type" to "type", "#validTo" to "validTo", "#hotelId" to "hotelId")
        val valueMap = mapOf(":type" to "wallet", ":validTo" to LocalDate.now().toString(), ":hotelId" to request.hotelId)
        val data: List<Map<String, String>> = dynamoDB.fetch(filterExpression, nameMap, valueMap)
        val wallets = data.toWallets(objectMapper)
        return GetWalletResponse(wallets)
    }
}