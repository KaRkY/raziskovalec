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
package org.raziskovalec.web.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.raziskovalec.domain.naslov.Mesto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Rene Svetina
 */
@Controller
@RequestMapping("/iskanje")
public class IskanjeController
{
    
    private transient final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String isci(final Model model)
    {
        model.addAttribute("iskalniParameter", "");
        logger.trace("Iskanje");
        return "iskanje/iskanje";
    }
    
    /**
     * 
     * @param iskalniParameter
     * @param model
     * @return
     * @throws URISyntaxException
     * @throws RestClientException
     */
    @RequestMapping(method = RequestMethod.POST)
    public String iskanje(@RequestParam("iskalniParameter") final String iskalniParameter, final Model model)
            throws RestClientException, URISyntaxException
            {
        logger.info("Iskanje z parametrom: {}", iskalniParameter);
        final RestTemplate template = new RestTemplate();
        final Mesto mesto = template.getForObject(new URI("http://localhost:8080/raziskovalec-service/services"),
                Mesto.class);
        model.addAttribute("iskalniParameter", mesto.getIme());
        return "iskanje/iskanje";
            }
}
