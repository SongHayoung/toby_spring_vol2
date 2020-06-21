package Spring.init;

import Spring.Test.jdk.web.hello.HelloSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    @Bean
    public HelloSpring helloSpring(){
        return new HelloSpring();
    }
}
