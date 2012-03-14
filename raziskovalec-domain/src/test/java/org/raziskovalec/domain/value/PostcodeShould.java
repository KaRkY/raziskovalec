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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class PostcodeShould
{
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnEmptyCode()
	{
		Postcode.valueOf("", "Ožbalt ob dravi");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnEmptyName()
	{
		Postcode.valueOf("2361", "");
	}

	@Test(expectedExceptions = { NullPointerException.class })
	public void failOnNullCode()
	{
		Postcode.valueOf(null, "Ožbalt ob dravi");
	}

	@Test(expectedExceptions = { NullPointerException.class })
	public void failOnNullName()
	{
		Postcode.valueOf("2361", null);
	}

	@Test
	public void returnEqualPostalCode()
	{
		Postcode postcode = Postcode.valueOf("[2361]Ožbalt ob Dravi");
		Postcode postcode1 = Postcode.valueOf("2361", "Ožbalt ob Dravi");

		assertEquals(postcode, postcode1);
	}

	@Test
	public void returnEqualPostalCodeValues()
	{
		Postcode postcode = Postcode.valueOf("[2361]Ožbalt ob Dravi");
		Postcode postcode1 = Postcode.valueOf("2361", "Ožbalt ob Dravi");

		assertEquals(postcode.getCode(), postcode1.getCode(), "Expected equal postal code.");
		assertEquals(postcode.getName(), postcode1.getName(), "Expected equal postal name.");
	}

	@Test
	public void returnPostalCode()
	{
		Postcode postcode = Postcode.valueOf("2361", "Ožbalt ob Dravi");

		assertNotNull(postcode);
	}

	@Test
	public void returnPostalCodeFromCodeString()
	{
		Postcode postcode = Postcode.valueOf("[2361]Ožbalt ob Dravi");

		assertNotNull(postcode);
	}
}
