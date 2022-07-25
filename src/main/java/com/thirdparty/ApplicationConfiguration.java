package com.thirdparty;

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
}
