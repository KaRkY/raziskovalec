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
package org.raziskovalec.web.config;

import java.util.Map;

import org.raziskovalec.web.i18n.LocalizationBean;
import org.raziskovalec.web.jsf.scopes.ViewScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.collect.Maps;

/**
 * @author Rene Svetina
 */
@Configuration
public class RootSpringConfig
{
	private final Logger	logger	= LoggerFactory.getLogger(this.getClass());
	
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public LocalizationBean localizationBean()
	{
		logger.trace("Entering method localizationBean()");
		final LocalizationBean localizationBean = new LocalizationBean();
		logger.trace("Leaving method localizationBean(): {}", localizationBean);
		return localizationBean;
	}
	
	@Bean
	public ResourceBundleMessageSource msg()
	{
		logger.trace("Entering method msg()");
		final ResourceBundleMessageSource messageSource = new
				ResourceBundleMessageSource();
		
		messageSource.setBasenames(new String[] {
				"org.raziskovalec.messages.menu",
				"org.raziskovalec.messages.search.messages",
		"org.raziskovalec.messages.researcher.messages" });
		
		logger.trace("Leaving method msg(): {}", messageSource);
		return messageSource;
	}
	
	@Bean
	public CustomScopeConfigurer scopeConfigurer()
	{
		final CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
		
		final Map<String, Object> scopes = Maps.newHashMap();
		scopes.put("view", new ViewScope());
		
		scopeConfigurer.setScopes(scopes);
		
		return scopeConfigurer;
	}
}
