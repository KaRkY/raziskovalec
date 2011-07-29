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

package org.raziskovalec.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/iskanje")
public class IskanjeController
{
	private transient final Logger	logger	= LoggerFactory.getLogger(this
													.getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String isci(final Model model)
	{
		model.addAttribute("iskalniParameter", "");
		return "iskanje/iskanje";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String iskanje(
			@RequestParam("iskalniParameter") final String iskalniParameter,
			final Model model)
	{
		this.logger.info("Iskanje z parametrom: {}", iskalniParameter);
		model.addAttribute("iskalniParameter", iskalniParameter);
		return "iskanje/iskanje";
	}
}
