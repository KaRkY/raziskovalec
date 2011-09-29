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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Rene Svetina
 * 
 */
@Entity
public class Naslov
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    @Column(nullable = false)
    private String hisnaStevilka;
    @Column(nullable = false)
    private String ulica;
    @ManyToOne(optional = false)
    @JoinColumn
    private Mesto  mesto;
    @ManyToOne(optional = false)
    @JoinColumn
    private Posta  posta;
    
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
     * @return the hisnaStevilka
     */
    public String getHisnaStevilka()
    {
        return hisnaStevilka;
    }
    
    /**
     * @param hisnaStevilka
     *            the hisnaStevilka to set
     */
    public void setHisnaStevilka(final String hisnaStevilka)
    {
        this.hisnaStevilka = hisnaStevilka;
    }
    
    /**
     * @return the ulica
     */
    public String getUlica()
    {
        return ulica;
    }
    
    /**
     * @param ulica
     *            the ulica to set
     */
    public void setUlica(final String ulica)
    {
        this.ulica = ulica;
    }
    
    /**
     * @return the mesto
     */
    public Mesto getMesto()
    {
        return mesto;
    }
    
    /**
     * @param mesto
     *            the mesto to set
     */
    public void setMesto(final Mesto mesto)
    {
        this.mesto = mesto;
    }
    
    /**
     * @return the posta
     */
    public Posta getPosta()
    {
        return posta;
    }
    
    /**
     * @param posta
     *            the posta to set
     */
    public void setPosta(final Posta posta)
    {
        this.posta = posta;
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
        final Naslov equalTo = (Naslov) obj;
        return new EqualsBuilder().append(getHisnaStevilka(), equalTo.getHisnaStevilka())
                .append(getUlica(), equalTo.getUlica())
                .append(getMesto(), equalTo.getMesto())
                .append(getPosta(), equalTo.getPosta())
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
        return new HashCodeBuilder(17, 31).append(getHisnaStevilka())
                .append(getUlica())
                .append(getMesto())
                .append(getPosta())
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
                .append("Hišna številka", getHisnaStevilka())
                .append("Ulica", getUlica())
                .build();
    }
}
