package org.raziskovalec.services.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.raziskovalec.services.test.TestingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:cxfApplicationContext.xml" })
public class RootConfiguration {

  @Bean
  public JacksonJsonProvider jsonProvider() {
    return new JacksonJsonProvider();
  }

  @Bean
  public TestingService testService() {
    return new TestingService();
  }
}
