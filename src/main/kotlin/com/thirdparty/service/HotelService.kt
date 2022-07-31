package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.DynamoDBClient
import com.thirdparty.data.GetHotelResponse
import com.thirdparty.data.Hotel
import com.thirdparty.toObjList
import org.springframework.stereotype.Service

@Service
class HotelService(private val dynamoDB: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(hotelId: String): GetHotelResponse {
        val attributes = mapOf(
            "PK" to hotelId,
            "type" to "hotel"
        )

        val data: List<Map<String, String>> = dynamoDB.fetch(attributes)
        val hotels = data.toObjList<Hotel>(objectMapper)

        return GetHotelResponse(hotels.firstOrNull())
    }

}