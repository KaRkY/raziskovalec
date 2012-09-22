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

import java.io.Serializable;

import javax.mail.internet.InternetAddress;

import org.raziskovalec.Identifier;
import org.raziskovalec.Name;

import com.google.common.base.Objects;

public class Researcher implements Serializable {
  private static final long     serialVersionUID = 5018104292542842256L;
  private final Identifier      id;
  private final Name            name;
  private final Name            lastName;
  private final InternetAddress email;

  public Researcher(final Identifier id, final Name name, final Name lastName, final InternetAddress email) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.email = email;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Researcher) {
      final Researcher other = (Researcher) obj;

      return Objects.equal(id, other.id);
    }
    else return false;
  }

  public InternetAddress getEmail() {
    return email;
  }

  public Identifier getId() {
    return id;
  }

  public Name getLastName() {
    return lastName;
  }

  public Name getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("id", id).add("name", name).add("lastName", lastName).add("email", email)
        .toString();
  }
}
