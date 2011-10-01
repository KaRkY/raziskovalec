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
package org.raziskovalec.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.raziskovalec.domain.naslov.Mesto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rene Svetina
 * 
 */

@Produces("application/json")
public class Iskanje
{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @GET
    @Path("/")
    public Mesto getMimi()
    {
        logger.trace("mimi called");
        final Mesto mesto = new Mesto();
        mesto.setIme("mimi");
        return mesto;
    }
}
