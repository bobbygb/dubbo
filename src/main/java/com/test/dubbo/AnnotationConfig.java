package com.test.dubbo;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

@Configuration
@ComponentScan("com.test")
public class AnnotationConfig implements TransactionManagementConfigurer{
    private static final Log log = LogFactory.getLog(AnnotationConfig.class);
    
    @Resource(name="dataSource")
    DataSource mainDataSource;
    
    @Bean
    public ApplicationConfig applicationConfig(){
    	ApplicationConfig applicationCofig = new ApplicationConfig();
    	applicationCofig.setName("TestApp");
    	return applicationCofig;
    }
    
    @Bean
	public RegistryConfig registryConfig() {
    	RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress("192.168.9.153:2181");
		registryConfig.setProtocol("zookeeper");
		registryConfig.setCheck(false);
		return registryConfig;
	}
    
    @Bean
	public static AnnotationBean annotationBean() {
    	AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setPackage("com.test");
		return annotationBean;
	}
    
    @Bean(name = "dataSource")
    public DataSource dataSource(){
 	   String DRIVER = "org.mariadb.jdbc.Driver";
       String URL_JDBC = "jdbc:mysql://192.168.9.153/trustee?characterEncoding=utf-8&autoReconnect=true";
       String USER_NAME = "mysqluser";
 	   String PASS_WORD = "mysqluser@zyxr.com";
       org.apache.tomcat.dbcp.dbcp.BasicDataSource ds = new org.apache.tomcat.dbcp.dbcp.BasicDataSource();
 	   ds.setDriverClassName(DRIVER);
 	   ds.setUrl(URL_JDBC);
 	   ds.setMaxIdle(10);
 	   ds.setMaxWait(-1);
 	   ds.setUsername(USER_NAME);
 	   ds.setPassword(PASS_WORD);
 	   ds.setValidationQuery("select 1;");
 	   ds.setTestWhileIdle(true);
 	   return ds;
    }
    
    @Bean(name = "jdbcTemplate")
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource ds){
 		JdbcTemplate jdbcTemplate = new JdbcTemplate();
 		jdbcTemplate.setDataSource(ds);
 		return jdbcTemplate;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager(){
       System.out.println("using DataSourceTransationManager......");
 	  DataSourceTransactionManager dtm = new DataSourceTransactionManager(mainDataSource);
 	  return dtm;
    }
    
}


