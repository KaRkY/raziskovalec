package org.raziskovalec.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class ResearcherForm {

  @NotBlank
  @Size(min = 3)
  private String    name;
  @NotBlank
  @Size(min = 3)
  private String    lastName;
  @NotBlank
  @Email
  private String    email;
  private String    telephoneNumber;
  private String    website;
  @DateTimeFormat(style = "S-")
  private LocalDate dateOfBirth;

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public String getLastName() {
    return lastName;
  }

  public String getName() {
    return name;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public String getWebsite() {
    return website;
  }

  public void setDateOfBirth(final LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setTelephoneNumber(final String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public void setWebsite(final String website) {
    this.website = website;
  }
}
