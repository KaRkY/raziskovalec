package org.raziskovalec.web.form;

public class OrganizationForm {
  private String  name;
  private String  email;
  private String  telephoneNumber;
  private String  website;
  private Integer registryNumber;

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public Integer getRegistryNumber() {
    return registryNumber;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public String getWebsite() {
    return website;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setRegistryNumber(final Integer registryNumber) {
    this.registryNumber = registryNumber;
  }

  public void setTelephoneNumber(final String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public void setWebsite(final String website) {
    this.website = website;
  }

}
