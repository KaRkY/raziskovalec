package org.raziskovalec.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionContextListener implements HttpSessionListener {
	private final Logger	logger	= LoggerFactory.getLogger(getClass());

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		logger.info("New session created. ID = {}", httpSessionEvent.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		logger.info("Session destroyed. ID = {}", httpSessionEvent.getSession().getId());
	}

}
