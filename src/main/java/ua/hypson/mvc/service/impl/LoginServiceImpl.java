package ua.hypson.mvc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.hypson.mvc.dao.UserDao;
import ua.hypson.mvc.entity.User;
import ua.hypson.mvc.exception.InvalidUserInputException;
import ua.hypson.mvc.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UserDao userDao;

  public LoginServiceImpl() {
  }

  @Override
  public User login(String login, String password) {
    User fetched = userDao.findByLogin(login);
    if (null == fetched) {
      throw new InvalidUserInputException("Bad credentials");
    } else if (!password.equals(fetched.getPassword())) {
      throw new InvalidUserInputException("Bad credentials");
    }

    return fetched;
  }

}
