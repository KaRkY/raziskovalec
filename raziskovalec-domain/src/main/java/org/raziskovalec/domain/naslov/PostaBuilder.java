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
package org.raziskovalec.domain.naslov;

import org.raziskovalec.domain.AbstractBuilder;

/**
 * Bilder za {@link Posta}
 *
 * @author Rene Svetina
 *
 */
public class PostaBuilder<V extends Posta, T extends PostaBuilder<?, ?>> extends AbstractBuilder<V, T>
{
    /**
     * @param id
     *            id
     * @return builder
     */
    public T id(final Long id)
    {
        getValue().setId(id);
        return self();
    }

    /**
     * @param postnaStevilka
     *            Poštna številka
     * @return builder
     */
    public T postnaStevilka(final Integer postnaStevilka)
    {
        getValue().setPostnaStevilka(postnaStevilka);
        return self();
    }

    /**
     * @param ime
     *            Ime pošte
     * @return builder
     */
    public T ime(final String ime)
    {
        getValue().setIme(ime);
        return self();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.raziskovalec.domain.builders.AbstractBuilder#getObject()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected V getObject()
    {
        return (V) new Posta();
    }

    public static PostaBuilder<? extends Posta, ? extends PostaBuilder<?, ?>> posta()
    {
        return new PostaBuilder<>();
    }
}
