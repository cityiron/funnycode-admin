package com.fc.system.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.fc.common.utils.model.BaseDO;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * @author tiecheng
 * @version 1.0
 * @className Generator
 * @description
 * @date 2024/06/26 10:37
 */
public class Generator {

    @Test
    public void testSimpleGenerator() {
        Path dir = Paths.get(System.getProperty("user.dir"));
        // 包路径
        String path = dir + "/src/main/java";
        // XML文件的路径
        String mapperXmlPath = dir + "/src/main/resources/mapper";
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/funnycode_admin?useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&useSSL=false&remarks=true&useInformationSchema=true&tinyInt1isBit=true", "root", "12345")
                .globalConfig(builder -> builder
                        .author("tiecheng")
                        .outputDir(path)
                        .disableOpenDir()
                        .dateType(DateType.ONLY_DATE)
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.fc")
                        .moduleName("sport")
                        .entity("entity")
//                        .other("model.dto")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlPath))
                )
                .strategyConfig(builder -> builder
                        .addTablePrefix("fc_")
                        .entityBuilder()
                        .enableTableFieldAnnotation()
                        .enableRemoveIsPrefix()
                        .superClass(BaseDO.class)
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        .addTableFills(new Column("modify_time", FieldFill.INSERT_UPDATE))
                        .addSuperEntityColumns("id", "is_deleted", "creator", "modifier", "create_time", "modify_time")
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle())
//                .injectionConfig(consumer -> {
//                    Map<String, String> customFile = new HashMap<>();
//                    // DTO
//                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
//                    consumer.customFile(customFile);
//                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
