package mcdodik.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigsApplication.class, args);
    }

}
