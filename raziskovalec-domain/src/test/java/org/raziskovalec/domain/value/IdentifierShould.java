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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;

import org.raziskovalec.Identifier;
import org.testng.annotations.Test;

/**
 * Testing Identifier.
 * 
 * @author Rene Svetina
 */
public class IdentifierShould {
	/**
	 * 2 Identifiers with same hash should equal.
	 */
	@Test
	public void equalWithSameString() {
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id1 = Identifier.valueOf(hash);
		Identifier id2 = Identifier.valueOf(hash);

		assertThat(id1, is(equalTo(id2)));
	}

	/**
	 * Creation should fail on invalid hash.
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnIvalidHash() {
		String hash = "12rt";
		Identifier.valueOf(hash);
	}

	/**
	 * Should generate random unique id.
	 */
	@Test
	public void generateNewId() {
		Identifier id = Identifier.newId();

		assertThat(id, is(notNullValue()));
	}

	/**
	 * Should get valid id from string.
	 */
	@Test
	public void getIdFromString() {
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id = Identifier.valueOf(hash);

		assertThat(id, is(notNullValue()));
	}

	/**
	 * Should generate same hashcode.
	 */
	@Test
	public void getSameHashOnSameString() {
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id1 = Identifier.valueOf(hash);
		Identifier id2 = Identifier.valueOf(hash);

		assertThat(id1.hashCode(), is(equalTo(id2.hashCode())));
	}

	/**
	 * Should return same instance.
	 */
	@Test
	public void getSameInstance() {
		Identifier id1 = Identifier.newId();
		Identifier id2 = Identifier.valueOf(id1.toString());

		assertThat(id1, is(sameInstance(id2)));
	}

	/**
	 * Should return string representation.
	 */
	@Test
	public void getStringRepresentationOfId() {
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id = Identifier.valueOf(hash);

		assertThat(id.toString(), is(equalTo(hash)));
	}
}
