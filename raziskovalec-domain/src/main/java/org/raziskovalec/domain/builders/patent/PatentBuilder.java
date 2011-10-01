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
package org.raziskovalec.domain.builders.patent;

import org.raziskovalec.domain.builders.AbstractBuilder;
import org.raziskovalec.domain.patent.Patent;
import org.raziskovalec.domain.raziskovanje.Organizacija;
import org.raziskovalec.domain.raziskovanje.Raziskovalec;

/**
 * Builder za {@link Patent}
 * 
 * @author Rene Svetina
 * 
 */
public class PatentBuilder extends AbstractBuilder<Patent, PatentBuilder>
{
    /**
     * @param ime
     *            ime patenta
     * @return builder
     */
    public PatentBuilder ime(final String ime)
    {
        getValue().setIme(ime);
        return self();
    }
    
    /**
     * @param lastnikRaziskovalec
     *            lastnik
     * @return builder
     */
    public PatentBuilder lastnikRaziskovalec(final Raziskovalec lastnikRaziskovalec)
    {
        getValue().setLastnikRaziskovalec(lastnikRaziskovalec);
        return self();
    }
    
    /**
     * @param lastnikOrganizacija
     *            lastnik
     * @return builder
     */
    public PatentBuilder lastnikOrganizacija(final Organizacija lastnikOrganizacija)
    {
        getValue().setLastnikOrganizacija(lastnikOrganizacija);
        return self();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.raziskovalec.domain.builders.AbstractBuilder#getObject()
     */
    @Override
    protected Patent getObject()
    {
        return new Patent();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.raziskovalec.domain.builders.AbstractBuilder#self()
     */
    @Override
    protected PatentBuilder self()
    {
        return this;
    }
    
}
