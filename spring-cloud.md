
# Spring Cloud

spring cloud基于spring boot，是构建分布式系统的工具集

#### 一个基本的spring cloud的项目一般有以下三个部分

##### 1.服务注册中心
*   可以使用Eureka和Consul实现一个服务发现注册中心，能通过向其他服务注册中心进行注册
    达到服务清单互相同步，实现高可用
    
##### 2.服务提供者
*   一个添加的了Eureka依赖的SpringBoot工程，需要注册到注册中心，然后就能被其他发现

##### 3.服务消费者
*    注册到注册中心的服务，需要使用Ribbon和Feign去调用服务以实现负载均衡，不然只能使用传统的ip+端口去访问而不能使用
服务名


## 一些组件

##### 1. actuator组件

*   SpringBoot的一个组件，通过它提供的端点监管和控制一个服务，它默认会对端点的
访问权限进行验证，除了/health和/info端点能访问，其他的大多数会出现401，可以通过
以下几个方式解决：

    1.将management.security.enabled设置为false来关闭验证
    2.开启Http验证来获取权限
    3.设置ContextPath

##### 2. Ribbon组件

*   SpringCloud实现客户负载均衡的组件，使用@LoadBalanced修饰过的RestTemplate实现对服务的访问
使用的负载均衡策略有

    1.RandomRule随机策略
    2.RoundRobinRule线性轮询策略
    3.RetryRule重试策略
    4.根据权重
	
##### 3.Hystrix组件

*    断路器组件用来为服务提供容错性的，在一个服务出现错误或无法请求的时候能够迅速断开而不会去请求下一个服务或者等待，在服务恢复的时候也能够还原连接。
 在pom.xml处加上以后依赖，在启动文件处加上@EnableCircuitBreaker开启断路器。

##### 4.Feign组件

*    一个声明式的服务调用组件，功能上相当于Ribbon组件和Hystrix组件的集合，使用Ribbon组件实现客户端的负载均衡,需要配置
 feign.hystrix.enabled=true  这个配置来开启hystrix
    
##### 5.zuul组件
*    用来提供一个统一的接口，让访问不同的服务都能使用统一地址，开启这个组件需要在pom.xml处添加依赖，在启动类添加@EnableZuulProxy注解，以及将这个服务注册到服务注册中心，zuul组件会默认查找服务列表然后将服务名映射成路由地址，之后在相应的路由地址下访问服务就行，想自定义路由的名字也可以在配置文件里配置格式如下

    zuul.routes.<任意名字>.path=/<routeName>/**
    zuul.routes.<任意名字>.service-id=<service-id>
  
这样就可以将服务配置到自定义的路由地址上

*    过滤器，zuul实现路由是通过zuul的过滤器实现的，zuul的过滤器有四种类型pre，route，post，error四种类型的过滤器，要实现自己的过滤器
实现ZuulFilter这个接口，然后实现
     
     *    filterType()   返回过滤器的类型
     *    filterOrder()  返回过滤器的执行顺序，越小优先级越高
     *    shouldFilter() 是否需要执行过滤器
     *    run()          过滤器的具体逻辑
     
        四个方法。
 
     ###### 过滤器中处理异常
     
     在过滤器中象平常java代码中里抛出异常是无法被接收的，想要在过滤器中处理异常有异常有以下两种方法
     1.获取RequestContext，然后在try-catch代码中处理异常，在发生异常后在catch块中，调用RequestContext的
     set方法，设置error.status_code和error.exception的值，之后异常就可以被zuul内置的SendErrorFilter过滤器处理
     2.自定义error类型的过滤器就可以处理异常。
 
 ##### 6.Config组件
 
