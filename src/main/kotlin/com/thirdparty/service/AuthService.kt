package com.thirdparty.service

import com.thirdparty.data.DynamoDBClient
import com.thirdparty.data.LoginRequest
import com.thirdparty.data.LoginResponse
import org.springframework.stereotype.Service

@Service
class AuthService(private val ddbClient: DynamoDBClient) {

    fun login(request: LoginRequest): LoginResponse? {
        println("Received login request: $request")
        val attributeMap = mapOf("username" to request.username!!, "password" to request.password!!, "type" to "user")

        val results: List<Map<String, String>> = ddbClient.fetch(attributeMap)
        return if(results.isEmpty()) LoginResponse("fail")
        else{
            val itemMap = results[0]
            LoginResponse(
                    status = "success",
                    role = itemMap["role"],
                    hotelId = itemMap["hotelId"],
                    partnerId = itemMap["partnerId"],
                    hotelName = itemMap["hotelName"],
                    partnerName = itemMap["partnerName"],
            )
        }
    }
}