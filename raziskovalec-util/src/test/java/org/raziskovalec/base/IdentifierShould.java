package org.raziskovalec.base;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class IdentifierShould
{
	@Test
	public void equalWithSameString()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id1 = Identifier.valueOf(hash);
		Identifier id2 = Identifier.valueOf(hash);

		assertEquals(id1, id2);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnIvalidHash()
	{
		String hash = "12rt";
		Identifier.valueOf(hash);
	}

	@Test
	public void generateNewId()
	{
		Identifier id = Identifier.newId();

		assertNotNull(id);
	}

	@Test
	public void getIdFromString()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id = Identifier.valueOf(hash);

		assertNotNull(id);
	}

	@Test
	public void getSameHashOnSameString()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id1 = Identifier.valueOf(hash);
		Identifier id2 = Identifier.valueOf(hash);

		assertEquals(id1.hashCode(), id2.hashCode());
	}

	@Test
	public void getSameInstance()

	{
		Identifier id1 = Identifier.newId();
		Identifier id2 = Identifier.valueOf(id1.toString());

		assertTrue(id1 == id2);
	}

	@Test
	public void getStringRepresentationOfId()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Identifier id = Identifier.valueOf(hash);

		assertEquals(id.toString(), hash);
	}
}
