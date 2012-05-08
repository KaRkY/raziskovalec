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
package org.raziskovalec.web.i18n;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * localization bean for holding current localization data.
 * 
 * @author Rene Svetina
 */
public final class LocalizationBean implements
		Serializable
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private static final int			COOKIE_MAX_AGE		= 31536000;
	private static final String			LOCALE_COOKIE_NAME	= "org.raziskovalec.localization.LOCALE";
	private static final long			serialVersionUID	= 2036176357496933262L;
	private final Map<String, Object>	cookieProperties;
	private Locale						currentLocale;
	private final Logger				logger				= LoggerFactory.getLogger(this.getClass());

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	/**
	 * Default constructor.
	 * 
	 * @param localizationData
	 *            for supported locales.
	 */
	public LocalizationBean(final LocalizationDataBean localizationData)
	{
		logger.debug("Creating localization bean.");

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestCookieMap = externalContext
				.getRequestCookieMap();

		cookieProperties = Maps.newHashMap();
		cookieProperties.put("maxAge", new Integer(COOKIE_MAX_AGE));

		if (!requestCookieMap.containsKey(LOCALE_COOKIE_NAME))
		{
			Iterator<Locale> requestLocales = externalContext.getRequestLocales();
			for (Locale requestLocale = requestLocales.next(); requestLocales.hasNext(); requestLocale = requestLocales
					.next())
			{
				if (localizationData.getSupportedLocales().contains(requestLocale))
				{
					currentLocale = requestLocale;

					break;
				}
			}

			if (currentLocale == null)
			{
				currentLocale = localizationData.getSupportedLocales().get(0);
			}

			externalContext.addResponseCookie(LOCALE_COOKIE_NAME, currentLocale.toString(), cookieProperties);
		}
		else
		{
			Cookie cookie = (Cookie) requestCookieMap.get(LOCALE_COOKIE_NAME);
			currentLocale = Locale.forLanguageTag(cookie.getValue());
		}
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	/**
	 * @return Current locale.
	 */
	public Locale getCurrentLocale()
	{
		return currentLocale;
	}

	/**
	 * Sets current locale.
	 * 
	 * @param currentLocale
	 *            locale to be set.
	 */
	public void setCurrentLocale(final Locale currentLocale)
	{
		logger.trace("Setting locale to: '{}'", currentLocale);
		this.currentLocale = currentLocale;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.addResponseCookie(LOCALE_COOKIE_NAME, currentLocale.toString(), cookieProperties);
	}

}
