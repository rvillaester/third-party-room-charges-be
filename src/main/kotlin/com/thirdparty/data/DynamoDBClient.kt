package com.thirdparty.data

import com.amazonaws.services.dynamodbv2.document.*
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList


@Component
class DynamoDBClient {
    @Autowired
    private lateinit var dynamoDB: com.amazonaws.services.dynamodbv2.document.DynamoDB

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

    /*fun fetch(items: Map<String, String>): List<Map<String, String>> {
        val transactions: MutableList<MutableMap<String, String>> = MutableList<MutableMap<String, String>>(1)
        try {
            val table: Table = dynamoDB.getTable(TABLE_NAME)

            val spec = ScanSpec().withNameMap(items)



            val items: ItemCollection<ScanOutcome> = table.scan(spec)
            items.forEach { currentItem ->
                transactions.add(currentItem.asMap() as Map<String, String>)
                println(currentItem.toString())
            }
        } catch (e: Exception) {
            System.err.println(e.message)
        }
    }*/

    companion object {
        private const val TABLE_NAME = "hackathon-third-party"
        private const val TRANSACTION_ID = "transactionId"
    }
}
