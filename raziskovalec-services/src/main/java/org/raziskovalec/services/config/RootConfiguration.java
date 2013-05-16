package org.raziskovalec.services.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.hibernate.dialect.HSQLDialect;
import org.hsqldb.jdbcDriver;
import org.raziskovalec.services.repository.HibernateResearcherRepository;
import org.raziskovalec.services.repository.ResearcherRepository;
import org.raziskovalec.services.rest.ResearcherServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Throwables;
import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@ImportResource({ "classpath:cxfApplicationContext.xml" })
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class RootConfiguration {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private Environment  environment;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource());
    sessionFactoryBean.setPackagesToScan("org.raziskovalec.domain");
    sessionFactoryBean.setHibernateProperties(hibernateProperties());

    return sessionFactoryBean;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    properties.setProperty("hibernate.dialect", HSQLDialect.class.getName());
    properties.setProperty("jadira.usertype.autoRegisterUserTypes", "true");
    properties.setProperty("jadira.usertype.databaseZone", "jvm");
    properties.setProperty("jadira.usertype.javaZone", "jvm");
    return properties;
  }

  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    final BoneCPDataSource dataSource = new BoneCPDataSource();
    dataSource.setDriverClass(jdbcDriver.class.getName());
    dataSource.setJdbcUrl(String.format("jdbc:hsqldb:file:%s/databases/raziskovalec/db",
        environment.getProperty("user.home")));
    dataSource.setUsername("SA");
    dataSource.setPassword("");
    dataSource.setDefaultAutoCommit(false);

    try (Connection dummy = dataSource.getConnection()) {
      logger.info("Datasource initialization finished.");
    } catch (final SQLException e) {
      logger.error("Datasource initialization failure.", e);
      Throwables.propagate(e);
    }
    return dataSource;
  }

  @Bean
  public JacksonJsonProvider jsonProvider() {
    return new JacksonJsonProvider();
  }

  @Bean
  public ResearcherRepository researcherRepository() {
    return new HibernateResearcherRepository(sessionFactory().getObject());
  }

  @Bean
  public ResearcherServices researcherServices() {
    return new ResearcherServices(researcherRepository());
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }
}
