package com.thirdparty

import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.PrimaryKey
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

fun <T> T.toMap(objectMapper: ObjectMapper): Map<String, String> {
    return objectMapper.readValue(objectMapper.writeValueAsBytes(this),
            object : TypeReference<Map<String, String>>() {})
}

fun <T> List<out Map<String, String>>.toObjList(objectMapper: ObjectMapper): List<T> {
    return this.map {
        objectMapper.readValue(objectMapper.writeValueAsBytes(it), object : TypeReference<T>() {})
    }
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