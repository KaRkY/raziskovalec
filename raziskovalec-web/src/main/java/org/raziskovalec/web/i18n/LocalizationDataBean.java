/**
 * Copyright 2011 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.raziskovalec.web.i18n;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.LocaleUtils;

import com.google.common.collect.Maps;

public class LocalizationDataBean
{
	// ========================================================================
	// Fields
	// ========================================================================
	private final Map<String, Locale>	supportedLocales	= Maps.newHashMap();
	
	// ========================================================================
	// Constructors
	// ========================================================================
	public LocalizationDataBean()
	{
		supportedLocales.put("Slovenščina", LocaleUtils.toLocale("sl_SI"));
		supportedLocales.put("English", LocaleUtils.toLocale("en_US"));
	}
	
	// ========================================================================
	// Methods
	// ========================================================================
	public Map<String, Locale> getSupportedLocales()
	{
		return supportedLocales;
	}
}
