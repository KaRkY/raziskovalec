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
