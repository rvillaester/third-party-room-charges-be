package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.*
import com.thirdparty.toDDBItem
import com.thirdparty.toObjList
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

    fun fetch(request: GetWalletRequest): GetWalletResponse {
        val operator = if(request.active) ">=" else "<"
        val filterExpression = "#type = :type and #validTo $operator :validTo"
        val nameMap = mapOf("#type" to "type", "#validTo" to "validTo")
        val valueMap = mapOf(":type" to "wallet", ":validTo" to LocalDate.now().toString())
        val data: List<Map<String, String>> = dynamoDB.fetch(filterExpression, nameMap, valueMap)
        val wallets = data.toObjList<Wallet>(objectMapper)
        return GetWalletResponse(wallets)
    }
}