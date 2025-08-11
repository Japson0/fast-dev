
package net.github.fastdev.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * swagger2配置，访问地址  http://localhost:8080/doc.html
 *
 * @author Dante Zheng
 * @version 1.0
 * @created 2019/4/16 11:53:16
 */
@Configuration
public class Swagger2Config {

    @Bean
    public EnumsPropertyCustomizer enumsPropertyPlugin() {
        return new EnumsPropertyCustomizer();
    }

}
