package org.raziskovalec.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

public class LocaleAttributIntrceptor extends HandlerInterceptorAdapter {

  @Override
  public void postHandle(final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler,
      final ModelAndView modelAndView) throws Exception {

    modelAndView.addObject("locale", RequestContextUtils.getLocale(request));
  }
}
