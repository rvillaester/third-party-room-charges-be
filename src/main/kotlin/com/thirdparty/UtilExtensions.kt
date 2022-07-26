package com.thirdparty

import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.PrimaryKey
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.GetTransactionResponse
import com.thirdparty.data.Transaction

fun <T> T.toMap(objectMapper: ObjectMapper): Map<String, String> {
    return objectMapper.readValue(objectMapper.writeValueAsBytes(this),
            object : TypeReference<Map<String, String>>() {})
}

fun List<out Map<String, String>>.toTransactionResponse(objectMapper: ObjectMapper): GetTransactionResponse {
    val transactions: List<Transaction> = this.map {
        objectMapper.readValue(objectMapper.writeValueAsBytes(it), object : TypeReference<Transaction>() {})
    }
    return GetTransactionResponse(transactions)
}

fun <T> T.toDDBItem(objectMapper: ObjectMapper, key: String = "pk"): Item {
    val items = this.toMap(objectMapper)
    val dynamoDBItem = Item()

    dynamoDBItem.withPrimaryKey(
            PrimaryKey(
                    "PK",
                    items[key]
            ))

    items.filter { it.key != key && it.value != null }
            .forEach { (key, value) ->
                dynamoDBItem.withString(key, value)
            }
    return dynamoDBItem
}