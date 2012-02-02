package org.raziskovalec.base;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class IdShould
{
	@Test
	public void equalWithSameString()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Id id1 = Id.valueOf(hash);
		Id id2 = Id.valueOf(hash);

		assertEquals(id1, id2);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void failOnIvalidHash()
	{
		String hash = "12rt";
		Id.valueOf(hash);
	}

	@Test
	public void generateNewId()
	{
		Id id = Id.newId();

		assertNotNull(id);
	}

	@Test
	public void getIdFromString()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Id id = Id.valueOf(hash);

		assertNotNull(id);
	}

	@Test
	public void getSameHashOnSameString()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Id id1 = Id.valueOf(hash);
		Id id2 = Id.valueOf(hash);

		assertEquals(id1.hashCode(), id2.hashCode());
	}

	@Test
	public void getSameInstance()

	{
		Id id1 = Id.newId();
		Id id2 = Id.valueOf(id1.toString());

		assertTrue(id1 == id2);
	}

	@Test
	public void getStringRepresentationOfId()

	{
		String hash = "5678901234567890123456789012345678abcdef";
		Id id = Id.valueOf(hash);

		assertEquals(id.toString(), hash);
	}
}
