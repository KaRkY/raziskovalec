/**
 * Copyright 2011 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.raziskovalec.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class RaziskovalecWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext rootAppContext = initRootContext(servletContext);

		AnnotationConfigWebApplicationContext servletAppContext = initMvcContext(
				servletContext, rootAppContext);
		
		initCharacterFilter(servletContext);

		servletContext.addListener(new ContextLoaderListener(rootAppContext));

		ServletRegistration.Dynamic servletDynamic = servletContext.addServlet("appServlet",
				new DispatcherServlet(servletAppContext));
		servletDynamic.setLoadOnStartup(1);
		Set<String> mappingConflicts = servletDynamic.addMapping("/");
		if (!mappingConflicts.isEmpty()) {
			throw new IllegalStateException(
					"'appServlet' could not be mapped to '/' due "
							+ "to an existing mapping. This is a known issue under Tomcat versions "
							+ "<= 7.0.14; see https://issues.apache.org/bugzilla/show_bug.cgi?id=51278");
		}

	}

	private void initCharacterFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic filterDynamic = servletContext.addFilter("characterEncoding", new CharacterEncodingFilter());
		filterDynamic.setInitParameter("encoding", "UTF-8");
		filterDynamic.setInitParameter("forceEncoding", "true");
		filterDynamic.getUrlPatternMappings().add("/*");
	}

	private AnnotationConfigWebApplicationContext initMvcContext(
			ServletContext servletContext,
			AnnotationConfigWebApplicationContext rootAppContext) {
		
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.setParent(rootAppContext);
		servletAppContext.setServletContext(servletContext);
		servletAppContext.register(MvcSpringConfig.class);
		
		return servletAppContext;
	}

	private AnnotationConfigWebApplicationContext initRootContext(
			ServletContext servletContext) {
		
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootSpringConfig.class);
		rootAppContext.setServletContext(servletContext);
		
		return rootAppContext;
	}
	
	class Test implements Filter{

		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void doFilter(ServletRequest arg0, ServletResponse arg1,
				FilterChain arg2) throws IOException, ServletException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			throw new RuntimeException();
			
		}}

}
