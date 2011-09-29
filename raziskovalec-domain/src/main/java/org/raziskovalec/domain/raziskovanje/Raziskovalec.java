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
package org.raziskovalec.domain.raziskovanje;

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
import org.raziskovalec.domain.naslov.Naslov;
import org.raziskovalec.domain.patent.Patent;
import org.raziskovalec.domain.projekt.Projekt;
import org.raziskovalec.domain.projekt.ProjektnaSkupina;
import org.raziskovalec.domain.znanja.Naziv;
import org.raziskovalec.domain.znanja.Znanje;

/**
 * @author Rene Svetina
 * 
 */
@Entity
public class Raziskovalec
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                  id;
    @Column(nullable = false)
    private String                ime;
    @Column(nullable = false)
    private String                priimek;
    @Column(nullable = false)
    private String                telefonskaStevilka;
    @Column(nullable = false, unique = true)
    private String                email;
    private String                www;
    @ManyToOne(optional = false)
    @JoinColumn
    private Organizacija          organizacija;
    @ManyToOne(optional = false)
    @JoinColumn
    private Naslov                naslov;
    @ManyToMany
    private Set<Naziv>            nazivi;
    @ManyToMany(mappedBy = "neodvisniRaziskovalci")
    private Set<Projekt>          projekti;
    @OneToMany(mappedBy = "projektniVodja")
    private Set<Projekt>          vodiProjekte;
    @ManyToMany
    private Set<Znanje>           znanja;
    @ManyToMany(mappedBy = "raziskovalci")
    private Set<ProjektnaSkupina> projektneSkupine;
    @OneToMany(mappedBy = "vodja")
    private Set<ProjektnaSkupina> vodiSkupine;
    @OneToMany(mappedBy = "lastnikRaziskovalec")
    private Set<Patent>           lastniskiPatenti;
    
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
     * @return the priimek
     */
    public String getPriimek()
    {
        return priimek;
    }
    
    /**
     * @param priimek
     *            the priimek to set
     */
    public void setPriimek(final String priimek)
    {
        this.priimek = priimek;
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
     * @return the organizacija
     */
    public Organizacija getOrganizacija()
    {
        return organizacija;
    }
    
    /**
     * @param organizacija
     *            the organizacija to set
     */
    public void setOrganizacija(final Organizacija organizacija)
    {
        this.organizacija = organizacija;
    }
    
    /**
     * @return the naslov
     */
    public Naslov getNaslov()
    {
        return naslov;
    }
    
    /**
     * @param naslov
     *            the naslov to set
     */
    public void setNaslov(final Naslov naslov)
    {
        this.naslov = naslov;
    }
    
    /**
     * @return the nazivi
     */
    public Set<Naziv> getNazivi()
    {
        return nazivi;
    }
    
    /**
     * @param nazivi
     *            the nazivi to set
     */
    public void setNazivi(final Set<Naziv> nazivi)
    {
        this.nazivi = nazivi;
    }
    
    /**
     * @return the projekti
     */
    public Set<Projekt> getProjekti()
    {
        return projekti;
    }
    
    /**
     * @param projekti
     *            the projekti to set
     */
    public void setProjekti(final Set<Projekt> projekti)
    {
        this.projekti = projekti;
    }
    
    /**
     * @return the vodiProjekte
     */
    public Set<Projekt> getVodiProjekte()
    {
        return vodiProjekte;
    }
    
    /**
     * @param vodiProjekte
     *            the vodiProjekte to set
     */
    public void setVodiProjekte(final Set<Projekt> vodiProjekte)
    {
        this.vodiProjekte = vodiProjekte;
    }
    
    /**
     * @return the znanja
     */
    public Set<Znanje> getZnanja()
    {
        return znanja;
    }
    
    /**
     * @param znanja
     *            the znanja to set
     */
    public void setZnanja(final Set<Znanje> znanja)
    {
        this.znanja = znanja;
    }
    
    /**
     * @return the projektneSkupine
     */
    public Set<ProjektnaSkupina> getProjektneSkupine()
    {
        return projektneSkupine;
    }
    
    /**
     * @param projektneSkupine
     *            the projektneSkupine to set
     */
    public void setProjektneSkupine(final Set<ProjektnaSkupina> projektneSkupine)
    {
        this.projektneSkupine = projektneSkupine;
    }
    
    /**
     * @return the vodiSkupine
     */
    public Set<ProjektnaSkupina> getVodiSkupine()
    {
        return vodiSkupine;
    }
    
    /**
     * @param vodiSkupine
     *            the vodiSkupine to set
     */
    public void setVodiSkupine(final Set<ProjektnaSkupina> vodiSkupine)
    {
        this.vodiSkupine = vodiSkupine;
    }
    
    /**
     * @return the lastniskiPatenti
     */
    public Set<Patent> getLastniskiPatenti()
    {
        return lastniskiPatenti;
    }
    
    /**
     * @param lastniskiPatenti
     *            the lastniskiPatenti to set
     */
    public void setLastniskiPatenti(final Set<Patent> lastniskiPatenti)
    {
        this.lastniskiPatenti = lastniskiPatenti;
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
        final Raziskovalec equalTo = (Raziskovalec) obj;
        return new EqualsBuilder().append(getIme(), equalTo.getIme())
                .append(getPriimek(), equalTo.getPriimek())
                .append(getTelefonskaStevilka(), equalTo.getTelefonskaStevilka())
                .append(getEmail(), equalTo.getEmail())
                .isEquals();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 31).append(getIme())
                .append(getPriimek())
                .append(getTelefonskaStevilka())
                .append(getEmail())
                .build();
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
                .append("Priimek", getPriimek())
                .append("Telefonska številka", getTelefonskaStevilka())
                .append("E-mail", getEmail())
                .append("Website", getWww())
                .build();
    }
}
