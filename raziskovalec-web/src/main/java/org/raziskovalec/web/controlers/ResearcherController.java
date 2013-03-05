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

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.raziskovalec.Identifier;
import org.raziskovalec.Name;
import org.raziskovalec.domain.Researcher;
import org.raziskovalec.web.form.ResearcherForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

/**
 * @author Rene Svetina
 */
@Controller
@RequestMapping("/researcher")
public class ResearcherController {
  private final Logger    logger = LoggerFactory.getLogger(this.getClass());
  private final WebClient client;

  @Autowired
  public ResearcherController(@Qualifier("webClient") final WebClient client) {
    this.client = client;
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView add() {
    final ModelAndView modelAndView = new ModelAndView("researcher/add");

    modelAndView.addObject("researcher", new ResearcherForm());

    return modelAndView;
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ModelAndView addSave(@ModelAttribute("researcher") @Valid final ResearcherForm researcherForm,
      final BindingResult bindingResult) {
    logger.trace("Saveing researcher.");

    final ModelAndView modelAndView = new ModelAndView();

    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("researcher/add");
    } else {

      final Researcher researcher = new Researcher(Identifier.newId(), Name.valueOf(researcherForm.getName()),
          Name.valueOf(researcherForm.getLastname()), researcherForm.getEmail(), researcherForm.getDateOfBirth());

      final Response researcherSaveResponse = client.path("/researcher/ad").post(researcher);

      if (researcherSaveResponse.getStatus() == 200) {
        modelAndView.setViewName("redirect:/researcher");
      } else {
        modelAndView.addObject("errors", Lists.newArrayList("error.service"));
        modelAndView.setViewName("researcher/add");
      }

    }

    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String list() {
    return "researcher/list";
  }
}
