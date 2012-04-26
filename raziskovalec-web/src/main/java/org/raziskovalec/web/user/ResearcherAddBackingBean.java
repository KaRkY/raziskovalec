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
package org.raziskovalec.web.user;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Researcher backing bean for adding new researchers.
 * 
 * @author Rene Svetina
 */
public final class ResearcherAddBackingBean
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private final Logger			logger	= LoggerFactory.getLogger(this.getClass());
	private final ResearcherBean	researcherBean;
	private final Session			session;
	private final Configuration		freemarkerConfiguration;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	/**
	 * Initialize bean with Researcher data.
	 * 
	 * @param researcherBean
	 *            actual researcher.
	 */
	public ResearcherAddBackingBean(final ResearcherBean researcherBean,
			final Session session,
			final Configuration freemarkerConfiguration)
	{
		this.researcherBean = researcherBean;
		this.session = session;
		this.freemarkerConfiguration = freemarkerConfiguration;
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

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

	/**
	 * Save researcher.
	 * 
	 * @return redirect path.
	 */
	public String save()
	{
		logger.info("Saveing researcher: '{}'", researcherBean);

		try
		{
			Template emailTemplate = freemarkerConfiguration
					.getTemplate("org/raziskovalec/messages/emails/informationTemplate.ftl",
							FacesContext.getCurrentInstance().getViewRoot().getLocale());
			Map<String, Object> freemarkerContext = Maps.newHashMap();
			freemarkerContext.put("researcher", researcherBean);

			StringWriter out = new StringWriter();
			emailTemplate.process(freemarkerContext, out);

			Message message = new MimeMessage(session);

			MimeMultipart multipart = new MimeMultipart();

			BodyPart body = new MimeBodyPart();
			body.setContent(out.toString(), "text/html");

			multipart.addBodyPart(body);

			message.setFrom(new InternetAddress("Rene Svetina<rene.svetina@gmail.com>"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("rene.svetina@gmail.com"));
			message.setSubject("Raziskovalec mail");
			message.setContent(multipart);

			Transport.send(message);

		} catch (IOException | MessagingException | TemplateException e)
		{
			throw new RuntimeException(e);
		}

		// return "/researcher/list?faces-redirect=true";
		return "";
	}
}
