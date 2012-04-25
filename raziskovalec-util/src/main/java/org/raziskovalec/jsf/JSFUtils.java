package org.raziskovalec.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class JSFUtils
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	// =================================================================================================================
	// Methods
	// =================================================================================================================
	public static Object getLabel(final FacesContext context,
									final UIComponent component)
	{
		Object o = component.getAttributes().get("label");
		if (o == null || o instanceof String && ((String) o).length() == 0)
		{
			o = component.getValueExpression("label");
		}
		// Use the "clientId" if there was no label specified.
		if (o == null)
		{
			o = component.getClientId(context);
		}
		return o;
	}
}
