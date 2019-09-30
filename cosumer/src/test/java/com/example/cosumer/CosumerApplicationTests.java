package com.example.cosumer;

import com.example.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CosumerApplicationTests {

    @Autowired
    private DemoService demoService;

    @Test
    public void contextLoads() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            String s = demoService.sayHello("你好");
            log.info("-----------：" + s);
            Thread.sleep(1000);
        }

    }

    /**
     * 测试最少并发
     *
     * @throws InterruptedException
     */
    @Test
    public void contextLoadsThread() throws InterruptedException {
        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {
            CosumerApplicationTests.ThreadStart threadStart = new CosumerApplicationTests().new ThreadStart();
            threadStart.start();
        }
    }


    class ThreadStart {
        public void start() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String s = demoService.sayHello("你好");
                    log.info("-----------：" + Thread.currentThread() + s);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}
