/**
 * Copyright 2011 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.raziskovalec.base;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * @author Rene Svetina
 * 
 */
public final class ObjectsUtil
{
	private ObjectsUtil()
	{
	}

	/**
	 * For null returns empty Set.
	 * 
	 * @param set
	 *            Set for null check.
	 * @param <E>
	 *            Set generic type.
	 * @return For null returns empty Set if not null then returns same Set.
	 */
	public static <E> Set<E> emptyForNull(final Set<E> set)
	{
		if (set == null)
		{
			return Sets.newHashSet();
		}
		return set;
	}

	/**
	 * Returns string representation or null.
	 * 
	 * @param o
	 *            any object
	 * @return string representation
	 */
	public static String toStringOrNull(final Object o)
	{
		if (o == null)
		{
			return null;
		}
		else
		{
			return o.toString();
		}
	}
}
