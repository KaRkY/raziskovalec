package org.raziskovalec.web.controlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rene Svetina
 */
@Controller
public class ErrorController {

  @RequestMapping("/404")
  public String error404() {
    return "error/404";
  }

  @RequestMapping("/500")
  public ModelAndView error500(final HttpServletRequest request) {
    final ModelAndView modelAndView = new ModelAndView("error/500");

    final Object exception = request.getAttribute("javax.servlet.error.exception");
    final Object pathInfo = request.getAttribute("javax.servlet.forward.path_info");

    modelAndView.addObject("exception", exception);
    modelAndView.addObject("pathInfo", pathInfo);

    return modelAndView;
  }
}
