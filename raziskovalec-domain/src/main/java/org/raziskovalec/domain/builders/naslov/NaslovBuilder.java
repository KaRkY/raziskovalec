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
package org.raziskovalec.domain.builders.naslov;

import org.raziskovalec.domain.builders.AbstractBuilder;
import org.raziskovalec.domain.naslov.Mesto;
import org.raziskovalec.domain.naslov.Naslov;
import org.raziskovalec.domain.naslov.Posta;

/**
 * Builder za {@link Naslov}
 * 
 * @author Rene Svetina
 * 
 */
public class NaslovBuilder extends AbstractBuilder<Naslov, NaslovBuilder>
{
    
    /**
     * @param hisnaStevilka
     *            Hišna številka
     * @return builder
     */
    public NaslovBuilder hisnaStevilka(final String hisnaStevilka)
    {
        getValue().setHisnaStevilka(hisnaStevilka);
        return self();
    }
    
    /**
     * @param ulica
     *            Ulica
     * @return builder
     */
    public NaslovBuilder ulica(final String ulica)
    {
        getValue().setUlica(ulica);
        return self();
    }
    
    /**
     * @param mesto
     *            Mesto v katerem je {@link Naslov}
     * @return builder
     */
    public NaslovBuilder mesto(final Mesto mesto)
    {
        getValue().setMesto(mesto);
        return self();
    }
    
    /**
     * @param posta
     *            Pošta v kateri je {@link Naslov}
     * @return builder
     */
    public NaslovBuilder posta(final Posta posta)
    {
        getValue().setPosta(posta);
        return self();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.raziskovalec.domain.builders.AbstractBuilder#getObject()
     */
    @Override
    protected Naslov getObject()
    {
        return new Naslov();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.raziskovalec.domain.builders.AbstractBuilder#self()
     */
    @Override
    protected NaslovBuilder self()
    {
        return this;
    }
    
}
