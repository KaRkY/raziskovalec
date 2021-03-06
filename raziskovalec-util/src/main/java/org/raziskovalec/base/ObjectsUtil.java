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
package org.raziskovalec.base;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author Rene Svetina
 */
public final class ObjectsUtil {
  private ObjectsUtil() {
  }

  public static <E> Set<E> emptyForNull(final Set<E> set) {
    if (set == null) return Sets.newHashSet();
    return set;
  }

  public static boolean isNotNullOrEmpty(final Object o) {
    if (o == null) return false;

    if (o.toString().isEmpty()) return false;

    return true;
  }

  public static String nullForEmpty(final String str) {
    if (str != null && !str.isEmpty()) return str;
    return null;
  }

  public static String toStringOrNull(final Object o) {
    if (o == null) return null;
    else return o.toString();
  }
}
