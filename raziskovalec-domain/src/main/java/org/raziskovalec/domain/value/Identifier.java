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

import java.io.Serializable;
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
import com.google.common.util.concurrent.ExecutionError;

/**
 * Class for DB Identity.
 * 
 * @author Rene Svetina
 */
public final class Identifier implements
								Serializable
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================

	private static final long						serialVersionUID	= 6346749479769179997L;
	private static final int						HASH_MULTIPLIER		= 31;
	private static final int						HASH_PRIME			= 17;
	private static final int						CACHE_EXPIRE_TIME	= 10;
	private static final long						CACHE_SIZE			= 10000L;
	private static final Cache<String, Identifier>	CACHE;
	private static Pattern							hashPattern;
	private final String							hash;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	static
	{
		CACHE = CacheBuilder.newBuilder()
				.maximumSize(CACHE_SIZE)
				.expireAfterAccess(CACHE_EXPIRE_TIME, TimeUnit.MINUTES)
				.build();
		hashPattern = Pattern.compile("[A-Fa-f0-9]{40}");
	}

	private Identifier()
	{
		final UUID uuid = UUID.randomUUID();
		hash = DigestUtils.shaHex(uuid.toString());
	}

	private Identifier(final String hash)
	{
		super();
		this.hash = hash;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

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
		final Identifier that = (Identifier) obj;
		final EqualsBuilder builder = new EqualsBuilder();

		builder.append(hash, that.hash);

		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final HashCodeBuilder builder = new HashCodeBuilder(HASH_PRIME, HASH_MULTIPLIER);

		builder.append(hash);

		return builder.build();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return hash;
	}

	/**
	 * @return new random unique id.
	 */
	public static Identifier newId()
	{
		final Identifier result = new Identifier();
		CACHE.put(result.toString(), result);
		return result;
	}

	/**
	 * @param hash
	 *            String representation of Identifier.
	 * @return Identifier representation of hash string.
	 */
	public static Identifier valueOf(final String hash)
	{
		final Matcher hashMatcher = hashPattern.matcher(hash);
		checkArgument(hashMatcher.matches(), String.format("%s: does not matches predefined pattern [A-Fa-f0-9]{64}",
				hash));

		try
		{
			return CACHE.get(hash, new Callable<Identifier>()
			{

				@Override
				public Identifier call() throws Exception
				{
					return new Identifier(hash);
				}

			});
		} catch (ExecutionException e)
		{
			throw new ExecutionError(new Error("Could not create Id", e));
		}
	}
}
