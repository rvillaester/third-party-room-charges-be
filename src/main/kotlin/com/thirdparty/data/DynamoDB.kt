package com.thirdparty.data

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class DynamoDB {

    @Autowired
    private lateinit var amazonDynamoDB: AmazonDynamoDB

    fun save(transaction: Transaction) {

    }
}