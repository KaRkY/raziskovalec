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
package org.raziskovalec;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Postcode for address.
 * 
 * @author Rene Svetina
 */
public final class Postcode implements Serializable {
  private static final long serialVersionUID = 8996124494460276539L;
  private final String      code;
  private final String      name;
  private static Pattern    postalPattern;

  static {
    Postcode.postalPattern = Pattern.compile("^\\[([A-Za-z0-9]+)\\](.*)$");
  }

  private Postcode(final String code, final String name) {
    Preconditions.checkNotNull(code, "Code should not be null.");
    Preconditions.checkNotNull(name, "name should not be null.");
    Preconditions.checkArgument(!code.isEmpty(), "Code should not be empty.");
    Preconditions.checkArgument(!name.isEmpty(), "Name should not be empty.");

    this.code = code;
    this.name = name;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Postcode) {
      final Postcode other = (Postcode) obj;

      return Objects.equal(code, other.code);
    }
    else return false;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(code);
  }

  @Override
  public String toString() {
    return String.format("[%s]%s", code, name);
  }

  public static Postcode valueOf(final String postalCode) {
    final Matcher postalMatcher = Postcode.postalPattern.matcher(postalCode);
    Preconditions.checkArgument(postalMatcher.matches(), "Wrong postalCode format, format should be [<code>]<name>");

    return Postcode.valueOf(postalMatcher.group(1), postalMatcher.group(2));
  }

  public static Postcode valueOf(final String code, final String name) {
    return new Postcode(code, name);
  }
}
