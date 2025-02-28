package com.smartvault.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("com.smartvault.authentication")
//@EnableAdminServer
@EnableJpaRepositories(basePackages = "com.smartvault.authentication")
//@ComponentScan(basePackages = { "com.smartvault.authentication.common",
//        "com.smartvault.authentication.llms" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = com.smartvault.authentication.llms.config.LLMConfig.class))

public class SmartvaultAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartvaultAuthenticationApplication.class, args);
    }
}
