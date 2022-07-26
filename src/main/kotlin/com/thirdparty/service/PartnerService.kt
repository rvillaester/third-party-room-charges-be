package com.thirdparty.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.DynamoDBClient
import com.thirdparty.data.GetPartnerResponse
import com.thirdparty.data.Partner
import com.thirdparty.toObjList
import org.springframework.stereotype.Service

@Service
class PartnerService(private val dynamoDBClient: DynamoDBClient, private val objectMapper: ObjectMapper) {

    fun fetch(partnerId: String): GetPartnerResponse {
        val attributes = mapOf(
            "PK" to partnerId,
            "type" to "partner"
        )
        
        val data: List<Map<String, String>> = dynamoDBClient.fetch(attributes)
        val partners = data.toObjList<Partner>(objectMapper)
        return GetPartnerResponse(partners.firstOrNull())
    }

}
