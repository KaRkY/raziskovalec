package org.raziskovalec.web.configuration;

import org.apache.tiles.preparer.ViewPreparer;
import org.raziskovalec.web.TilesAttributeRequestContextPreparer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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
import org.springframework.web.servlet.view.tiles2.SpringBeanPreparerFactory;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring.web.view.ThymeleafTilesView;
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
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
  }

  @Bean
  public ViewPreparer attributePreparer() {
    return new TilesAttributeRequestContextPreparer();
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
    templateEngine.setAdditionalDialects(ImmutableSet.<IDialect> of(new TilesDialect()));

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
  public ThymeleafTilesConfigurer tilesConfigurer() {
    final ThymeleafTilesConfigurer tilesConfigurer = new ThymeleafTilesConfigurer();

    tilesConfigurer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
    tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles-defs.xml", "/WEB-INF/thymeleaf/**/tiles-defs.xml" });

    return tilesConfigurer;
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

    viewResolver.setViewClass(ThymeleafTilesView.class);
    viewResolver.setContentType("text/html; charset=UTF-8");
    viewResolver.setTemplateEngine(templateEngine());

    return viewResolver;
  }
}
