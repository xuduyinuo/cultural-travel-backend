package com.xudu.culturaltravelbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xudu.culturaltravelbackend.mapper")
@SpringBootApplication
public class CulturalTravelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CulturalTravelBackendApplication.class, args);
    }

}
