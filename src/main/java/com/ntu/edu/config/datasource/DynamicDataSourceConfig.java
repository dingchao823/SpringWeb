package com.ntu.edu.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ntu.edu.config.ConstantConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 *
 */
@Configuration
public class DynamicDataSourceConfig {

	// 数据源1，读取spring.datasource.druid.first下的配置信息
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.druid.first")
	public DataSource firstDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	// 数据源2，读取spring.datasource.druid.second下的配置信息
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.druid.second")
	public DataSource secondDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	// 加了@Primary注解，表示指定DynamicDataSource为Spring的数据源
	// 因为DynamicDataSource是继承与AbstractRoutingDataSource，而AbstractR
	// outingDataSource又是继承于AbstractDataSource，AbstractDataSource实现了统一
	// 的DataSource接口，所以DynamicDataSource也可以当做DataSource使用

	/**
	 * 这里要注意，这里的输入源如 firstDataSource 名字要与方法一致，不然会导致找不到 DataSource
	 *
	 * @param firstDataSource /
	 * @param secondDataSource /
	 * @return /
	 */
	@Bean
	@Primary
	public DynamicDataSource dataSource(DataSource firstDataSource,
										DataSource secondDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(ConstantConfig.DataSourceName.FIRST, firstDataSource);
		targetDataSources.put(ConstantConfig.DataSourceName.SECOND, secondDataSource);
		return new DynamicDataSource(firstDataSource, targetDataSources);
	}
}
