/**
 * Copyright 2011 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.raziskovalec.web.user;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Researcher data to be added.
 * 
 * @author Rene Svetina
 */
public final class ResearcherBean implements
									Serializable
{
	// ========================================================================
	// Fields
	// ========================================================================
	private static final long	serialVersionUID	= 4905370220866247144L;
	private String				email;
	private String				lastname;
	private String				name;
	private String				telephoneNumber;
	private String				www;

	// ========================================================================
	// Methods
	// ========================================================================

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname()
	{
		return lastname;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber()
	{
		return telephoneNumber;
	}

	/**
	 * @return the www
	 */
	public String getWww()
	{
		return www;
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
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(final String lastname)
	{
		this.lastname = lastname;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * @param telephoneNumber
	 *            the telephoneNumber to set
	 */
	public void setTelephoneNumber(final String telephoneNumber)
	{
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @param www
	 *            the www to set
	 */
	public void setWww(final String www)
	{
		this.www = www;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("Name", getName())
				.append("Lastname", getLastname())
				.append("Email", getEmail())
				.append("Telephonenumber", getTelephoneNumber())
				.append("WWW", getWww())
				.build();
	}
}
