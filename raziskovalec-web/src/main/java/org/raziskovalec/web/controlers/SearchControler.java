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
package org.raziskovalec.web.controlers;

import org.raziskovalec.web.form.SearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Rene Svetina
 */
@Controller
@RequestMapping("/search")
public class SearchControler
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private final Logger	logger	= LoggerFactory.getLogger(getClass());

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	// =================================================================================================================
	// Methods
	// =================================================================================================================
	@RequestMapping(method = RequestMethod.GET)
	public String search(@ModelAttribute("search") final SearchForm searchForm, final Model model)
	{
		logger.trace("Serving search page.");

		return "search/search";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String searchFor(@ModelAttribute("search") final SearchForm searchForm, final Model model)
	{
		logger.trace("Searching for: {}", searchForm.getSearchTerm());

		return "search/search";
	}
}
