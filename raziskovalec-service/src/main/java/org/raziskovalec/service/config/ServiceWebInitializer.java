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
package org.raziskovalec.service.config;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author Rene Svetina
 * 
 */
public class ServiceWebInitializer implements WebApplicationInitializer
{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
     * .ServletContext)
     */
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException
    {
        logger.trace("Init rootAppContext.");
        final AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
        
        logger.trace("Adding listener");
        servletContext.addListener(new ContextLoaderListener(rootAppContext));
        
        logger.trace("Adding servlet.");
        final ServletRegistration.Dynamic servletDynamic = servletContext.addServlet("appServlet", new CXFServlet());
        servletDynamic.setLoadOnStartup(1);
        final Set<String> mappingConflicts = servletDynamic.addMapping("/");
        
        servletDynamic.setInitParameter("config-location", "/WEB-INF/spring/cxf-applicationContext.xml");
        
    }
    
}
