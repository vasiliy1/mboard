package ua.hypson.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.hypson.mvc.entity.Message;
import ua.hypson.mvc.entity.Role;
import ua.hypson.mvc.entity.User;
import ua.hypson.mvc.service.BoardService;
import ua.hypson.mvc.service.RoleService;
import ua.hypson.mvc.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

  @Autowired
  private RoleService roleService;

  @Autowired
  private UserService userService;

  @Autowired
  private BoardService boardService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(dateFormat, false));
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView create_GET() {
    ModelAndView model = new ModelAndView("admin/create");
    model.addObject("creatingUser", new User());
    return model;
  }

  @RequestMapping(value = "/home")
  public ModelAndView home_GET(ModelAndView model) {
    model.setViewName("admin/home");
    List<User> users = userService.getAllUsers();
    model.addObject("users", users);

    return model;
  }

  @RequestMapping(value = "/msgedit")
  public ModelAndView msgedit_GET(ModelAndView model) {
    model.setViewName("admin/msgedit");

    List<Message> list = boardService.getMessagesOrderByDate();
    model.addObject("messages", list);
    return model;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView create_POST(@Valid @ModelAttribute("creatingUser") User creatingUser, BindingResult errors) {
    ModelAndView model = new ModelAndView();
    if (errors.hasErrors()) {
      model.setViewName("create");
      return model;
    }
    Role creatingUserRole = roleService.getRoleByName(creatingUser.getRole().getName());
    creatingUser.setRole(creatingUserRole);
    userService.save(creatingUser);
    model.setViewName("admin/success");
    model.addObject("successmessage", "User has been created successfully");
    return model;

  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ModelAndView delete_POST(@RequestParam("delete") String login) {
    userService.deleteByLogin(login);
    ModelAndView model = new ModelAndView("admin/success");
    model.addObject("successmessage", "User has been deleted successfully");
    return model;
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public ModelAndView edit_POST(@RequestParam("edit") String login, HttpSession session) {
    ModelAndView model = new ModelAndView("admin/edit");
    User editingUser = userService.findByLogin(login);
    session.setAttribute("editingUserId", editingUser.getId());
    model.addObject("editingUser", editingUser);
    return model;
  }

  @RequestMapping(value = "/processedit", method = RequestMethod.POST)
  public ModelAndView processedit_POST(@Valid @ModelAttribute("editingUser") User editingUser, BindingResult errors,
      HttpSession session) {
    ModelAndView model = new ModelAndView();
    if (errors.hasErrors()) {
      model.setViewName("admin/edit");
      return model;
    }
    Role editingUserRole = roleService.getRoleByName(editingUser.getRole().getName());
    editingUser.setRole(editingUserRole);
    long id = (Long) session.getAttribute("editingUserId");
    editingUser.setId(id);
    userService.update(editingUser);
    model.setViewName("admin/success");
    model.addObject("successmessage", "User has been edited successfully");
    return model;
  }

  @RequestMapping(value = "/refresh", method = RequestMethod.GET)
  public ModelAndView refresh_GET() {
    ModelAndView model = new ModelAndView("admin/home");
    List<User> users = userService.getAllUsers();
    model.addObject("users", users);
    return model;
  }

  @RequestMapping(value = "/msgedit/delete", method = RequestMethod.POST)
  public ModelAndView msgedit_delete_POST(@RequestParam("delete") Long id) {
//    boardService.deleteById(id);
    Message m = boardService.findById(id);
    boardService.delete(m);

    ModelAndView model = new ModelAndView("admin/msgedit");
    List<Message> list = boardService.getMessagesOrderByDate();
    model.addObject("messages", list);
    return model;
  }

}
