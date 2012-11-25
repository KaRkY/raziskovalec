package org.raziskovalec.web.configuration;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

@Configuration
public class RootConfiguration {

  @Bean
  public JacksonJsonProvider jsonProvider() {
    return new JacksonJsonProvider();
  }

  @Bean
  public WebClient webClient() {
    return WebClient.create("http://localhost:8080/raziskovalec-services", Lists.newArrayList(jsonProvider())).type(
        MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
  }
}
