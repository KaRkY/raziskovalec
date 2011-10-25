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
package org.raziskovalec.domain.projekt;

import org.joda.time.LocalDate;
import org.raziskovalec.builders.AbstractBuilder;
import org.raziskovalec.domain.raziskovanje.Organizacija;
import org.raziskovalec.domain.raziskovanje.OrganizacijaBuilder;
import org.raziskovalec.domain.raziskovanje.Raziskovalec;
import org.raziskovalec.domain.raziskovanje.RaziskovalecBuilder;

/**
 * @author Rene Svetina
 *
 */
public class ProjektBuilder<V extends Projekt, T extends ProjektBuilder<?, ?>> extends AbstractBuilder<V, T>
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

    public T ime(final String ime)
    {
        getValue().setIme(ime);
        return self();
    }

    public T datumZacetka(final LocalDate datumZacetka)
    {
        getValue().setDatumZacetka(datumZacetka);
        return self();
    }

    public T datumZakljucka(final LocalDate datumZakljucka)
    {
        getValue().setDatumZakljucka(datumZakljucka);
        return self();
    }

    public T opis(final String opis)
    {
        getValue().setOpis(opis);
        return self();
    }

    public T addTag(final Tag tag)
    {
        getValue().addTag(tag);
        return self();
    }

    public T addTag(final TagBuilder<? extends Tag, ? extends TagBuilder<?, ?>> tag)
    {
        getValue().addTag(tag.build());
        return self();
    }

    public T vodilnaOrganizacija(final Organizacija vodilnaOrganizacija)
    {
        getValue().setVodilnaOrganizacija(vodilnaOrganizacija);
        return self();
    }

    public T vodilnaOrganizacija(
            final OrganizacijaBuilder<? extends Organizacija, ? extends OrganizacijaBuilder<?, ?>> vodilnaOrganizacija)
    {
        getValue().setVodilnaOrganizacija(vodilnaOrganizacija.build());
        return self();
    }

    public T vodilnaSkupina(final ProjektnaSkupina vodilnaSkupina)
    {
        getValue().setVodilnaSkupina(vodilnaSkupina);
        return self();
    }

    public T vodilnaSkupina(
            final ProjektnaSkupinaBuilder<? extends ProjektnaSkupina, ? extends ProjektnaSkupinaBuilder<?, ?>> vodilnaSkupina)
    {
        getValue().setVodilnaSkupina(vodilnaSkupina.build());
        return self();
    }

    public T addSudelujocoOrganizacijo(final Organizacija organizacija)
    {
        getValue().addSudelujocoOrganizacijo(organizacija);
        return self();
    }

    public T addSudelujocoOrganizacijo(
            final OrganizacijaBuilder<? extends Organizacija, ? extends OrganizacijaBuilder<?, ?>> organizacija)
    {
        getValue().addSudelujocoOrganizacijo(organizacija.build());
        return self();
    }

    public T addSudelujocoSkupino(final ProjektnaSkupina sudelujocaSkupina)
    {
        getValue().addSudelujocoSkupino(sudelujocaSkupina);
        return self();
    }

    public T addSudelujocoSkupino(
            final ProjektnaSkupinaBuilder<? extends ProjektnaSkupina, ? extends ProjektnaSkupinaBuilder<?, ?>> sudelujocaSkupina)
    {
        getValue().addSudelujocoSkupino(sudelujocaSkupina.build());
        return self();
    }

    public T projektniVodja(final Raziskovalec projektniVodja)
    {
        getValue().setProjektniVodja(projektniVodja);
        return self();
    }

    public T projektniVodja(
            final RaziskovalecBuilder<? extends Raziskovalec, ? extends RaziskovalecBuilder<?, ?>> projektniVodja)
    {
        getValue().setProjektniVodja(projektniVodja.build());
        return self();
    }

    public T addNeodvisnegaRaziskovalca(final Raziskovalec neodvisniRaziskovalec)
    {
        getValue().addNeodvisnegaRaziskovalca(neodvisniRaziskovalec);
        return self();
    }

    public T addNeodvisnegaRaziskovalca(
            final RaziskovalecBuilder<? extends Raziskovalec, ? extends RaziskovalecBuilder<?, ?>> neodvisniRaziskovalec)
    {
        getValue().addNeodvisnegaRaziskovalca(neodvisniRaziskovalec.build());
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
        return (V) new Projekt();
    }

}
