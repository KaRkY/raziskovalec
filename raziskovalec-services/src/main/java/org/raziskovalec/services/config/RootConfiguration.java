package org.raziskovalec.services.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.hsqldb.jdbcDriver;
import org.raziskovalec.services.rest.ResearcherServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

import com.google.common.base.Throwables;
import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@ImportResource({ "classpath:cxfApplicationContext.xml" })
public class RootConfiguration {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Bean(destroyMethod = "close")
  public DataSource dataSource(final Environment environment) {
    final BoneCPDataSource dataSource = new BoneCPDataSource();
    dataSource.setDriverClass(jdbcDriver.class.getName());
    dataSource.setJdbcUrl(String.format("jdbc:hsqldb:file:%s/databases/raziskovalec/db",
        environment.getProperty("user.home")));
    dataSource.setUsername("SA");

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
  public ResearcherServices researcherServices() {
    return new ResearcherServices();
  }
}
