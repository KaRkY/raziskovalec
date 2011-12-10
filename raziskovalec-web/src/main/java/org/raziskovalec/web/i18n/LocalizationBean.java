package org.raziskovalec.web.i18n;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class LocalizationBean implements Serializable
{
	// ========================================================================
	// Fields
	// ========================================================================
	private static final long		serialVersionUID	= 2036176357496933262L;
	private Locale					currentLocale;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final List<SelectItem>	suportedLocales	= Lists.newArrayList();
	
	// ========================================================================
	// Constructors
	// ========================================================================
	public LocalizationBean()
	{
		logger.debug("Creating localization bean.");
		suportedLocales.add(buildItem("Slovenščina", LocaleUtils.toLocale("sl_SI")));
		suportedLocales.add(buildItem("English", LocaleUtils.toLocale("en_US")));
		
		currentLocale = LocaleUtils.toLocale("sl_SI");
	}
	
	// ========================================================================
	// Methods
	// ========================================================================
	public void changeLocale(final Locale locale)
	{
		final FacesContext context = FacesContext.getCurrentInstance();
		final UIViewRoot viewRoot = context.getViewRoot();
		System.out.println(viewRoot.getClientId());
		
		setCurrentLocale(locale);
	}
	
	public Locale getCurrentLocale()
	{
		return currentLocale;
	}
	
	public List<SelectItem> getSuportedLocales()
	{
		return suportedLocales;
	}
	
	public void setCurrentLocale(final Locale currentLocale)
	{
		logger.debug("Current locale: {}", currentLocale);
		this.currentLocale = currentLocale;
	}
	
	private SelectItem buildItem(final String label, final Locale locale)
	{
		final SelectItem selectItem = new SelectItem();
		selectItem.setLabel(label);
		selectItem.setValue(locale);
		
		return selectItem;
	}
}
