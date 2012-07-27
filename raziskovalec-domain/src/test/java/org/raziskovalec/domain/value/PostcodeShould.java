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

import org.raziskovalec.Postcode;
import org.testng.annotations.Test;

/**
 * Postcode tests.
 * 
 * @author Rene Svetina
 */
public class PostcodeShould {
	/**
	 * Fail on empty code.
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnEmptyCode() {
		Postcode.valueOf("", "Ožbalt ob dravi");
	}

	/**
	 * Fail on empty name.
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnEmptyName() {
		Postcode.valueOf("2361", "");
	}

	/**
	 * Fail on null code.
	 */
	@Test(expectedExceptions = { NullPointerException.class })
	public void failOnNullCode() {
		Postcode.valueOf(null, "Ožbalt ob dravi");
	}

	/**
	 * Fail on null name.
	 */
	@Test(expectedExceptions = { NullPointerException.class })
	public void failOnNullName() {
		Postcode.valueOf("2361", null);
	}

	/**
	 * Success on equal postal name.
	 */
	@Test
	public void returnEqualPostalCode() {
		Postcode postcode = Postcode.valueOf("[2361]Ožbalt ob Dravi");
		Postcode postcode1 = Postcode.valueOf("2361", "Ožbalt ob Dravi");

		assertThat(postcode, is(equalTo(postcode1)));
	}

	/**
	 * Success on equal postal code.
	 */
	@Test
	public void returnEqualPostalCodeValues() {
		Postcode postcode = Postcode.valueOf("[2361]Ožbalt ob Dravi");
		Postcode postcode1 = Postcode.valueOf("2361", "Ožbalt ob Dravi");

		assertThat("Expected equal postal code.", postcode.getCode(), is(equalTo(postcode1.getCode())));
		assertThat("Expected equal postal name.", postcode.getName(), is(equalTo(postcode1.getName())));
	}

	/**
	 * Return non null postal code.
	 */
	@Test
	public void returnPostalCode() {
		Postcode postcode = Postcode.valueOf("2361", "Ožbalt ob Dravi");

		assertThat(postcode, is(notNullValue()));
	}

	/**
	 * Return postal code on post string.
	 */
	@Test
	public void returnPostalCodeFromCodeString() {
		Postcode postcode = Postcode.valueOf("[2361]Ožbalt ob Dravi");

		assertThat(postcode, is(notNullValue()));
	}
}
