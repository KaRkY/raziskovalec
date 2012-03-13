package org.raziskovalec.web.validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LengthValidator implements
		Validator
{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Integer min;
	private Integer max;

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

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
			throws ValidatorException
	{
		logger.trace("lalalal");
		logger.trace("min: " + min + " max: " + max);
	}

}
