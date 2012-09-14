package org.raziskovalec.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ResearcherForm {

  @NotBlank
  @Size(min = 3)
  private String name;

  @NotBlank
  @Size(min = 3)
  private String lastname;

  @NotBlank
  @Email
  private String email;

  private String telephonenumber;

  private String www;

  public String getEmail() {
    return email;
  }

  public String getLastname() {
    return lastname;
  }

  public String getName() {
    return name;
  }

  public String getTelephonenumber() {
    return telephonenumber;
  }

  public String getWww() {
    return www;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setLastname(final String lastname) {
    this.lastname = lastname;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setTelephonenumber(final String telephonenumber) {
    this.telephonenumber = telephonenumber;
  }

  public void setWww(final String www) {
    this.www = www;
  }
}
