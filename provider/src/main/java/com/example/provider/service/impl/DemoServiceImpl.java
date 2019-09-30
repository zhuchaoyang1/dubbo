package com.example.provider.service.impl;

import com.example.service.DemoService;

/**
 * 提供者实现common api接口
 *
 * @author 朱朝阳
 * @date 2019/9/30 11:06
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return this.getClass().getName() + "是provider，我实现了commons api接口，你好" + name;
    }

}
