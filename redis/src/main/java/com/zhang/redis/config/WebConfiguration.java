package com.zhang.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.redis.cache.impl.DateProviderImpl;
import com.zhang.redis.common.advince.GlobalResponseBodyAdvice;
import com.zhang.redis.common.core.DateProvider;
import com.zhang.redis.common.core.ObjectMapperImpl;
import com.zhang.redis.common.exception.GlobalExceptionHandler;
import com.zhang.redis.common.json.JsonExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {GlobalExceptionHandler.class, GlobalResponseBodyAdvice.class})
public class WebConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImpl();
    }

    @Bean
    public DateProvider dateProvider() {
        return new DateProviderImpl();
    }

    @Bean
    public JsonExtractor jsonExtractor() {
        return new JsonExtractor();
    }
}
