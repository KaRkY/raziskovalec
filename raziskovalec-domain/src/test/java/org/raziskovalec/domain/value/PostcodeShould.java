package org.raziskovalec.domain.value;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class PostcodeShould
{
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
}
