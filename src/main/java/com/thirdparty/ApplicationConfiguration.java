package com.thirdparty;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder builder) {
        return builder
                .build()
                .registerModule(new JavaTimeModule())
                .setDefaultLeniency(false);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
        builder.setRegion((Regions.AP_SOUTHEAST_2.getName()));
        return builder.build();
    }

    @Bean
    public DynamoDB dynamoDB(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDB(amazonDynamoDB);
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
