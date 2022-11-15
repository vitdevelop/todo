package com.vitdevelop.todo_app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.vitdevelop.todo_app.core.service",
        "com.vitdevelop.todo_app.web.controller"})
@EnableJpaRepositories(basePackages = "com.vitdevelop.todo_app.core.repository")
@EnableJpaAuditing
//@EnableConfigurationProperties
public class AppConfiguration {
}
