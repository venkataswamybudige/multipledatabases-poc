package com.venkat.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MysqlDatasourceConfiguration {


    @ConfigurationProperties("spring.datasource.mysql")
    @Bean
    public DataSourceProperties mySqlDataSourceProperties(){
        return  new DataSourceProperties();
    }


    @Bean
    DataSource mysqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(mySqlDataSourceProperties().getUrl());
        dataSource.setUsername(mySqlDataSourceProperties().getUsername());
        dataSource.setPassword(mySqlDataSourceProperties().getPassword());
        dataSource.setDriverClassName(mySqlDataSourceProperties().getDriverClassName());
        return  dataSource;
    }
}
