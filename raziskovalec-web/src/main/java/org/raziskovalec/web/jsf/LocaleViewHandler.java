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

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocaleViewHandler extends ViewHandlerWrapper
{
	private final Logger		logger	= LoggerFactory.getLogger(this.getClass());
	private final ViewHandler	parent;
	
	public LocaleViewHandler(final ViewHandler parent)
	{
		logger.debug("Creating LocaleViewHandler");
		this.parent = parent;
	}
	
	@Override
	public Locale calculateLocale(final FacesContext context)
	{
		logger.trace("Entering method calculateLocale()");
		Locale currentLocale = LocaleBeanUtils.getLocaleFromLocaleBean();
		
		if (currentLocale == null)
		{
			currentLocale = super.calculateLocale(context);
			
		}
		final UIViewRoot viewRoot = context.getViewRoot();
		if (viewRoot != null)
		{
			viewRoot.setLocale(currentLocale);
		}
		logger.trace("Leaving method calculateLocale(): {}", currentLocale);
		return currentLocale;
	}
	
	@Override
	public ViewHandler getWrapped()
	{
		return parent;
	}
	
}
