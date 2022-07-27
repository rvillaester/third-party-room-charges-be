package com.thirdparty

import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.PrimaryKey
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.thirdparty.data.Hotel
import com.thirdparty.data.Partner
import com.thirdparty.data.Transaction
import com.thirdparty.data.Wallet

fun <T> T.toMap(objectMapper: ObjectMapper): Map<String, String> {
    return objectMapper.readValue(objectMapper.writeValueAsBytes(this),
            object : TypeReference<Map<String, String>>() {})
}

fun List<out Map<String, String>>.toTransactions(objectMapper: ObjectMapper): List<Transaction> {
    return this.map {
        objectMapper.readValue(objectMapper.writeValueAsBytes(it), object : TypeReference<Transaction>() {})
    }
}
fun List<out Map<String, String>>.toHotels(objectMapper: ObjectMapper): List<Hotel> {
    return this.map {
        objectMapper.readValue(objectMapper.writeValueAsBytes(it), object : TypeReference<Hotel>() {})
    }
}

fun Map<String, String>.toHotel(objectMapper: ObjectMapper): Hotel {
    return objectMapper.readValue(objectMapper.writeValueAsBytes(this), object : TypeReference<Hotel>() {})
}

fun List<out Map<String, String>>.toPartners(objectMapper: ObjectMapper): List<Partner> {
    return this.map {
        objectMapper.readValue(objectMapper.writeValueAsBytes(it), object : TypeReference<Partner>() {})
    }
}

fun List<out Map<String, String>>.toWallets(objectMapper: ObjectMapper): List<Wallet> {
    return this.map {
        objectMapper.readValue(objectMapper.writeValueAsBytes(it), object : TypeReference<Wallet>() {})
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