package org.raziskovalec.web.jsf;

import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.raziskovalec.web.i18n.LocalizationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.common.collect.Lists;

public final class LocaleBeanUtils
{
	private static final Logger	logger	= LoggerFactory.getLogger(LocaleBeanUtils.class);
	
	private LocaleBeanUtils()
	{
	}
	
	public static Locale getLocaleFromLocaleBean()
	{
		logger.trace("Entering method getLocaleFromLocaleBean()");
		final FacesContext context = FacesContext.getCurrentInstance();
		final WebApplicationContext webApplicationContext =
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)
						context.getExternalContext()
						.getContext());
		logger.debug("Acquired WebApplicationContext: {}",
				webApplicationContext);
		final List<LocalizationBean> localizationBeans =
				Lists.newArrayList();
		logger.debug("Created localization bean list.");
		try
		{
			localizationBeans.add((LocalizationBean)
					webApplicationContext.getBean("localizationBean"));
			logger.debug("Added single localization bean.");
		}
		catch (final BeansException e)
		{
			localizationBeans.addAll(webApplicationContext.getBeansOfType(LocalizationBean.class,
					true, true).values());
			logger.debug("Added multi localization bean.");
		}
		
		for (final LocalizationBean localizationBean : localizationBeans)
		{
			if (localizationBean != null)
			{
				final Locale currentLocale = localizationBean.getCurrentLocale();
				logger.trace("Leaving method calculateLocale(): {}", currentLocale);
				return currentLocale;
			}
		}
		
		return null;
	}
}
