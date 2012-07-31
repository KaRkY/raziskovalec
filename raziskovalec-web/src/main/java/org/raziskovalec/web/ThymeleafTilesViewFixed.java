package org.raziskovalec.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.servlet.context.ServletUtil;
import org.springframework.web.servlet.support.JstlUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractTemplateView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.ProcessingContext;
import org.thymeleaf.extras.tiles2.spring.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring.web.view.ThymeleafTilesView;
import org.thymeleaf.spring3.context.SpringWebContext;
import org.thymeleaf.spring3.naming.SpringContextVariableNames;

public class ThymeleafTilesViewFixed extends ThymeleafTilesView {
    
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	final ServletContext servletContext = getServletContext();

        if (getTemplateName() == null) {
            throw new IllegalArgumentException("Property 'templateName' is required");
        }
        if (getLocale() == null) {
            throw new IllegalArgumentException("Property 'locale' is required");
        }
        if (getTemplateEngine() == null) {
            throw new IllegalArgumentException("Property 'templateEngine' is required");
        }
        
        final Map<String, Object> mergedModel = new HashMap<String, Object>();
        
        final Map<String, Object> staticVariables = this.getStaticVariables();
        if (staticVariables != null) {
            mergedModel.putAll(staticVariables);
        }
        if (model != null) {
            mergedModel.putAll(model);
        }

        
        final RequestContext requestContext = 
                new RequestContext(request, response, servletContext, mergedModel);
        
        // For compatibility with ThymeleafView
        addRequestContextAsVariable(mergedModel, SpringContextVariableNames.SPRING_REQUEST_CONTEXT, requestContext);
        // For compatibility with AbstractTemplateView
        addRequestContextAsVariable(mergedModel, AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE, requestContext);

        
        final IWebContext context = 
                new SpringWebContext(request, response, servletContext , getLocale(), mergedModel, getApplicationContext());
        final IProcessingContext processingContext = new ProcessingContext(context);
        
        final TemplateEngine viewTemplateEngine = getTemplateEngine();
        
        final String templateContentType = getContentType();
        final Locale templateLocale = getLocale();
        final String templateCharacterEncoding = getCharacterEncoding();

        response.setLocale(templateLocale);
        if (templateContentType != null) {
            response.setContentType(templateContentType);
        } else {
            response.setContentType(DEFAULT_CONTENT_TYPE);
        }
        if (templateCharacterEncoding != null) {
            response.setCharacterEncoding(templateCharacterEncoding);
        }
        
        TilesContainer container = ServletUtil.getContainer(servletContext);
        if (container == null) {
            throw new ServletException(
                    "Tiles container is not initialized. " +
                    "Have you added a " + ThymeleafTilesConfigurer.class.getSimpleName() + " to " +
                    "your web application context?");
        }

        
        exposeModelAsRequestAttributes(mergedModel, request);
        JstlUtils.exposeLocalizationContext(requestContext);
        
        
        container.render(getTemplateName(), viewTemplateEngine, processingContext, request, response, response.getWriter());
        
    }
}
