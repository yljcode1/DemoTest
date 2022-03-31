## gateway 网关服务

#### 使用网关

1、添加依赖

```xml
<!-- spring cloud gateway 依赖 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

2、resources/application.yml配置文件

```yaml
server:
  port: 8080

spring:
  application:
    name: gateway-server
  cloud:
    gateway:
      routes:
        # 系统模块
        - id: api-server
          uri: http://localhost:9201/
          predicates:
            - Path=/api/**
          filters:
            - StripPrefix=1
```

## 路由规则

Spring Cloud Gateway创建Route对象时， 使用RoutePredicateFactory创建Predicate对象， Predicate对象可以赋值给Route。

* Spring Cloud Gateway包含许多内置的Route Predicate Factories
* 所有这些断言都匹配 HTTP 请求的不同属性。
* 多个Route Predicate Factories可以通过逻辑与（and）结合起来一起使用。

路由断言工厂RoutePredicateFactory包含的主要实现类如图所示 , 包括Datetime、请求的远端地址、路由权重、请求头、Host 地址、请求方法、请求路径和请求参数等类型的路由断言。

Datetime 匹配日期时间之后发生的请求

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - After=2021-02-23T14:20:00.000+08:00[Asia/Shanghai]
```

Cookie 匹配指定名称且其值与正则表达式匹配的cookie

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - Cookie=loginname, ruoyi
```

测试 curl http://localhost:8080/system/config/1 --cookie "loginname=ruoyi"

Header 匹配具有指定名称的请求头，\d+值匹配正则表达式

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - Header=X-Request-Id, \d+
```

Host 匹配主机名的列表

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - Host=**.somehost.org,**.anotherhost.org
```

Method 匹配请求methods的参数，它是一个或多个参数

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - Method=GET,POST
```

Path 匹配请求路径

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - Path=/system/**
```

Query 匹配查询参数

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - Query=username, abc.
```

RemoteAddr 匹配IP地址和子网掩码

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system
          uri: http://localhost:9201/
          predicates:
            - RemoteAddr=192.168.10.1/0
```

Weight 匹配权重

```yaml
spring:
  application:
    name: ruoyi-gateway
  cloud:
    gateway:
      routes:
        - id: ruoyi-system-a
          uri: http://localhost:9201/
          predicates:
            - Weight=group1, 8
        - id: ruoyi-system-b
          uri: http://localhost:9201/
          predicates:
            - Weight=group1, 2
```

### 路由配置

在spring cloud gateway中配置uri有三种方式，包括

* websocket配置方式

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: ruoyi-api
          uri: ws://localhost:9090/
          predicates:
            - Path=/api/**
```

* http地址配置方式

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: ruoyi-api
          uri: http://localhost:9090/
          predicates:
            - Path=/api/**
```

* 注册中心配置方式

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: ruoyi-api
          uri: lb://ruoyi-api
          predicates:
            - Path=/api/**
```