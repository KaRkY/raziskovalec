package org.raziskovalec.web.form;

public class ResearcherForm {
  private String name;
  private String lastname;
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
