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
package org.raziskovalec.base;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class Identifier
{
	// ========================================================================
	// Fields
	// ========================================================================
	private static final Cache<String, Identifier>	cache;
	private static Pattern					hashPattern;
	private final String					hash;

	// ========================================================================
	// Constructors
	// ========================================================================
	static
	{
		cache = CacheBuilder.newBuilder()
				.maximumSize(10000L)
				.expireAfterAccess(10, TimeUnit.MINUTES)
				.build();
		hashPattern = Pattern.compile("[A-Fa-f0-9]{40}");
	}

	private Identifier()
	{
		UUID uuid = UUID.randomUUID();
		hash = DigestUtils.shaHex(uuid.toString());
	}

	private Identifier(final String hash)
	{
		super();
		this.hash = hash;
	}

	// ========================================================================
	// Methods
	// ========================================================================

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
		final Identifier o = (Identifier) obj;
		EqualsBuilder builder = new EqualsBuilder();

		builder.append(hash, o.hash);

		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder(17, 31);

		builder.append(hash);

		return builder.build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return hash;
	}

	public static Identifier newId()
	{
		Identifier result = new Identifier();
		cache.put(result.toString(), result);
		return result;
	}

	public static Identifier valueOf(final String hash)
	{
		Matcher hashMatcher = hashPattern.matcher(hash);
		if (!hashMatcher.matches())
			throw new IllegalArgumentException(String.format("%s: does not matches predefined pattern [A-Fa-f0-9]{64}",
					hash));

		try
		{
			return cache.get(hash, new Callable<Identifier>()
			{

				@Override
				public Identifier call() throws Exception
				{
					return new Identifier(hash);
				}

			});
		}
		catch (ExecutionException e)
		{
			throw new Error("Could not create Id", e);
		}
	}
}
