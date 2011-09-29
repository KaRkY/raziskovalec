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
package org.raziskovalec.domain.patent;

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
import org.raziskovalec.domain.raziskovanje.Organizacija;
import org.raziskovalec.domain.raziskovanje.Raziskovalec;

/**
 * @author Rene Svetina
 * 
 */
@Entity
public class Patent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long         id;
    @Column(nullable = false, unique = true)
    private String       ime;
    @ManyToOne
    @JoinColumn
    private Raziskovalec lastnikRaziskovalec;
    @ManyToOne
    @JoinColumn
    private Organizacija lastnikOrganizacija;
    
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
     * @return the lastnikRaziskovalec
     */
    public Raziskovalec getLastnikRaziskovalec()
    {
        return lastnikRaziskovalec;
    }
    
    /**
     * @param lastnikRaziskovalec
     *            the lastnikRaziskovalec to set
     */
    public void setLastnikRaziskovalec(final Raziskovalec lastnikRaziskovalec)
    {
        this.lastnikRaziskovalec = lastnikRaziskovalec;
    }
    
    /**
     * @return the lastnikOrganizacija
     */
    public Organizacija getLastnikOrganizacija()
    {
        return lastnikOrganizacija;
    }
    
    /**
     * @param lastnikOrganizacija
     *            the lastnikOrganizacija to set
     */
    public void setLastnikOrganizacija(final Organizacija lastnikOrganizacija)
    {
        this.lastnikOrganizacija = lastnikOrganizacija;
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
        final Patent equalTo = (Patent) obj;
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
                .build();
    }
}
