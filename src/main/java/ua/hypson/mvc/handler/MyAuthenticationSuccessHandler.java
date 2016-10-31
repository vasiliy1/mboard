package ua.hypson.mvc.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ua.hypson.mvc.listener.LoggedUserListener;
import ua.hypson.mvc.service.ActiveUserStore;

@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  ActiveUserStore activeUserStore;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      LoggedUserListener user = new LoggedUserListener(authentication.getName(), activeUserStore);
      session.setAttribute("user", user);
    }
  }
}
