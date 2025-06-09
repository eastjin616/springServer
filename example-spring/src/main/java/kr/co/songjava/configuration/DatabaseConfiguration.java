package kr.co.songjava.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource") //스프링부트에서 자동으로 Configuration파일을 불러올수 있음
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	

}
