/**
 * Copyright 2012 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.raziskovalec.domain;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.LocalDate;
import org.raziskovalec.Name;

import com.google.common.base.Objects;

public class Researcher implements Serializable {
  private static final long serialVersionUID = 5018104292542842256L;
  private final Integer     id;
  private Name              name;
  private Name              lastName;
  private String            email;
  private LocalDate         dateOdBirth;
  private String            telephoneNumber;
  private String            website;

  @JsonCreator
  public Researcher(@JsonProperty("id") final Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Researcher) {
      final Researcher other = (Researcher) obj;

      return equal(id, other.id);
    } else return false;
  }

  public LocalDate getDateOdBirth() {
    return dateOdBirth;
  }

  public String getEmail() {
    return email;
  }

  public Integer getId() {
    return id;
  }

  public Name getLastName() {
    return lastName;
  }

  public Name getName() {
    return name;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public String getWebsite() {
    return website;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public void setDateOdBirth(final LocalDate dateOdBirth) {
    this.dateOdBirth = dateOdBirth;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setLastName(final Name lastName) {
    this.lastName = lastName;
  }

  public void setName(final Name name) {
    this.name = name;
  }

  public void setTelephoneNumber(final String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public void setWebsite(final String website) {
    this.website = website;
  }

  @Override
  public String toString() {
    return toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("lastName", lastName)
        .add("email", email)
        .add("dateOfBirth", dateOdBirth)
        .toString();
  }
}
