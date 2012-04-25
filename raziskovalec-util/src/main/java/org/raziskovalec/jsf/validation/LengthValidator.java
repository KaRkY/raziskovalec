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
package org.raziskovalec.jsf.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.raziskovalec.jsf.Functions;
import org.raziskovalec.jsf.JSFUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private Integer			min;
	private Integer			max;

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
			String sValue = value.toString();
			if (sValue.length() < getMin() || getMax() != null && sValue.length() > getMax())
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions.msg("org.raziskovalec.web.validation.LengthValidator.summary",
						getMin(),
						getMax() != null ? getMax() : Functions.msg("org.raziskovalec.web.validation.unlimited"),
						JSFUtils.getLabel(context, component)));
				message.setDetail(Functions.msg("org.raziskovalec.web.validation.LengthValidator.detail",
						getMax(),
						getMax() != null ? getMax() : Functions.msg("org.raziskovalec.web.validation.unlimited"),
						JSFUtils.getLabel(context, component)));

				throw new ValidatorException(message);
			}
		}
	}

	/**
	 * @return the min
	 */
	public Integer getMin()
	{
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(final Integer min)
	{
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public Integer getMax()
	{
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(final Integer max)
	{
		this.max = max;
	}
}
