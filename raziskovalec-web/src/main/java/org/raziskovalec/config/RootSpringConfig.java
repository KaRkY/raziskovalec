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

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.config.AdviceMode;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class RootSpringConfig implements TransactionManagementConfigurer {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public DataSource dataSource() {
		try {
			logger.trace("Getting DataSource");
			InitialContext ctx = new InitialContext();
			return (DataSource) ctx.lookup("java:comp/env/jdbc/raziskovalecDB");
		} catch (NamingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Bean
	public PlatformTransactionManager txManager() {
		logger.trace("Creating Transaction manager");
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(
				dataSource());
		return dataSourceTransactionManager;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}

}
