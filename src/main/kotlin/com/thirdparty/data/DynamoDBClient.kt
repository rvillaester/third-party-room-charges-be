package com.thirdparty.data

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec
import com.thirdparty.TABLE_NAME
import org.springframework.stereotype.Component

@Component
class DynamoDBClient(private val dynamoDB: DynamoDB) {

    fun putItem(item: Item, table: String = TABLE_NAME) {
        val table: Table = dynamoDB.getTable(table)
        table.putItem(item)
    }

    fun fetch(items: Map<String, String>): List<Map<String, String>> {
        val itemMap: MutableList<Map<String, String>> = mutableListOf()
        try {
            val table: Table = dynamoDB.getTable(TABLE_NAME)
            val filterExpression = items.map {
                "#".plus(it.key).plus(" = ").plus(":").plus(it.key)
            }.joinToString(separator = " and ")

            val valueMap = items.mapKeys {
                ":".plus(it.key)
            }

            val nameMap = items.map{
                "#".plus(it.key) to it.key
            }.toMap()

            val spec = ScanSpec()
                    .withFilterExpression(filterExpression)
                    .withValueMap(valueMap)
                    .withNameMap(nameMap)

            table.scan(spec).forEach { currentItem ->
                itemMap.add(currentItem.asMap() as Map<String, String>)
            }
        } catch (e: Exception) {
            System.err.println(e.message)
        }

        return itemMap
    }
}
