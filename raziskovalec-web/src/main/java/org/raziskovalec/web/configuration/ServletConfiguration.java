package org.raziskovalec.web.configuration;

import nz.net.ultraq.web.thymeleaf.LayoutDialect;

import org.raziskovalec.web.interceptors.UserInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.google.common.collect.ImmutableSet;

@Configuration
@EnableWebMvc
@ComponentScan({ "org.raziskovalec.web" })
public class ServletConfiguration extends WebMvcConfigurerAdapter {
  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(new LocaleChangeInterceptor());
    registry.addInterceptor(userInterceptor());
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    registry.addResourceHandler("/bootstrap/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/2.3.0/");
    registry.addResourceHandler("/modernizr/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/modernizr/2.6.2/");
    registry.addResourceHandler("/jquery/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/1.9.0/");
    registry.addResourceHandler("/font-awesome/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/font-awesome/3.0.0/");
  }

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
  }

  @Override
  public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public Validator getValidator() {
    return validator();
  }

  @Bean
  public IDialect layoutDialect() {
    return new LayoutDialect();
  }

  @Bean
  public LocaleResolver localeResolver() {
    final SessionLocaleResolver localeResolver = new SessionLocaleResolver();

    return localeResolver;
  }

  @Bean
  public MessageSource messageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    messageSource.setBasenames("org.raziskovalec.messages.home.messages", "org.raziskovalec.messages.error.messages",
        "org.raziskovalec.messages.menu", "org.raziskovalec.messages.search.messages",
        "org.raziskovalec.messages.researcher.messages", "org.raziskovalec.messages.validation.messages",
        "org.raziskovalec.messages.login.messages");

    return messageSource;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

    templateEngine.setMessageSource(messageSource());
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.setAdditionalDialects(ImmutableSet.<IDialect> of(layoutDialect()));

    return templateEngine;
  }

  @Bean
  public ServletContextTemplateResolver templateResolver() {
    final ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();

    templateResolver.setPrefix("/WEB-INF/thymeleaf/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    templateResolver.setOrder(2);
    templateResolver.setCacheable(false);
    templateResolver.setCharacterEncoding("UTF-8");

    return templateResolver;
  }

  @Bean
  public HandlerInterceptor userInterceptor() {
    return new UserInterceptor(messageSource(), localeResolver());
  }

  @Bean
  public Validator validator() {
    final LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();

    validatorFactoryBean.setValidationMessageSource(messageSource());

    return validatorFactoryBean;
  }

  @Bean
  public ViewResolver viewResolver() {
    final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

    viewResolver.setContentType("text/html; charset=UTF-8");
    viewResolver.setTemplateEngine(templateEngine());

    return viewResolver;
  }
}
