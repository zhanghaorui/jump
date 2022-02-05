package com.zhang.redis.config;

import com.zhang.redis.common.bean.SpringApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {WebConfiguration.class, RedisConfig.class, SpringApplicationContext.class})
public class CommonAutoConfiguration {
}
