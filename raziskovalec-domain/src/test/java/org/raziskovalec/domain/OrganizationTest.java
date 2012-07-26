package org.raziskovalec.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.raziskovalec.Name;
import org.testng.annotations.Test;

public class OrganizationTest {

	@Test
	public void createOrganizationWithNameAndEmail() throws AddressException {
		Organization organization = new Organization(Name.valueOf("FERI"), InternetAddress.parse("feri@uni-mb.si", true)[0]);

		assertThat("Organization:", organization, is(notNullValue()));

		assertThat("Organization name:", organization.name(), is(equalTo(Name.valueOf("FERI"))));

		assertThat("Organization email:", organization.email(), is(equalTo(InternetAddress.parse("feri@uni-mb.si")[0])));
	}
}
