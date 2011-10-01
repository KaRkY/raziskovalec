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
package org.raziskovalec.service.domain.projekt;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;
import org.raziskovalec.service.domain.raziskovanje.Organizacija;
import org.raziskovalec.service.domain.raziskovanje.Raziskovalec;

/**
 * @author Rene Svetina
 * 
 */
@Entity
public class Projekt implements Serializable
{
    private static final long     serialVersionUID = -6388127217228163547L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                  id;
    @Column(nullable = false, unique = true)
    private String                ime;
    @Column(nullable = false)
    private LocalDate             datumZacetka;
    private LocalDate             datumZakljucka;
    @Column(nullable = false)
    private String                opis;
    @Column(nullable = false)
    private String                namen;
    @ManyToMany
    @JoinTable
    private Set<Tag>              tags;
    @ManyToOne(optional = false)
    @JoinColumn
    private Organizacija          vodilnaOrganizacija;
    @ManyToOne(optional = false)
    @JoinColumn
    private ProjektnaSkupina      vodilnaSkupina;
    @ManyToMany
    private Set<Organizacija>     sudelujoceOrganizacije;
    @ManyToMany
    private Set<ProjektnaSkupina> sudelujoceSkupine;
    @ManyToOne(optional = false)
    @JoinColumn
    private Raziskovalec          projektniVodja;
    @ManyToMany
    private Set<Raziskovalec>     neodvisniRaziskovalci;
    
    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }
    
    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id)
    {
        this.id = id;
    }
    
    /**
     * @return the ime
     */
    public String getIme()
    {
        return ime;
    }
    
    /**
     * @param ime
     *            the ime to set
     */
    public void setIme(final String ime)
    {
        this.ime = ime;
    }
    
    /**
     * @return the datumZacetka
     */
    public LocalDate getDatumZacetka()
    {
        return datumZacetka;
    }
    
    /**
     * @param datumZacetka
     *            the datumZacetka to set
     */
    public void setDatumZacetka(final LocalDate datumZacetka)
    {
        this.datumZacetka = datumZacetka;
    }
    
    /**
     * @return the datumZakljucka
     */
    public LocalDate getDatumZakljucka()
    {
        return datumZakljucka;
    }
    
    /**
     * @param datumZakljucka
     *            the datumZakljucka to set
     */
    public void setDatumZakljucka(final LocalDate datumZakljucka)
    {
        this.datumZakljucka = datumZakljucka;
    }
    
    /**
     * @return the opis
     */
    public String getOpis()
    {
        return opis;
    }
    
    /**
     * @param opis
     *            the opis to set
     */
    public void setOpis(final String opis)
    {
        this.opis = opis;
    }
    
    /**
     * @return the namen
     */
    public String getNamen()
    {
        return namen;
    }
    
    /**
     * @param namen
     *            the namen to set
     */
    public void setNamen(final String namen)
    {
        this.namen = namen;
    }
    
    /**
     * @return the tags
     */
    public Set<Tag> getTags()
    {
        return tags;
    }
    
    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(final Set<Tag> tags)
    {
        this.tags = tags;
    }
    
    /**
     * @return the vodilnaOrganizacija
     */
    public Organizacija getVodilnaOrganizacija()
    {
        return vodilnaOrganizacija;
    }
    
    /**
     * @param vodilnaOrganizacija
     *            the vodilnaOrganizacija to set
     */
    public void setVodilnaOrganizacija(final Organizacija vodilnaOrganizacija)
    {
        this.vodilnaOrganizacija = vodilnaOrganizacija;
    }
    
    /**
     * @return the vodilnaSkupina
     */
    public ProjektnaSkupina getVodilnaSkupina()
    {
        return vodilnaSkupina;
    }
    
    /**
     * @param vodilnaSkupina
     *            the vodilnaSkupina to set
     */
    public void setVodilnaSkupina(final ProjektnaSkupina vodilnaSkupina)
    {
        this.vodilnaSkupina = vodilnaSkupina;
    }
    
    /**
     * @return the sudelujoceOrganizacije
     */
    public Set<Organizacija> getSudelujoceOrganizacije()
    {
        return sudelujoceOrganizacije;
    }
    
    /**
     * @param sudelujoceOrganizacije
     *            the sudelujoceOrganizacije to set
     */
    public void setSudelujoceOrganizacije(final Set<Organizacija> sudelujoceOrganizacije)
    {
        this.sudelujoceOrganizacije = sudelujoceOrganizacije;
    }
    
    /**
     * @return the sudelujoceSkupine
     */
    public Set<ProjektnaSkupina> getSudelujoceSkupine()
    {
        return sudelujoceSkupine;
    }
    
    /**
     * @param sudelujoceSkupine
     *            the sudelujoceSkupine to set
     */
    public void setSudelujoceSkupine(final Set<ProjektnaSkupina> sudelujoceSkupine)
    {
        this.sudelujoceSkupine = sudelujoceSkupine;
    }
    
    /**
     * @return the projektniVodja
     */
    public Raziskovalec getProjektniVodja()
    {
        return projektniVodja;
    }
    
    /**
     * @param projektniVodja
     *            the projektniVodja to set
     */
    public void setProjektniVodja(final Raziskovalec projektniVodja)
    {
        this.projektniVodja = projektniVodja;
    }
    
    /**
     * @return the neodvisniRaziskovalci
     */
    public Set<Raziskovalec> getNeodvisniRaziskovalci()
    {
        return neodvisniRaziskovalci;
    }
    
    /**
     * @param neodvisniRaziskovalci
     *            the neodvisniRaziskovalci to set
     */
    public void setNeodvisniRaziskovalci(final Set<Raziskovalec> neodvisniRaziskovalci)
    {
        this.neodvisniRaziskovalci = neodvisniRaziskovalci;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        final Projekt equalTo = (Projekt) obj;
        return new EqualsBuilder().append(getIme(), equalTo.getIme()).isEquals();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 31).append(getIme()).build();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("Id", getId())
                .append("Ime", getIme())
                .append("Datum začetka", getDatumZacetka())
                .append("Datum zaključka", getDatumZakljucka())
                .append("Opis", getOpis())
                .append("Namen", getNamen())
                .build();
    }
}
