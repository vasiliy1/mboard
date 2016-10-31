package ua.hypson.mvc.service;

import ua.hypson.mvc.entity.User;

/**
 * Created by admin on 08.06.2016.
 */
public interface LoginService {
  User login(String login, String password);

}
