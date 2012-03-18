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

import org.raziskovalec.web.jsf.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.faces.util.MessageFactory;

/**
 * Validates if field is empty.
 * 
 * @author Rene Svetina
 */
public final class RequiredValidator implements
									Validator
{
	// ========================================================================
	// Fields
	// ========================================================================
	private final Logger	logger	= LoggerFactory.getLogger(getClass());

	// ========================================================================
	// Constructors
	// ========================================================================

	/**
	 * Default constructor.
	 */
	public RequiredValidator()
	{
		logger.trace("Creating email validator.");
	}

	// ========================================================================
	// Methods
	// ========================================================================
	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
	{
		if (UIInput.isEmpty(value))
		{
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(Functions
					.msg("org.raziskovalec.web.validation.RequiredValidator.summary",
							MessageFactory.getLabel(context, component)));
			message.setDetail(Functions
					.msg("org.raziskovalec.web.validation.RequiredValidator.detail",
							MessageFactory.getLabel(context, component)));

			throw new ValidatorException(message);
		}
	}

}
