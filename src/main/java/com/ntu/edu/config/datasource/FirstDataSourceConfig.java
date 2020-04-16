package com.ntu.edu.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 第一个数据源
 *
 * dataSource 上面的 ConfigurationProperties 不能丢失，不然无法连接数据库
 */
@Configuration
public class FirstDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

}
