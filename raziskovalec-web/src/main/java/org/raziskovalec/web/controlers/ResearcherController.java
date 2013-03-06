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

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.raziskovalec.Name;
import org.raziskovalec.base.ObjectsUtil;
import org.raziskovalec.domain.Researcher;
import org.raziskovalec.web.form.ResearcherForm;
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

import com.google.common.collect.Lists;

/**
 * @author Rene Svetina
 */
@Controller
@RequestMapping("/researcher")
public class ResearcherController {
  private final Logger     logger           = LoggerFactory.getLogger(this.getClass());
  private final WebClient  client;
  private static final int RESULTS_PER_PAGE = 10;

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

      final Researcher researcher = new Researcher(null);
      researcher.setName(Name.valueOf(researcherForm.getName()));
      researcher.setLastName(Name.valueOf(researcherForm.getLastname()));
      researcher.setDateOdBirth(researcherForm.getDateOfBirth());
      researcher.setEmail(ObjectsUtil.nullForEmpty(researcherForm.getEmail()));
      researcher.setTelephoneNumber(ObjectsUtil.nullForEmpty(researcherForm.getTelephonenumber()));
      researcher.setWebsite(ObjectsUtil.nullForEmpty(researcherForm.getWww()));

      final WebClient localClient = WebClient.fromClient(client, true);
      final Response researcherSaveResponse = localClient.path("/researcher").put(researcher);

      if (researcherSaveResponse.getStatus() == 200) {
        modelAndView.setViewName("redirect:/researcher");
      } else {
        modelAndView.addObject("errors", Lists.newArrayList("error.service"));
        modelAndView.setViewName("researcher/add");
      }

    }
    return modelAndView;
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public String delete(@PathVariable("id") final Integer id) {
    final WebClient localClient = WebClient.fromClient(client, true);
    final Response response = localClient.path("/researcher/{id}", id).delete();

    if (response.getStatus() == 200) return "redirect:/researcher";
    else return "redirect:/researcher";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView edit(@PathVariable("id") final Integer id) {
    final ModelAndView modelAndView = new ModelAndView();
    final WebClient localClient = WebClient.fromClient(client, true);
    final Response response = localClient.path("/researcher/{id}", id).get();

    if (response.getStatus() == 200) {
      final Researcher researcher = response.readEntity(Researcher.class);
      final ResearcherForm researcherForm = new ResearcherForm();
      researcherForm.setName(researcher.getName().toString());
      researcherForm.setLastname(researcher.getLastName().toString());
      researcherForm.setEmail(researcher.getEmail());
      researcherForm.setTelephonenumber(researcher.getTelephoneNumber());
      researcherForm.setWww(researcher.getWebsite());
      researcherForm.setDateOfBirth(researcher.getDateOdBirth());

      modelAndView.addObject("researcher", researcherForm);
      modelAndView.setViewName("researcher/edit");
    } else {
      modelAndView.setViewName("redirect:/researcher");
    }

    return modelAndView;
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
  public ModelAndView editSave(@PathVariable("id") final Integer id,
      @ModelAttribute("researcher") @Valid final ResearcherForm researcherForm,
      final BindingResult bindingResult) {
    logger.trace("Saveing researcher.");

    final ModelAndView modelAndView = new ModelAndView();

    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("researcher/edit");
    } else {

      final Researcher researcher = new Researcher(id);
      researcher.setName(Name.valueOf(researcherForm.getName()));
      researcher.setLastName(Name.valueOf(researcherForm.getLastname()));
      researcher.setDateOdBirth(researcherForm.getDateOfBirth());
      researcher.setEmail(ObjectsUtil.nullForEmpty(researcherForm.getEmail()));
      researcher.setTelephoneNumber(ObjectsUtil.nullForEmpty(researcherForm.getTelephonenumber()));
      researcher.setWebsite(ObjectsUtil.nullForEmpty(researcherForm.getWww()));

      final WebClient localClient = WebClient.fromClient(client, true);
      final Response researcherSaveResponse = localClient.path("/researcher/{id}", id).post(researcher);

      if (researcherSaveResponse.getStatus() == 200) {
        modelAndView.setViewName("redirect:/researcher");
      } else {
        modelAndView.addObject("errors", Lists.newArrayList("error.service"));
        modelAndView.setViewName("researcher/edit");
      }

    }
    return modelAndView;
  }

  @RequestMapping(value = "/presentation/{id}", method = RequestMethod.GET)
  public String get(@PathVariable("id") final int id) {
    return "researcher/present";
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView list() {
    return listPage(0);
  }

  @RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
  public ModelAndView listPage(@PathVariable("pageNum") final int pageNum) {
    final WebClient resultClient = WebClient.fromClient(client, true);
    final ModelAndView modelAndView = new ModelAndView("researcher/list");
    modelAndView.addObject("pageNum", pageNum);

    final WebClient countClient = WebClient.fromClient(client, true);
    final Response countResponse = countClient.path("/researcher/count").get();
    if (countResponse.getStatus() == 200) {
      modelAndView.addObject("pageCount", countResponse.readEntity(Integer.class) / RESULTS_PER_PAGE);
    } else
    {
      modelAndView.addObject("pageCount", 0);
      return modelAndView;
    }

    final Response resultResponse = resultClient.path("/researcher/{pageNum}/{resultsPerPage}",
        pageNum,
        RESULTS_PER_PAGE)
        .get();

    if (resultResponse.getStatus() == 200) {
      final List<Researcher> researchers = resultResponse.readEntity(new GenericType<List<Researcher>>() {
      });

      modelAndView.addObject("researchers", researchers);
    }

    return modelAndView;
  }
}
