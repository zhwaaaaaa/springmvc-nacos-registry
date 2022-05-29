package com.zhw.nacos.registry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class RegistryDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(RegistryDemoApplication.class, args);
    }

    @RestController
    public static class RegistryController {
        @Value("${server.port}")
        private int port;

        @GetMapping("/hello")
        public String helloWorld() {
            return "Current server is running on " + port;
        }
    }


}
