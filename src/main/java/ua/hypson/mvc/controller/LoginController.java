package ua.hypson.mvc.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.hypson.mvc.entity.User;
import ua.hypson.mvc.service.LoginService;
import ua.hypson.mvc.service.UserService;

@Controller
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  LoginService loginService;

  @Autowired
  UserService userService;

  public LoginController() {
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login_GET(@RequestParam(required = false, value = "error") String error, Model model) {
    ModelAndView mav = new ModelAndView("login");
    if(error != null) {
      mav.addObject("error", "Bad credentials");
    }
    return mav;
  }

  @RequestMapping(value = "/denied", method = RequestMethod.GET)
  public String denied() {
    return "denied";
  }

  @RequestMapping(value = "/")
  public String redirectLogin() {
    return "redirect:login";
  }

  @RequestMapping(value = "/home")
  public String showHomePage(Principal principal) {
    User user = userService.findByLogin(principal.getName());
    if (user.getRole().getName().equals("admin")) {
      return "redirect:admin/home";
    }
    return "home";
  }

}
