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
package org.raziskovalec.domain.value;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Postcode for address.
 * 
 * @author Rene Svetina
 * 
 */
public final class Postcode implements
		Serializable
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private static final long	serialVersionUID	= 8996124494460276539L;
	private static final int	HASH_PRIME			= 17;
	private static final int	HASH_MULTIPLIER		= 31;
	private final String		code;
	private final String		name;
	private static Pattern		postalPattern;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	static
	{
		postalPattern = Pattern.compile("^\\[([A-Za-z0-9]+)\\](.*)$");
	}

	private Postcode(final String code, final String name)
	{
		checkNotNull(code, "Code should not be null.");
		checkNotNull(name, "name should not be null.");
		checkArgument(!code.isEmpty(), "Code should not be empty.");
		checkArgument(!name.isEmpty(), "Name should not be empty.");

		this.code = code;
		this.name = name;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	/**
	 * Postal code.
	 * 
	 * @return Postal code.
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Postal name.
	 * 
	 * @return Postal name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Postalcode.
	 * 
	 * @param code
	 *            postal code
	 * @param name
	 *            postal name
	 * @return Postcode
	 */
	public static Postcode valueOf(final String code, final String name)
	{
		return new Postcode(code, name);
	}

	/**
	 * Postcode from format string [code]name.
	 * 
	 * @param postalCode
	 *            formated string.
	 * @return Postcode
	 */
	public static Postcode valueOf(final String postalCode)
	{
		Matcher postalMatcher = postalPattern.matcher(postalCode);
		checkArgument(postalMatcher.matches(), "Wrong postalCode format, format should be [<code>]<name>");

		return valueOf(postalMatcher.group(1), postalMatcher.group(2));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final HashCodeBuilder builder = new HashCodeBuilder(HASH_PRIME, HASH_MULTIPLIER);

		builder.append(code);

		return builder.build();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		final Postcode that = (Postcode) obj;
		final EqualsBuilder builder = new EqualsBuilder();

		builder.append(code, that.code);

		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("[%s]%s", code, name);
	}
}
