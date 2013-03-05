package org.raziskovalec.services;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.raziskovalec.services.config.RootConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ServicesWebAppInitializer implements WebApplicationInitializer {
  private static final String SERVLET_NAME = "cxfServlet";
  private final Logger        logger       = LoggerFactory.getLogger(getClass());

  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    createContextLoader(servletContext);
    createCxfServlet(servletContext);

  }

  private void createContextLoader(final ServletContext servletContext) {
    final WebApplicationContext rootContext = createRootContext();

    if (rootContext != null) {
      servletContext.addListener(new ContextLoaderListener(rootContext));
    } else {
      logger.error("Context loader listener not initialized.");
    }
  }

  private void createCxfServlet(final ServletContext servletContext) {
    final CXFServlet cxfServlet = new CXFServlet();
    final Dynamic servlet = servletContext.addServlet(SERVLET_NAME, cxfServlet);

    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");
  }

  private WebApplicationContext createRootContext() {
    final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(RootConfiguration.class);
    // context.refresh();

    return context;
  }

}
