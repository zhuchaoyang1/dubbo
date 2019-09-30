package com.example.cosumer.config;

import com.example.service.DemoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 朱朝阳
 * @date 2019/9/30 11:27
 */
@Configuration
@ImportResource(value = {"classpath:comsumer.xml"})
public class RegisterConsumer {

    @Bean
    public DemoService demoServiceFactory() {
        ClassPathXmlApplicationContext consumerContext = new ClassPathXmlApplicationContext("comsumer.xml");

        DemoService demoService = (DemoService) consumerContext.getBean("demoService");
        return demoService;

    }

}
