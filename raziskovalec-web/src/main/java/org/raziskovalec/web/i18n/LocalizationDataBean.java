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
package org.raziskovalec.web.i18n;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.google.common.collect.Lists;

/**
 * Bean for holding all locales.
 * 
 * @author Rene Svetina
 * 
 */
public class LocalizationDataBean
{
	// ========================================================================
	// Fields
	// ========================================================================
	private final List<Locale> supportedLocales = Lists.newArrayList();

	// ========================================================================
	// Constructors
	// ========================================================================

	/**
	 * Derfaul constructor.
	 */
	public LocalizationDataBean()
	{
		this(Locale.getDefault());
	}

	/**
	 * Constructor that initialiyes all supported locales.
	 * 
	 * @param locales
	 *            supported locales.
	 */
	public LocalizationDataBean(final Locale... locales)
	{
		Collections.addAll(supportedLocales, locales);
	}

	// ========================================================================
	// Methods
	// ========================================================================
	/**
	 * List of supported locales.
	 * 
	 * @return supported locales.
	 */
	public List<Locale> getSupportedLocales()
	{
		return supportedLocales;
	}
}
