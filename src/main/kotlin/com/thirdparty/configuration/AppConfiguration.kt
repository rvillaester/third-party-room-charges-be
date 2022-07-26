package com.thirdparty.configuration

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppConfiguration () {
    @Bean
    open fun amazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder.defaultClient()
    }
}
