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

import org.raziskovalec.web.form.ResearcherForm;
import org.raziskovalec.web.form.mapping.ResearcherFormMapper;
import org.raziskovalec.web.services.ResearcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rene Svetina
 */
@Controller
@RequestMapping("/researcher")
public class ResearcherController {
  private final Logger            logger           = LoggerFactory.getLogger(this.getClass());
  private static final int        RESULTS_PER_PAGE = 10;
  private final ResearcherService researcherService;

  @Autowired
  public ResearcherController(
      @Qualifier("researcherService") final ResearcherService researcherService) {
    this.researcherService = researcherService;
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
      researcherService.save(ResearcherFormMapper.mapToResearcher(researcherForm, null));
      modelAndView.setViewName("redirect:/researcher");
    }
    return modelAndView;
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") final Integer id) {
    researcherService.delete(id);

    return "redirect:/researcher";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView edit(@PathVariable("id") final Integer id) {
    final ModelAndView modelAndView = new ModelAndView("researcher/add");

    modelAndView.addObject("researcher", ResearcherFormMapper.mapToForm(researcherService.get(id)));

    return modelAndView;
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
  public ModelAndView editSave(@PathVariable("id") final Integer id,
      @ModelAttribute("researcher") @Valid final ResearcherForm researcherForm,
      final BindingResult bindingResult) {
    logger.trace("Saveing researcher.");

    final ModelAndView modelAndView = new ModelAndView();

    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("researcher/add");
    } else {

      researcherService.edit(ResearcherFormMapper.mapToResearcher(researcherForm, id));

      modelAndView.setViewName("redirect:/researcher");

    }
    return modelAndView;
  }

  @RequestMapping(value = "/presentation/{id}", method = RequestMethod.GET)
  public ModelAndView get(@PathVariable("id") final int id) {
    final ModelAndView modelAndView = new ModelAndView("researcher/present");

    modelAndView.addObject("researcher", ResearcherFormMapper.mapToForm(researcherService.get(id)));

    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView list() {
    return listPage(0);
  }

  @RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
  public ModelAndView listPage(@PathVariable("pageNum") final int pageNum) {
    final ModelAndView modelAndView = new ModelAndView("researcher/list");

    modelAndView.addObject("pageNum", pageNum);
    modelAndView.addObject("pageCount", researcherService.count() / RESULTS_PER_PAGE);
    modelAndView.addObject("researchers", researcherService.listPaged(pageNum, RESULTS_PER_PAGE));

    return modelAndView;
  }
}
