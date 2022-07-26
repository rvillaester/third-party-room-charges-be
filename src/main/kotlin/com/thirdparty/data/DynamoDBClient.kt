package com.thirdparty.data

import com.amazonaws.services.dynamodbv2.document.*
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class DynamoDBClient {
    @Autowired
    private lateinit var dynamoDB: DynamoDB

    fun create(items: Map<String, String>) {
        val table: Table = dynamoDB.getTable(TABLE_NAME)

        val dynamoDBItem = Item()

        dynamoDBItem.withPrimaryKey(PrimaryKey(TRANSACTION_ID, items.getValue(TRANSACTION_ID)))

        items
            .filter { it.key != TRANSACTION_ID }
            .forEach { item ->
                dynamoDBItem.withString(item.key, item.value)
            }

        table.putItem(dynamoDBItem)
    }

    fun fetch(items: Map<String, String>): List<Map<String, String>> {
        val transactions: MutableList<Map<String, String>> = mutableListOf()
        try {
            val table: Table = dynamoDB.getTable(TABLE_NAME)

            val spec = ScanSpec().withNameMap(items)

            table.scan(spec).forEach { currentItem ->
                transactions.add(currentItem.asMap() as Map<String, String>)
                println(currentItem.toString())
            }
        } catch (e: Exception) {
            System.err.println(e.message)
        }

        return transactions
    }

    companion object {
        private const val TABLE_NAME = "hackathon-third-party"
        private const val TRANSACTION_ID = "transactionId"
    }
}
