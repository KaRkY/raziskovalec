package org.raziskovalec.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.raziskovalec.web.configuration.RootConfiguration;
import org.raziskovalec.web.configuration.ServletConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);

    servletContext.addListener(ApplicationContextListener.class);
    servletContext.addListener(SessionContextListener.class);
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { RootConfiguration.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { ServletConfiguration.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }
}
