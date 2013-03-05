package org.raziskovalec.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

public class UserInterceptor extends HandlerInterceptorAdapter {

  private final MessageSource messageSource;

  public UserInterceptor(final MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public void postHandle(final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler,
      final ModelAndView modelAndView) throws Exception {
    modelAndView.addObject("loggedIn", true);
    modelAndView.addObject("userName",
        messageSource.getMessage("user.guest", new Object[] {}, RequestContextUtils.getLocale(request)));
  }
}
