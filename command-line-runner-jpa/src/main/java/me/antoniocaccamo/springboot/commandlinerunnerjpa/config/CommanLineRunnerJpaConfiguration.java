package me.antoniocaccamo.springboot.commandlinerunnerjpa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CommanLineRunnerJpaConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {

        DataSource dataSource = DataSourceBuilder.create().build();

        return dataSource;
    }
}
