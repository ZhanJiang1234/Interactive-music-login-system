package com.project.music;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

class ProjectMusicApplicationTests {

    @Test
   public void contextLoads() {

        FastAutoGenerator.create("jdbc:sqlite:music.db", "root", "root")
                .globalConfig(builder -> {
                    builder.author("test") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://mp"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.project") // 设置父包名
                            .moduleName("music") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://mp")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user","music_category","music","music_collect" );
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
