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

import org.raziskovalec.builders.AbstractBuilder;

/**
 * Builder za {@link Naslov}
 *
 * @author Rene Svetina
 *
 */
public class NaslovBuilder<V extends Naslov, T extends NaslovBuilder<?, ?>> extends AbstractBuilder<V, T>
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
     * @param hisnaStevilka
     *            Hišna številka
     * @return builder
     */
    public T hisnaStevilka(final String hisnaStevilka)
    {
        getValue().setHisnaStevilka(hisnaStevilka);
        return self();
    }

    /**
     * @param ulica
     *            Ulica
     * @return builder
     */
    public T ulica(final String ulica)
    {
        getValue().setUlica(ulica);
        return self();
    }

    /**
     * @param mesto
     *            Mesto v katerem je {@link Naslov}
     * @return builder
     */
    public T mesto(final Mesto mesto)
    {
        getValue().setMesto(mesto);
        return self();
    }

    /**
     * @param mesto
     *            builder za mesto
     * @return this
     */
    public T mesto(final MestoBuilder<? extends Mesto, ? extends MestoBuilder<?, ?>> mesto)
    {
        getValue().setMesto(mesto.build());
        return self();
    }

    /**
     * @param posta
     *            Pošta v kateri je {@link Naslov}
     * @return builder
     */
    public T posta(final Posta posta)
    {
        getValue().setPosta(posta);
        return self();
    }

    /**
     * @param posta
     *            builder za posto
     * @return this
     */
    public T posta(final PostaBuilder<? extends Posta, ? extends PostaBuilder<?, ?>> posta)
    {
        getValue().setPosta(posta.build());
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
        return (V) new Naslov();
    }

    public static NaslovBuilder<? extends Naslov, ? extends NaslovBuilder<?, ?>> naslov()
    {
        return new NaslovBuilder<>();
    }
}
