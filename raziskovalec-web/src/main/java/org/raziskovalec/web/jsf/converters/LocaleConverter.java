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
package org.raziskovalec.web.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.LocaleUtils;

public class LocaleConverter implements Converter
{
	// ========================================================================
	// Methods
	// ========================================================================
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value)
	{
		return LocaleUtils.toLocale(value);
	}

	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value)
	{
		return value.toString();
	}

}