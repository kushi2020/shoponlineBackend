package com.backendshoponline.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.backendshoponline.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	
	public  static final String   DATABASE_URL="jdbc:h2:tcp://localhost/~/test";
	public  static final String   DATABASE_DRIVER="org.h2.Driver";
	public  static final String   DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
	public  static final String   DATABASE_USERNAME="sa";
	public  static final String   DATABASE_PASSWORD="";
	
	
	@Bean("dataSource")
	public DataSource getDataSource(){
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		return dataSource;
	}
	
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource){
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.backendshoponline.dto");
		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties=new Properties();
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql",true);
		properties.put("hibernate.format_sql",true);
		properties.put("hibernate.hbm2ddl.auto","create");
		/*properties.put("hibernate.hbm2ddl.auto","update");*/
		return properties;
	}
@Bean
public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
   HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
   return transactionManager;
}

}