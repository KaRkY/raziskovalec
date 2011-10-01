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
package org.raziskovalec.service.domain.builders.naslov;

import org.raziskovalec.service.domain.builders.AbstractBuilder;
import org.raziskovalec.service.domain.naslov.Posta;

/**
 * Bilder za {@link Posta}
 * 
 * @author Rene Svetina
 * 
 */
public class PostaBuilder extends AbstractBuilder<Posta, PostaBuilder>
{
    
    /**
     * @param postnaStevilka
     *            Poštna številka
     * @return builder
     */
    public PostaBuilder postnaStevilka(final Integer postnaStevilka)
    {
        getValue().setPostnaStevilka(postnaStevilka);
        return self();
    }
    
    /**
     * @param ime
     *            Ime pošte
     * @return builder
     */
    public PostaBuilder ime(final String ime)
    {
        getValue().setIme(ime);
        return self();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.raziskovalec.domain.builders.AbstractBuilder#getObject()
     */
    @Override
    protected Posta getObject()
    {
        return new Posta();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.raziskovalec.domain.builders.AbstractBuilder#self()
     */
    @Override
    protected PostaBuilder self()
    {
        return this;
    }
}