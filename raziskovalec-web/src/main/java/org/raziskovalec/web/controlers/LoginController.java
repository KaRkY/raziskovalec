package org.raziskovalec.web.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Rene Svetina
 */
@Controller
@RequestMapping("/login")
public class LoginController {

  @RequestMapping(method = RequestMethod.GET)
  public String login() {
    return "authentication";
  }
}
