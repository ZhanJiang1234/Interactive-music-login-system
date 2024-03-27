package com.project.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.project.music.mapper")
@ServletComponentScan
public class ProjectMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMusicApplication.class, args);
    }

}
