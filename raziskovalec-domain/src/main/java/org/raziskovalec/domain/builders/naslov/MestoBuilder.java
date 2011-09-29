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

/**
 * Builder za {@link Mesto}
 * 
 * @author Rene Svetina
 * 
 */
public class MestoBuilder extends AbstractBuilder<Mesto, MestoBuilder>
{
    
    /**
     * @param ime
     *            ime mesta
     * @return builder
     */
    public MestoBuilder ime(final String ime)
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
    protected Mesto getObject()
    {
        return new Mesto();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.raziskovalec.domain.builders.AbstractBuilder#self()
     */
    @Override
    protected MestoBuilder self()
    {
        return this;
    }
    
}
