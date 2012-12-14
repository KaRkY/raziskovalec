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

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkArgument;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

/**
 * Value class for handling personal names.
 * 
 * @author Rene Svetina
 */
public final class Name implements Serializable {
  private static final long      serialVersionUID = -7175433342123178074L;
  private final Optional<String> name;

  private Name() {
    name = Optional.absent();
  }

  private Name(final String name) {
    this.name = Optional.fromNullable(name);
    checkArgument(!this.name.isPresent() || !this.name.get().isEmpty(), "Name can not be empty.");
    checkArgument(!this.name.isPresent() || !this.name.get().equals("N/A"), "Name can not be N/A");
  }

  @JsonCreator
  public static Name valueOf(final String name) {
    return new Name(name);
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Name) {
      final Name other = (Name) obj;
      return equal(name, other.name);
    } else return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }

  @Override
  @JsonValue
  public String toString() {
    return name.or("N/A");
  }
}
