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
package org.raziskovalec.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResearcherAddBackingBean
{
	// ========================================================================
	// Fields
	// ========================================================================
	private final Logger			logger	= LoggerFactory.getLogger(this.getClass());
	private final ResearcherBean	researcher;
	
	// ========================================================================
	// Constructors
	// ========================================================================
	public ResearcherAddBackingBean(final ResearcherBean researcher)
	{
		this.researcher = researcher;
	}
	
	// ========================================================================
	// Methods
	// ========================================================================
	
	public String cancel()
	{
		logger.info("Canceling researcher save.");
		return "/researcher/list?faces-redirect=true";
	}
	
	public String save()
	{
		logger.info("Saveing researcher: '{}'", researcher);
		return "/researcher/list?faces-redirect=true";
	}
}
