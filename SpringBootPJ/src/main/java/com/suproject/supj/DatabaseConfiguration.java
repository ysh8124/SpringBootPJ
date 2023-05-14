package com.suproject.supj;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfiguration {
	
	@Bean
	@Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari") // �ش� Bean �� ���� �Ǹ鼭 �ش� ������Ƽ�� ���� ���� ������ ����Ѵ�.
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
	
}
