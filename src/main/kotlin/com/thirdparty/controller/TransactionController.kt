package com.thirdparty.controller

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.thirdparty.data.CreateTransactionRequest
import com.thirdparty.service.TransactionService
import com.thirdparty.data.GetTransactionRequest
import com.thirdparty.data.GetTransactionResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction")
class TransactionController(private val service: TransactionService) {

    @Autowired
    private lateinit var amazonDynamoDB: AmazonDynamoDB

    @PostMapping("create")
    fun create(@Valid @RequestBody request: CreateTransactionRequest): String? {

        return "Save transaction to Dynamo DB and returns reference number"
    }

    @PostMapping("fetch", consumes = ["application/json"], produces = ["application/json"])
    fun fetch(@Valid @RequestBody request: GetTransactionRequest): GetTransactionResponse? {
        return service.fetch(request)
    }
}