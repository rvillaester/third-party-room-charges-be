package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.DynamoDBClient
import com.thirdparty.data.GetPartnerRequest
import com.thirdparty.data.GetPartnerResponse
import com.thirdparty.toMap
import com.thirdparty.toPartners
import org.springframework.stereotype.Service

@Service
class PartnerService(private val dynamoDBClient: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(request: GetPartnerRequest): GetPartnerResponse {
        var attributes: Map<String, String> = request.toMap(objectMapper)
        attributes += Pair("type", "partner")
        val data: List<Map<String, String>> = dynamoDBClient.fetch(attributes)
        val partners = data.toPartners(objectMapper)
        return GetPartnerResponse(partners.first())
    }

}
