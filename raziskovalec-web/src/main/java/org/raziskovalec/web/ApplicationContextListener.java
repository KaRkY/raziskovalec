package org.raziskovalec.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContextListener implements ServletContextListener {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    logger.info("Application {} stopped.", servletContextEvent.getServletContext().getServletContextName());
  }

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    logger.info("Application {} started.", servletContextEvent.getServletContext().getServletContextName());
  }

}
