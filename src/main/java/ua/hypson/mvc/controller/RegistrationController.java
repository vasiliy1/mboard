package ua.hypson.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.Captcha;

import ua.hypson.mvc.entity.User;
import ua.hypson.mvc.service.UserService;

@Controller
public class RegistrationController {

  @InitBinder
  public void initBinder(WebDataBinder binder) {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(dateFormat, false));
  }

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView register_GET() {
    ModelAndView model = new ModelAndView("registration");
    model.addObject("user", new User());
    return model;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView register_POST(@Valid @ModelAttribute(value = "user") User user, BindingResult errors,
      HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    if (errors.hasErrors()) {
      model.setViewName("registration");
      return model;
    }
    Captcha captcha = Captcha.load(request, "validationCaptcha");
    boolean isHuman = captcha.validate(request.getParameter("captchaText"));

    if (isHuman) {
      userService.registerRegular(user);
      model.setViewName("registersuccess");
      model.addObject("registeredUser", user);
      return model;
    } else {
      model.setViewName("registration");
      errors.addError(new ObjectError("captchaError", "Please try to enter text one more time"));
      return model;
    }
  }

}
