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

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.raziskovalec.controllers" })
public class MvcSpringConfig extends WebMvcConfigurerAdapter
{

	private Logger	logger	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void configureDefaultServletHandling(
			final DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Override
	public void configureViewControllers(
			final ViewControllerConfigurer configurer)
	{

		configurer.mapViewName("/", "home");
	}

	@Bean
	public FreeMarkerConfigurer tilesConfigurer()
	{
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

		configurer.setTemplateLoaderPath("/WEB-INF/freemarker");

		Properties prop = new Properties();
		prop.setProperty("auto_import",
				"/layout/layout.ftl as layout, spring.ftl as spring");

		configurer.setFreemarkerSettings(prop);

		return configurer;
	}

	@Bean
	public ViewResolver viewResolver()
	{
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");

		return resolver;
	}

}
