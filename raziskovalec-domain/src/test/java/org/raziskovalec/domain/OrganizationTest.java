package org.raziskovalec.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.raziskovalec.Name;
import org.testng.annotations.Test;

public class OrganizationTest {

  @Test
  public void createOrganizationWithNameAndEmail() {
    final Organization organization = new Organization(Name.valueOf("FERI"), "feri@uni-mb.si");

    assertThat("Organization:", organization, is(notNullValue()));

    assertThat("Organization name:", organization.name(), is(equalTo(Name.valueOf("FERI"))));

    assertThat("Organization email:", organization.email(), is(equalTo("feri@uni-mb.si")));
  }
}
