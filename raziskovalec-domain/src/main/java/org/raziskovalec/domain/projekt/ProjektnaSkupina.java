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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.raziskovalec.domain.raziskovanje.Organizacija;
import org.raziskovalec.domain.raziskovanje.Raziskovalec;
import org.testng.internal.annotations.Sets;

import com.google.common.collect.ImmutableSet;

/**
 * @author Rene Svetina
 * 
 */
@Entity
public class ProjektnaSkupina implements Serializable
{
    private static final long serialVersionUID = 7396808074849756293L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;
    @Column(nullable = false, unique = true)
    private String            ime;
    @Column(nullable = false)
    private String            telefonskaStevilka;
    @Column(nullable = false)
    private String            email;
    private String            www;
    @ManyToOne(optional = false)
    @JoinColumn
    private Raziskovalec      vodja;
    @ManyToOne(optional = false)
    @JoinColumn
    private Organizacija      vodilnaOrganizacija;
    @ManyToMany
    private Set<Organizacija> sudelujoceOrganizacije;
    @OneToMany(mappedBy = "vodilnaSkupina")
    private Set<Projekt>      projekti;
    @ManyToMany(mappedBy = "sudelujoceSkupine")
    private Set<Projekt>      sodelujeV;
    @ManyToMany
    private Set<Raziskovalec> raziskovalci;
    
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
     * @return the telefonskaStevilka
     */
    public String getTelefonskaStevilka()
    {
        return telefonskaStevilka;
    }
    
    /**
     * @param telefonskaStevilka
     *            the telefonskaStevilka to set
     */
    public void setTelefonskaStevilka(final String telefonskaStevilka)
    {
        this.telefonskaStevilka = telefonskaStevilka;
    }
    
    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }
    
    /**
     * @return the www
     */
    public String getWww()
    {
        return www;
    }
    
    /**
     * @param www
     *            the www to set
     */
    public void setWww(final String www)
    {
        this.www = www;
    }
    
    /**
     * @return the vodja
     */
    public Raziskovalec getVodja()
    {
        return vodja;
    }
    
    /**
     * @param vodja
     *            the vodja to set
     */
    public void setVodja(final Raziskovalec vodja)
    {
        this.vodja = vodja;
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
     * @return the sudelujoceOrganizacije
     */
    public Set<Organizacija> getSudelujoceOrganizacije()
    {
        return ImmutableSet.copyOf(sudelujoceOrganizacije);
    }
    
    /**
     * @param sudelujocaOrganizacija
     *            the sudelujoceOrganizacije to set
     */
    public void addSudelujoceOrganizacije(final Organizacija sudelujocaOrganizacija)
    {
        if (sudelujoceOrganizacije == null)
            sudelujoceOrganizacije = Sets.newHashSet();
        sudelujoceOrganizacije.add(checkNotNull(sudelujocaOrganizacija, "Sudelujoca organizacija nemore biti null"));
    }
    
    /**
     * @return the projekti
     */
    public Set<Projekt> getProjekti()
    {
        return ImmutableSet.copyOf(projekti);
    }
    
    /**
     * @param projekti
     *            the projekti to set
     */
    public void setProjekti(final Projekt projekt)
    {
        if (projekti == null)
            projekti = Sets.newHashSet();
        projekti.add(checkNotNull(projekt, "Projekt nemore biti null"));
    }
    
    /**
     * @return the sodelujeV
     */
    public Set<Projekt> getSodelujeV()
    {
        return ImmutableSet.copyOf(sodelujeV);
    }
    
    /**
     * @param sodelujeV
     *            the sodelujeV to set
     */
    public void setSodelujeV(final Projekt sodelujeV)
    {
        if (this.sodelujeV == null)
            this.sodelujeV = Sets.newHashSet();
        this.sodelujeV.add(checkNotNull(sodelujeV, "Sodeluje v nemore biti null"));
    }
    
    /**
     * @return the raziskovalci
     */
    public Set<Raziskovalec> getRaziskovalci()
    {
        return ImmutableSet.copyOf(raziskovalci);
    }
    
    /**
     * @param raziskovalci
     *            the raziskovalci to set
     */
    public void setRaziskovalci(final Raziskovalec raziskovalcec)
    {
        if (raziskovalci == null)
            raziskovalci = Sets.newHashSet();
        raziskovalci.add(checkNotNull(raziskovalcec, "Raziskovalec nemore biti null"));
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
        final ProjektnaSkupina equalTo = (ProjektnaSkupina) obj;
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
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("Id", getId())
        .append("Ime", getIme())
        .append("Telefonska številka", getTelefonskaStevilka())
        .append("E-mail", getEmail())
        .append("Website", getWww())
        .build();
    }
}
