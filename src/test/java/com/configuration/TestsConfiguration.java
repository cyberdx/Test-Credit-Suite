package com.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.domain"})
@EnableJpaRepositories(basePackages = {"com.repository"})
@ComponentScan(basePackages = {"com.service", "com.configuration"})
@EnableTransactionManagement
public class TestsConfiguration {

}
