package com.suproject.supj;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource") // [2]
    public DataSourceProperties primaryDataSourceProperties() { // [3]
        DataSourceProperties sourceProperty = new DataSourceProperties();
        return sourceProperty;
    }
	
}
