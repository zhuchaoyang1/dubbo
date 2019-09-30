package com.example.cosumer;

import com.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CosumerApplication {

    @Autowired
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(CosumerApplication.class, args);
    }

}
