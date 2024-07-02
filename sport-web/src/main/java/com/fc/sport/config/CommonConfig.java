package com.fc.sport.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author tiecheng
 * @version 1.0
 * @className CommonConfig
 * @description
 * @date 2024/06/26 14:38
 */
@Configuration
@ComponentScan(basePackages = {"com.fc.sport.controller", "com.fc.sport.service", "com.fc.sport.domain.service", "com.fc.sport.biz.service"})
public class CommonConfig {
}
