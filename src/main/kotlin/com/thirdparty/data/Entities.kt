package com.thirdparty.data

import java.time.LocalDate
import java.time.LocalTime

data class Transaction(
        val PK: String, // this is the transaction ID
        val detail: String?,
        val amount: Double?,
        val receiptNumber: String?,
        val customerNumber:  String?,
        val hotelId: String?,
        val partnerId: String?,
        val date: LocalDate?,
        val time: LocalTime?,
        val type: String = "transaction",
        val status: String = "pending"
)

data class Wallet(
        val PK: String, // this is the wallet id
        val roomNumber: String,
        val validTo: LocalDate,
        val firstName: String?,
        val lastName: String?,
        val date: LocalDate,
        val type: String = "wallet"
)