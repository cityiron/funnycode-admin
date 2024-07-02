package com.fc.system;

import com.fc.sport.config.CommonConfig;
import com.fc.system.config.MybatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tiecheng
 * @version 1.0
 * @className SystemApplication
 * @description 启动类
 * @date 2024/06/25 14:30
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.fc.*.config"})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }

}
