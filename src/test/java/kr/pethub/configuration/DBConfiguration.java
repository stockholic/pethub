package kr.pethub.configuration;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY, order = 0)
public class DBConfiguration{
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private @Value("${deploy}") String deploy;
    private @Value("${jdbc.driverClassName}") String driverClassName;
    private @Value("${jdbc.url}") String url;
    private @Value("${jdbc.username}") String username;
    private @Value("${jdbc.password}") String password;
    private @Value("${jdbc.initialSize}") int initialSize;
    private @Value("${jdbc.minIdle}") int minIdle;
    private @Value("${jdbc.maxActive}") int maxActive;
    private @Value("${jdbc.maxIdle}") int maxIdle;
    private @Value("${jdbc.maxWait}") int maxWait;
    private @Value("${jdbc.validationQuery}") String validationQuery;
    private @Value("${jdbc.testWhileIdle}") boolean testWhileIdle;
    private @Value("${jdbc.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxTotal(maxActive);
        dataSource.setMaxIdle(maxIdle);
        
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return sqlSessionFactoryBean();
    }
    
    private SqlSessionFactory sqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        
        sqlSessionFactory.setConfigLocation(defaultResourceLoader.getResource("classpath:config/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/**/*.xml"));
        sqlSessionFactory.setDataSource(dataSource());
        
        return sqlSessionFactory.getObject();
    }
    
    @Bean
    public DataSourceTransactionManager txManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}