# dolphinschedule任务集成

## 思路

- 将制作好的jar包上传至dolphinscheduler的资源中心。
- 创建工作流。定义全局参数为taskId。taskId为运行时任务实例参数，每次运行应该不一样。还有其他可选参数，如graphId。
- 创建Shell任务,脚本内容如下，资源一栏选择刚才上传的jar包版本。graphId=${graphId}为可选参数，如果有多个参数，空格隔开

```sh
export taskId=${taskId}
export jobName=graph-builder
export preJobId=${preJobId}
sh shart-java.sh demo1.jar graphId=${graphId}
```

- 资源选择脚本里面执行的jar包，和shart-java.sh脚本
- 自定义参数一栏，一定要将preJobId作为OUT定义，这样才能将jobId传递给下游

## 开发Job

### 创建SpringBoot工程

### 引入依赖

```xml
</dependencies>
<dependency>
        <groupId>net.evecom</groupId>
        <artifactId>dolphinscheduler-core</artifactId>
</dependency>
</dependencies>

<dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>net.evecom</groupId>
                <artifactId>custom-component</artifactId>
                <version>${fast-dev.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

### 配置支持

bootstrap.yml

- Nacos-config
- mybatis-plus
- redis（暂定用来传递参数）

```yaml
spring:
  profiles:
    active: dynamic 
  application:
    name: graph-builder
  cloud:
    nacos:
      server-addr: ${NACOS_SERVER_ADD:172.16.40.233:31111}
      config:
        namespace: ${NACOS_CONFIG_NAMESPACE:1204a6a3-f458-4747-8804-72991fc57a01}
        username: ${NACOS_USER:nacos}
        password: ${NACOS_PASSWORD:nacos}
        group: dynamic
        file-extension: yaml
        shared-configs:
          - data-id: dolphinschedule-conf.yaml
            group: DEFAULT_GROUP
          - datasource.yaml
          - mybatis-plus.yaml
```

### 实现Job接口

```java
@Component
public class JobDetail implements JobInfo {

    @Override
    public String execute(Map<String, String> currentParams, String preParams) throws Exception {
        System.out.println("前面传过来的参数为-----------------------------------");
        System.out.println(preParams);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("hello, execute");
        System.out.println(currentParams);
        return "success";
    }
}
```

- currentParams：当前任务的参数，比如graphId等
- preParams：上一个任务的传递过来的结果数据，execute方法返回的值会传给下一个任务，如果返回空则不传

### 说明

该工程为SpringBoot最小依赖工程，请勿加入其他影响启动的依赖。







