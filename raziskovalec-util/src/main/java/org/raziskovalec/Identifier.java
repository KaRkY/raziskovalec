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
package org.raziskovalec;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Objects;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.util.concurrent.ExecutionError;

/**
 * Class for DB Identity.
 * 
 * @author Rene Svetina
 */
public final class Identifier implements Serializable {
	// =================================================================================================================
	// Fields
	// =================================================================================================================

	private static final long						serialVersionUID	= 6346749479769179997L;
	private static final int						CACHE_EXPIRE_TIME	= 10;
	private static final long						CACHE_SIZE			= 10000L;
	private static final HashFunction				HF					= Hashing.sha1();
	private static final Cache<String, Identifier>	CACHE;
	private static Pattern							hashPattern;
	private final String							hash;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	static {
		CACHE = CacheBuilder.newBuilder().maximumSize(CACHE_SIZE).expireAfterAccess(CACHE_EXPIRE_TIME, TimeUnit.MINUTES).build();
		hashPattern = Pattern.compile("[A-Fa-f0-9]{40}");
	}

	private Identifier() {
		final UUID uuid = UUID.randomUUID();
		final Hasher hasher = HF.newHasher();
		hasher.putString(uuid.toString());
		hash = hasher.hash().toString();
	}

	private Identifier(final String hash) {
		super();
		this.hash = hash;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	/**
	 * @return new random unique id.
	 */
	public static Identifier newId() {
		final Identifier result = new Identifier();
		CACHE.put(result.toString(), result);
		return result;
	}

	/**
	 * @param hash
	 *            String representation of Identifier.
	 * @return Identifier representation of hash string.
	 */
	public static Identifier valueOf(final String hash) {
		// Validates hash pattern.
		final Matcher hashMatcher = hashPattern.matcher(hash);
		checkArgument(hashMatcher.matches(), String.format("%s: does not matches predefined pattern [A-Fa-f0-9]{64}", hash));

		try {
			// Trys to resolve Identity from cache. If Identity is not in cache
			// it creates a new random unique Identity.
			return CACHE.get(hash, new Callable<Identifier>() {

				@Override
				public Identifier call() throws Exception {
					return new Identifier(hash);
				}

			});
		} catch (final ExecutionException e) {
			throw new ExecutionError(new Error("Could not create Id", e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Identifier) {
			final Identifier other = (Identifier) obj;
			return Objects.equal(hash, other.hash);
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(hash);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return hash;
	}
}
