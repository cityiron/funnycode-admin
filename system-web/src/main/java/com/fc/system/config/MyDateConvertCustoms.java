package com.fc.system.config;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tiecheng
 * @version 1.0
 * @className MyDateConvertCustoms
 * @description
 * @date 2024/06/27 13:45
 */
@Configuration
public class MyDateConvertCustoms implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        // 覆盖默认的Date反序列化，第一个参数为需要反序列化的类，第二个为具体的序列化格式
        jacksonObjectMapperBuilder.deserializerByType(
                Date.class
                , new DateDeserializers.DateDeserializer(
                        DateDeserializers.DateDeserializer.instance
                        , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        , null));
    }

}
