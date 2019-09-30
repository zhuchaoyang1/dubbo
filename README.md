# Dubbo

分布式任务调度

## 项目目录结构说明

api：为项目调度的接口
cosumer：服务消费者项目
provider：服务提供者1
provider2：服务提供者2
（注：provider、provider2都是对api中DemoService的实现）

Dubbo官网链接：http://dubbo.apache.org/en-us/
Dubbo GitHub链接：https://github.com/apache/dubbo

## Dubbo工作流程

![image](https://github.com/zhuchaoyang1/dubbo/tree/master/readme/img/architecture.png)

## 所需要的支持

zookeeper作为dubbo的服务注册与发现中心

## Dubbo图形化界面

需要clone dubbo的源码，然后打包成jar包或war包去部署

```java
mvn clean package xxx
```

![image](https://github.com/zhuchaoyang1/dubbo/tree/master/readme/img/dubbo控制台.jpg)

## Dubbo的几种集群负载均衡策略

- 随机策略 ： 
  前提：当有多个服务提供者实现同一个API接口，且在配置的`dubbo:application `名称相同时；
  结果：dubbo会随机的选择其中一个提供者为消费者提供服务。
- 轮询策略：
  前提：当有多个服务提供者实现同一个API接口，且在配置的`dubbo:application `名称相同时；
  结果：dubbo轮询方式为消费者提供服务
- 分布压力最小策略：
  这一个需要有大批量的提供者和消费者才会感觉到，适用于大型分布式
  （注：当然上面所说的前提也不是必须的，只是如果只有一个服务提供者，那么随便什么策略都是相同的结果）

### Dubbo和Zookeeper的区别

首先需要说明：Dubbo和Zookeeper是不一样的东西：
Dubbo官方推荐使用Zookeeper作为注册中心
![image](https://github.com/zhuchaoyang1/dubbo/tree/master/readme/img/zook.jpg)

### Zookeeper （注册中心）

主要功能是服务注册与发现的注册中心。是用于分布式中一致性处理的框架（可以把注册中心比喻成一个信息网站，像58同城），以下为zokeeper主要工作：

- 数据发布订阅，即注册中心。 （如发布租房信息、查看租房信息）
- 负载均衡
- 命名服务。zookeeper的节点结构天然支持命名服务，即把信息集中存储，并以树状管理，方便统一查阅。
- 分布式协调通知。协调通知实际上与发布订阅类似，由于引入的第三方的zookeeper，实际上对很多种协调通知做了解耦。
- 集群管理与master选举。通过上面的第二点特性，可以轻易得知集群机器存活状况，从而轻松管理集群；通过上面第一点特性，可以做出master争抢。
- 分布式锁。实际上就是第一点特性的应用。
- 分布式队列。实际上就是第三点特性的应用。
- 分布式的并发等待。类似于多线程的join问题，主任务的执行依赖于其他子任务全部执行完毕，在单机多线程里可以用join，但是分布式环境下如何实现呢。利用zookeeper，可以创建一个主任务节点，旗下子任务一旦执行完毕，则在主任务节点下挂一个子任务节点，等节点数量足够，则认为主任务可以开始执行。

### Dubbo 

dubbo （远程服务调用的分布式框架）主要实现应用与zokeeper等注册中心链接调用的工具（类jdbc工具类），dubbo为你实现了低层中的注册、订阅、调用等功能，让使用者专注于业务。

因此说Dubbo和Zookeeper不是一个东西。而Dubbo也有他的对手就是：FeignClient

## 遇到过的问题

- dubbo控制台或者项目报错：多个不同应用注册了相同服务
  可能来源：
  有一个服务公共接口DemoService、有两个服务提供者均实现了接口DemoService，但提供者的配置文件中配置的name值不一样，如：

```xml
<!-- provider1 -->
<dubbo:application name="demo-provider" owner="zcy-provider1" organization="dubbox"/>

<!-- provider1 -->
<dubbo:application name="demo-provider2" owner="zcy-provider2" organization="dubbox"/>
```

如果如上配置，那么就会报警告：多个不同应用注册了相同服务
