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

public class NameShould
{
	@Test
	public void equalWithSameString()
	{
		String name = "Rene";
		Name name1 = Name.valueOf(name);
		Name name2 = Name.valueOf("Rene");

		assertEquals(name1, name2);
	}

	@Test(expectedExceptions = { NullPointerException.class })
	public void failOnNullName()
	{
		Name.valueOf(null);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnEmptyName()
	{
		Name.valueOf("");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnNAName()
	{
		Name.valueOf("N/A");
	}

	@Test
	protected void returnName()
	{
		Name name = Name.valueOf("Rene");

		assertNotNull(name);
	}
}
