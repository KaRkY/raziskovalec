package org.raziskovalec.web.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.raziskovalec.base.ObjectsUtil;
import org.raziskovalec.web.jsf.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.faces.util.MessageFactory;

public class LengthValidator implements
		Validator
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public LengthValidator()
	{
		logger.trace("Creating length validator.");
	}

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
			throws ValidatorException
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

	private String getAttribute(final String name, final UIComponent component)
	{
		return ObjectsUtil.toStringOrNull(component.getAttributes().get(name));
	}

}
