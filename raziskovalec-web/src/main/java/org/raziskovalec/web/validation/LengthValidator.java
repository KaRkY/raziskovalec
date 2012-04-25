/*
 * Copyright 2011 Rene Svetina.
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
package org.raziskovalec.web.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.raziskovalec.base.ObjectsUtil;
import org.raziskovalec.jsf.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.faces.util.MessageFactory;

/**
 * Length validator.
 * 
 * @author Rene Svetina
 */
public final class LengthValidator implements
		Validator
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private final Logger	logger	= LoggerFactory.getLogger(getClass());

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	/**
	 * Default constructor.
	 */
	public LengthValidator()
	{
		logger.trace("Creating length validator.");
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	private String getAttribute(final String name, final UIComponent component)
	{
		return ObjectsUtil.toStringOrNull(component.getAttributes().get(name));
	}

	/*
	 * (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext ,
	 * javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
	{
		if (!UIInput.isEmpty(value))
		{
			Integer min = Integer.parseInt(getAttribute("min", component));
			Integer max = null;
			String sMax = getAttribute("max", component);
			if (sMax != null)
			{
				max = Integer.parseInt(sMax);
			}
			else
			{
				sMax = Functions.msg("org.raziskovalec.web.validation.unlimited");
			}

			String sValue = value.toString();
			if (sValue.length() < min || max != null && sValue.length() > max)
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions.msg("org.raziskovalec.web.validation.LengthValidator.summary",
						min,
						sMax,
						MessageFactory.getLabel(context, component)));
				message.setDetail(Functions.msg("org.raziskovalec.web.validation.LengthValidator.detail",
						min,
						sMax,
						MessageFactory.getLabel(context, component)));

				throw new ValidatorException(message);
			}
		}
	}
}
