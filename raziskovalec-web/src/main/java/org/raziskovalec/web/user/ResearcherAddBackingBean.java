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
package org.raziskovalec.web.user;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.raziskovalec.domain.Researcher;
import org.raziskovalec.domain.value.Name;
import org.raziskovalec.web.jsf.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Researcher backing bean for adding new researchers.
 * 
 * @author Rene Svetina
 * 
 */
public class ResearcherAddBackingBean
{
	// ========================================================================
	// Fields
	// ========================================================================
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ResearcherBean researcherBean;

	// ========================================================================
	// Constructors
	// ========================================================================
	/**
	 * Initialize bean with Researcher data.
	 * 
	 * @param researcherBean
	 *            actual researcher.
	 */
	public ResearcherAddBackingBean(final ResearcherBean researcherBean)
	{
		this.researcherBean = researcherBean;
	}

	// ========================================================================
	// Methods
	// ========================================================================

	/**
	 * Cancel adding.
	 * 
	 * @return redirect path.
	 */
	public String cancel()
	{
		logger.info("Canceling researcher save.");
		return "/researcher/list?faces-redirect=true";
	}

	private boolean validate(final boolean reportMessages)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		boolean ok = true;

		if (StringUtils.isBlank(researcherBean.getName()))
		{
			if (reportMessages)
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions.msg("researcher.error.name.blank"));
				facesContext.addMessage("researcher:name", message);
			}
			ok = false;
		}

		if (StringUtils.isBlank(researcherBean.getLastname()))
		{
			if (reportMessages)
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions.msg("researcher.error.lastname.blank"));
				facesContext.addMessage("researcher:lastname", message);
			}
			ok = false;
		}

		return ok;
	}

	/**
	 * Save researcher.
	 * 
	 * @return redirect path.
	 */
	public String save()
	{
		logger.info("Saveing researcher: '{}'", researcherBean);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try
		{
			boolean ok = validate(true);

			if (ok)
			{
				Researcher researcher = new Researcher();
				researcher.setName(Name.valueOf(researcherBean.getName()));
				researcher.setLastName(Name.valueOf(researcherBean.getLastname()));
				InternetAddress[] internetAddresses = InternetAddress.parse(researcherBean.getEmail());
				researcher.setEmail(internetAddresses[0]);
				logger.trace("Saved researcher: {}", researcher);
			}
			else
			{
				return "";
			}

		} catch (AddressException e)
		{
			logger.debug("Adding researcher fail address format wrong.", e);

			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(Functions.msg("researcher.error.emailformat.summary"));
			message.setDetail(Functions.msg("researcher.error.emailformat.detail", researcherBean.getName()));

			facesContext.addMessage(null, message);

			return "";
		}
		return "/researcher/list?faces-redirect=true";
	}
}
