package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.DynamoDBClient
import com.thirdparty.data.GetHotelRequest
import com.thirdparty.data.GetHotelResponse
import com.thirdparty.toHotels
import com.thirdparty.toMap
import org.springframework.stereotype.Service

@Service
class HotelService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(request: GetHotelRequest): GetHotelResponse {
        var attributes: Map<String, String> = request.toMap(objectMapper)
        attributes += Pair("type", "hotel")
        val data: List<Map<String, String>> = dynamoDB.fetch(attributes)
        val hotels = data.toHotels(objectMapper)

        return GetHotelResponse(hotels.first())
    }

}