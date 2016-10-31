package ua.hypson.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.hypson.mvc.dto.MessageDTO;
import ua.hypson.mvc.entity.Message;
import ua.hypson.mvc.entity.User;
import ua.hypson.mvc.service.BoardService;
import ua.hypson.mvc.service.UserService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @Autowired
  private UserService userService;

  @Autowired
  @Qualifier("sessionRegistry")
  private SessionRegistry sessionRegistry;

  /**
   *
   * @return List of online user login's
   */
  private List<String> getOnlineUsers() {
    List<Object> principals = sessionRegistry.getAllPrincipals();
    List<String> usersOnlineNames = new ArrayList<>();
    for (final Object principal : principals) {
      if (principal instanceof User) {
        final User user = (User) principal;

        List<SessionInformation> activeUserSessions = sessionRegistry.getAllSessions(principal, false);

        if (!activeUserSessions.isEmpty()) {
          usersOnlineNames.add(user.getLogin());
        }
      }
    }
    return usersOnlineNames;
  }

  /**
   *
   * @return Map of all users(as key) with online/offline mark(as value)
   */
  private Map<User, String> getUsersMapWithOnline(List<String> usersOnlineNames) {
    List<User> allUsers = userService.getAllUsers();
    Map<User, String> map = new HashMap<User, String>();
    for (User user : allUsers) {
      if (usersOnlineNames.contains(user.getLogin())) {
        map.put(user, "online");
      } else {
        map.put(user, "offline");
      }
    }
    return map;
  }

  /**
   * Shows all new private messages for logged user.
   *
   */
  @RequestMapping(value = "/pmto", method = RequestMethod.GET)
  public ModelAndView pmto_GET(@RequestParam("show") String show) {
    ModelAndView mav = new ModelAndView("showpm");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User loggedUser = (User) auth.getPrincipal();
    if (show.equals("new")) {
      List<Message> messages = boardService.getNewMessages(loggedUser);
      mav.addObject("messages", messages);
      boardService.markAsViewed(messages);
    }

    return mav;
  }

  /**
   * Shows page with private messaging to particular user
   */
  @RequestMapping(value = "/pmto/{receiverLogin}", method = RequestMethod.GET)
  public ModelAndView pmtoLogin_GET(@PathVariable String receiverLogin) {

    MessageDTO messageDTO = new MessageDTO();

    User receiver = userService.findByLogin(receiverLogin);

    messageDTO.setReceiver(receiverLogin);
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User author = (User) auth.getPrincipal();
    messageDTO.setAuthor(author.getLogin());
    ModelAndView mav = new ModelAndView("pm", "message", messageDTO);
    List<Message> messages = boardService.findAllPrivateMessagesOrderByDate(author, receiver);
    boardService.markAsViewed(receiver.getId());
    mav.addObject("messages", messages);
    boardService.markAsViewed(messages);
    mav.addObject("newmessages", !boardService.getNewMessages(author).isEmpty());

    return mav;
  }

  /**
   * Posts new private message and refresh page with private messaging
   */
  @RequestMapping(value = "/pmto/{receiverLogin}", method = RequestMethod.POST)
  public ModelAndView pmtoLogin_POST(@Valid @ModelAttribute(value = "message") MessageDTO messageDto,
      BindingResult errors, HttpServletRequest request) {
    if (errors.hasErrors()) {
      List<String> errorsText = convertBindingErrorsToListOfStrings(errors);

      return new ModelAndView("messageerror", "errors", errorsText);
    }
    Message message = new Message();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User author = (User) auth.getPrincipal();
    message.setAuthor(author);
    message.setBody(messageDto.getBody());
    User receiver = userService.findByLogin(messageDto.getReceiver());
    message.setReceiver(receiver);
    message.setDate(new Date());
    message.setViewed(false);
    boardService.addMessage(message);
    ModelAndView mav = new ModelAndView("pm", "messages",
        boardService.findAllPrivateMessagesOrderByDate(message.getAuthor(), message.getReceiver()));
    mav.addObject("newmessages", !boardService.getNewMessages(author).isEmpty());

    return mav;
  }

  /**
   * Shows Message Board
   */
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView board_GET() {
    ModelAndView mav = new ModelAndView("board", "message", new Message());
    List<Message> messagesOrderByDate = boardService.getMessagesOrderByDate();
    mav.addObject("messages", messagesOrderByDate);

    mav.addObject("usersmap", getUsersMapWithOnline(getOnlineUsers()));

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User author = (User) auth.getPrincipal();

    mav.addObject("newmessages", !boardService.getNewMessages(author).isEmpty());
    return mav;
  }

  /**
   * Post new message and shows Message Board
   */
  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView board_POST(@Valid @ModelAttribute(value = "message") Message message, BindingResult errors,
      HttpServletRequest request) {
    if (errors.hasErrors()) {
      List<String> errorsText = convertBindingErrorsToListOfStrings(errors);

      return new ModelAndView("messageerror", "errors", errorsText);
    }
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User author = (User) auth.getPrincipal();
    message.setAuthor(author);
    message.setDate(new Date());

    boardService.addMessage(message);

    List<Message> messages = boardService.getMessagesOrderByDate();
    ModelAndView mav = new ModelAndView("board", "messages", messages);
    mav.addObject("newmessages", !boardService.getNewMessages(author).isEmpty());
    mav.addObject("usersmap", getUsersMapWithOnline(getOnlineUsers()));
    return mav;
  }

  private List<String> convertBindingErrorsToListOfStrings(BindingResult errors) {
    List<String> errorsText = new ArrayList<>();
    for (ObjectError error : errors.getAllErrors()) {
      errorsText.add(error.getDefaultMessage());
    }
    return errorsText;
  }

}
