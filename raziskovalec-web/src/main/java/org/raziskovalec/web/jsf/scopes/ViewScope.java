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
package org.raziskovalec.web.jsf.scopes;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

public class ViewScope implements Scope
{
	// ========================================================================
	// Fields
	// ========================================================================
	public static final String	VIEW_SCOPE_CALLBACKS	= "viewScope.callbacks";

	// ========================================================================
	// Methods
	// ========================================================================

	@Override
	public synchronized Object get(final String name, final ObjectFactory<?> objectFactory)
	{
		Object instance = getViewMap().get(name);
		if (instance == null)
		{
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		return instance;
	}

	@Override
	public String getConversationId()
	{
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
	}

	@Override
	public void registerDestructionCallback(final String name, final Runnable runnable)
	{
		@SuppressWarnings("unchecked")
		final Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		if (callbacks != null)
			callbacks.put(name, runnable);
	}

	@Override
	public Object remove(final String name)
	{
		final Object instance = getViewMap().remove(name);
		if (instance != null)
		{
			@SuppressWarnings("unchecked")
			final Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			if (callbacks != null)
				callbacks.remove(name);
		}
		return instance;
	}

	@Override
	public Object resolveContextualObject(final String name)
	{
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.resolveReference(name);
	}

	private Map<String, Object> getViewMap()
	{
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}
}
