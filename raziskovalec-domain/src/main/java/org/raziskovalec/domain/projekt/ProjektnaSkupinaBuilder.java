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

import org.raziskovalec.builders.AbstractBuilder;
import org.raziskovalec.domain.raziskovanje.Organizacija;
import org.raziskovalec.domain.raziskovanje.OrganizacijaBuilder;
import org.raziskovalec.domain.raziskovanje.Raziskovalec;
import org.raziskovalec.domain.raziskovanje.RaziskovalecBuilder;

/**
 * @author Rene Svetina
 *
 */
public class ProjektnaSkupinaBuilder<V extends ProjektnaSkupina, T extends ProjektnaSkupinaBuilder<?, ?>>
                                                                                                          extends
                                                                                                          AbstractBuilder<V, T>
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

    public T telefonskaStevilka(final String telefonskaStevilka)
    {
        getValue().setTelefonskaStevilka(telefonskaStevilka);
        return self();
    }

    public T email(final String email)
    {
        getValue().setEmail(email);
        return self();
    }

    public T www(final String www)
    {
        getValue().setWww(www);
        return self();
    }

    public T vodja(final Raziskovalec vodja)
    {
        getValue().setVodja(vodja);
        return self();
    }

    public T vodja(final RaziskovalecBuilder<? extends Raziskovalec, ? extends RaziskovalecBuilder<?, ?>> vodja)
    {
        getValue().setVodja(vodja.build());
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

    public T addProjekt(final Projekt projekt)
    {
        getValue().addProjekt(projekt);
        return self();
    }

    public T addProjekt(final ProjektBuilder<? extends Projekt, ? extends ProjektBuilder<?, ?>> projekt)
    {
        getValue().addProjekt(projekt.build());
        return self();
    }

    public T addSudelujeV(final Projekt projekt)
    {
        getValue().addSodelujeV(projekt);
        return self();
    }

    public T addSudelujeV(final ProjektBuilder<? extends Projekt, ? extends ProjektBuilder<?, ?>> projekt)
    {
        getValue().addSodelujeV(projekt.build());
        return self();
    }

    public T addRaziskovalec(final Raziskovalec raziskovalec)
    {
        getValue().addRaziskovalec(raziskovalec);
        return self();
    }

    public T addRaziskovalec(
            final RaziskovalecBuilder<? extends Raziskovalec, ? extends RaziskovalecBuilder<?, ?>> raziskovalec)
    {
        getValue().addRaziskovalec(raziskovalec.build());
        return self();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.raziskovalec.builders.AbstractBuilder#getObject()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected V getObject()
    {
        return (V) new ProjektnaSkupina();
    }

    public static ProjektnaSkupinaBuilder<? extends ProjektnaSkupina, ? extends ProjektnaSkupinaBuilder<?, ?>> projektnaSkupina()
    {
        return new ProjektnaSkupinaBuilder<>();
    }

}
