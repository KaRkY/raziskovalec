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

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalizationBean implements Serializable
{
	// ========================================================================
	// Fields
	// ========================================================================
	private static final long	serialVersionUID	= 2036176357496933262L;
	private Locale				currentLocale;
	private final Logger		logger				= LoggerFactory.getLogger(this.getClass());

	// ========================================================================
	// Constructors
	// ========================================================================
	public LocalizationBean()
	{
		logger.debug("Creating localization bean.");

		currentLocale = LocaleUtils.toLocale("sl_SI");
	}

	// ========================================================================
	// Methods
	// ========================================================================

	public Locale getCurrentLocale()
	{
		return currentLocale;
	}

	public void setCurrentLocale(final Locale currentLocale)
	{
		logger.trace("Setting locale to: '{}'", currentLocale);
		this.currentLocale = currentLocale;
	}

}
