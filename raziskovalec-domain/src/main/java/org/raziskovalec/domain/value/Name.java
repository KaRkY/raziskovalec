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
package org.raziskovalec.domain.value;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Value class for handling personal names.
 * 
 * @author Rene Svetina
 * 
 */
public final class Name implements
		Serializable
{
	// ========================================================================
	// Fields
	// ========================================================================
	private static final long serialVersionUID = -7175433342123178074L;
	private static final int HASH_MULTIPLIER = 31;
	private static final int HASH_PRIME = 17;
	public static final Name EMPTY = new Name();
	private final String name;

	// ========================================================================
	// Constructors
	// ========================================================================
	private Name()
	{
		name = null;
	}

	private Name(final String name)
	{
		checkNotNull(name, "Name can not be null.");
		checkArgument(!name.isEmpty(), "Name can not be empty.");
		checkArgument(!name.equals("N/A"), "Name can not be N/A");

		this.name = name;
	}

	// ========================================================================
	// Methods
	// ========================================================================

	/**
	 * Return a Name respresentation of string.
	 * 
	 * @param name
	 *            Name
	 * @return Name
	 */
	public static Name valueOf(final String name)
	{
		return new Name(name);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder(HASH_PRIME, HASH_MULTIPLIER);

		builder.append(name);

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
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Name that = (Name) obj;

		EqualsBuilder builder = new EqualsBuilder();

		builder.append(name, that.name);

		return builder.build();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		if (name != null)
		{
			return name;
		}
		else
		{
			return "N/A";
		}
	}
}
