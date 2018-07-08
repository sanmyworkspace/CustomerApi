package com.tring.customer;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
//@ComponentScan("com.tring.customer")
@PropertySource(value = { "classpath:/log4j.properties","classpath:/hibernate.properties" })
//@Configuration
public class CustomerApiApplication extends SpringBootServletInitializer {

	static Logger logger = LogManager.getLogger(CustomerApiApplication.class);

	@Autowired
	private Environment environment;

	@Autowired
	@Bean(name = "customerSessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDataSource());
		sessionFactoryBean.setPackagesToScan(new String[] { "com.tring.customer.model" });
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		return sessionFactoryBean;
	}

	private DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driverClassName"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getProperty("db.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("db.show_sql"));
		properties.put("hibernate.format_sql", environment.getProperty("db.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("db.hbm2ddl.auto"));
		return properties;
	}

	@Autowired
	@Bean(name = "customerHibernateTemplate")
	public HibernateTemplate getHibernateTemplate() {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(getSessionFactory().getObject());
		return hibernateTemplate;
	}

	@Autowired
	@Bean(name = "customerTxManager")
	public HibernateTransactionManager geTransactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(getSessionFactory().getObject());
		return txManager;
	}
	
	public static void main(String[] args) {
		logger.debug("CustomerApiApplication is started intializing...........");
		SpringApplication.run(CustomerApiApplication.class, args);
	}
}
