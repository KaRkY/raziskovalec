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
package org.raziskovalec.web.jsf;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

public class MessageSourceSpringBeanFacesELResolver extends SpringBeanFacesELResolver
{
	private final Logger	logger	= LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object getValue(final ELContext elContext, final Object base,
			final Object property) throws ELException
			{
		logger.trace("Entering method getValue(base: {}, property: {})", base,
				property);
		if (base instanceof MessageSource && property instanceof String)
		{
			final String result = ((MessageSource) base).getMessage(
					(String) property, null, getLocale());
			
			logger.debug("  Result for " + property + ": " + result);
			
			if (null != result)
			{
				elContext.setPropertyResolved(true);
			}
			
			return result;
		}
		
		return super.getValue(elContext, base, property);
			}
	
	private Locale getLocale()
	{
		Locale locale = LocaleBeanUtils.getLocaleFromLocaleBean();
		if (locale == null)
		{
			locale = Locale.getDefault();
		}
		return locale;
	}
	
}
