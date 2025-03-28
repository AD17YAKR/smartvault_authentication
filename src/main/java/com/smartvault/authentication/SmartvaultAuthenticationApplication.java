package com.smartvault.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("com.smartvault.authentication")
@EnableJpaRepositories(basePackages = "com.smartvault.authentication")
public class SmartvaultAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartvaultAuthenticationApplication.class, args);
    }
}
