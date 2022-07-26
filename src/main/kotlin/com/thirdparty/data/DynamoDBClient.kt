package com.thirdparty.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class DynamoDBClient {

    @Autowired
    private lateinit var dynamoDBMapper: DynamoDBMapper

    fun create(transaction: Transaction) {
        val transactionItem = TransactionItem(transaction)

        dynamoDBMapper.save(transactionItem)
    }

    fun fetch(transactionRequest: GetTransactionRequest): List<TransactionItem> {

        val eav = HashMap<String, AttributeValue>()
        val filterExpressions = mutableListOf<String>()

        if (transactionRequest.customerNumber != null) {
            eav[":customerNumber"] = AttributeValue().withS(transactionRequest.customerNumber)
            filterExpressions.add("customerNumber = :customerNumber")
        }

        if (transactionRequest.hotelId != null) {
            eav[":hotelId"] = AttributeValue().withS(transactionRequest.hotelId)
            filterExpressions.add("hotelId = :hotelId")
        }

        if (transactionRequest.partnerId != null) {
            eav[":partnerId"] = AttributeValue().withS(transactionRequest.partnerId)
            filterExpressions.add("partnerId = :partnerId")
        }


        val scanExpression = DynamoDBScanExpression()
            .withFilterExpression(filterExpressions.joinToString(" and "))
            .withExpressionAttributeValues(eav)

        return dynamoDBMapper
            .scan(TransactionItem::class.java, scanExpression)
    }
}
