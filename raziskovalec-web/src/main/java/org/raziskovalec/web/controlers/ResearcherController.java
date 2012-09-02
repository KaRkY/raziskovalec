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

import org.raziskovalec.web.form.ResearcherForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rene Svetina
 */
@Controller
@RequestMapping("/researcher")
public class ResearcherController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView add() {
    final ModelAndView modelAndView = new ModelAndView("researcher.add");

    modelAndView.addObject("researcher", new ResearcherForm());

    return modelAndView;
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ModelAndView addSave(@ModelAttribute("researcher") final ResearcherForm researcherForm,
      @RequestParam(required = false, value = "save") final String save,
      @RequestParam(required = false, value = "cancel") final String cancel, final BindingResult bindingResult) {
    final ModelAndView modelAndView = new ModelAndView();

    if (save != null && cancel == null) logger.info("Researcher added.");

    if (bindingResult.hasErrors() && save != null && cancel != null) modelAndView.setViewName("researcher.add");
    else modelAndView.setViewName("redirect:/researcher");

    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String list() {
    return "researcher.list";
  }
}
