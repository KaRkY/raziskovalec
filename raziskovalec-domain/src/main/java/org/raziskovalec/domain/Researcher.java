/**
 * Copyright 2012 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.raziskovalec.domain;

import java.io.Serializable;

import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.raziskovalec.domain.value.Identifier;
import org.raziskovalec.domain.value.Name;

/**
 * Researcher domain class.
 * 
 * @author Rene Svetina
 */
public class Researcher implements
						Serializable
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================

	private static final long	serialVersionUID	= 5018104292542842256L;
	private static final int	HASH_MULTIPLIER		= 31;
	private static final int	HASH_PRIME			= 17;
	private final Identifier	id;
	private Name				name;
	private Name				lastName;
	private InternetAddress		email;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	/**
	 * Default constructor.
	 */
	public Researcher()
	{
		id = Identifier.newId();
	}

	/**
	 * Initializes researcher.
	 * 
	 * @param id
	 *            unique id
	 * @param name
	 *            Personal name
	 * @param lastName
	 *            lastName
	 */
	public Researcher(final Identifier id, final Name name, final Name lastName)
	{
		this.id = id;
		this.name = name;
		this.lastName = lastName;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	/**
	 * @return the name
	 */
	public Name getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final Name name)
	{
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public Name getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final Name lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public InternetAddress getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final InternetAddress email)
	{
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public Identifier getId()
	{
		return id;
	}

	@Override
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder(HASH_PRIME, HASH_MULTIPLIER);

		builder.append(id);

		return builder.build();
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (obj == this)
		{
			return true;
		}
		if (obj.getClass() != getClass())
		{
			return false;
		}
		final Researcher that = (Researcher) obj;
		final EqualsBuilder builder = new EqualsBuilder();

		builder.append(id, that.id);

		return builder.isEquals();
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

		builder.append("id", id);
		builder.append("name", name);
		builder.append("lastName", lastName);
		builder.append("email", email);

		return builder.build();
	}
}
