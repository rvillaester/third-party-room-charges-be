package com.thirdparty.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate
import java.time.LocalTime

data class Transaction(
        val PK: String, // this is the transaction ID
        val detail: String?,
        val amount: Double?,
        val receiptNumber: String?,
        val walletId:  String?,
        val hotelId: String?,
        val partnerId: String?,
        val date: LocalDate?,
        val time: LocalTime?,
        val type: String = "transaction",
        val status: String = "pending"
)

data class Wallet(
        val PK: String, // this is the wallet id
        val hotelId: String,
        val roomNumber: String,
        val validTo: LocalDate,
        val firstName: String?,
        val lastName: String?,
        val date: LocalDate,
        val type: String = "wallet"
)

data class Hotel(
        val PK: String, // this is the hotel id
        val name: String?,
        val partners: List<Map<String, String>>?,
        val type: String = "hotel"
)

data class Partner(
        val PK: String,
        val name: String?,
        val hotels: List<Map<String, String>>?,
        val type: String = "partner"
)
