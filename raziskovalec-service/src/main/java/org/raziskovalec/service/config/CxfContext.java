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

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.raziskovalec.service.Iskanje;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Rene Svetina
 * 
 */
@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml" })
public class CxfContext
{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     */
    public CxfContext()
    {
        logger.trace("Configuration created.");
    }
    
    @Bean()
    public JAXRSServerFactoryBean server()
    {
        logger.trace("Getting Server factory bean.");
        final JAXRSServerFactoryBean server = new JAXRSServerFactoryBean();
        server.setAddress("/");
        server.setServiceBean(iskanje());
        server.setProvider(provider());
        
        server.init();
        return server;
    }
    
    @Bean
    public Iskanje iskanje()
    {
        return new Iskanje();
    }
    
    @Bean
    public JacksonJsonProvider provider()
    {
        return new JacksonJsonProvider();
    }
}
