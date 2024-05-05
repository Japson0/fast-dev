# 快速开发框架

## 目录说明

- archetype：脚手架目录，定义了集成Maven脚手架机制的项目，可以通过该脚手架快速创建需要的工程结构。
- spring-cache-ctx： 集成spring-cache的扩展包，用于解耦中间件缓存
- spring-cache-redis-ctx：扩展redis的缓存中间件，增加了protobuf格式。
- spring-mybatis-ctx：mybatis的扩展包，内置mybatis-plus，增加了基于注解的快速查询模块，还扩展了加解密、权限控制等插件
    - spring-mybatis-ctx-starter：插件核心包
    - spring-mybatis-ctx-annotation：插件注解包，主要是为了注解和核心解耦，（针对微服务模式，接口包直接给其他服务引用）
- spring-swagger-ctx：基于knife4j的swagger文档，这个包主要是为了调整项目的一些类的格式，如枚举信息等
- spring-scaffold：这个包主要是为了集成一个基础的Spring环境，比如上面的`spring-mybatis-ctx`、`spring-swagger-ctx`
  这类包，并调整各依赖版本，减少版本冲突
  - common-info：基础包，包含了一些预置异常类、序列化器等公共类的包
  - spring-framework-scaffold-starter：集成SpringBoot环境的包，扩展了一些通用查询方法如`BaseControoler`、`BaseService`
    ，还包含了一些脱敏、枚举转换等操作
  - spring-framework-cloud-scaffold-starter：集成SpringCloud环境的包，在`spring-framework-scaffold-starte`
    的基础上，增加了Nacos等针对微服务的一些扩展。
- custom-component：这个模块主要是为了将平时的一些对于框架的使用上的一个抽象的积累，可以理解卫视一个框架工具类的扩展
  - spring-elasticsearch-ctx：将ES查询简单化，只需要加上简单的注解即可进行ES的查询
  - spring-hadoop-client-ctx：Hadoop客户端，集成了权限的验证
  - spring-minio-client-ctx：Minio的客户端

---

### 具体[文档](https://www.showdoc.com.cn/2501729102990778/11123879379293110)

