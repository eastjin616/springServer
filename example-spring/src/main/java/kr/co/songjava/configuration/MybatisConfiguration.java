package kr.co.songjava.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


/**
 * Mybatis 설정
 * @author 서동진
 */
@Configuration
@MapperScan(basePackages = "kr.co.songjava.mvc.repository")
public class MybatisConfiguration {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource, ApplicationContext applicationContext) throws Exception  {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sql/*.xml"));
		return factory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
