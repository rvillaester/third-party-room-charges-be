package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.DynamoDBClient
import com.thirdparty.data.GetHotelResponse
import com.thirdparty.toHotels
import org.springframework.stereotype.Service

@Service
class HotelService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(hotelId: String): GetHotelResponse {
        val attributes = mapOf(
            "PK" to hotelId,
            "type" to "hotel"
        )

        val data: List<Map<String, String>> = dynamoDB.fetch(attributes)
        val hotels = data.toHotels(objectMapper)

        return GetHotelResponse(hotels.firstOrNull())
    }

}