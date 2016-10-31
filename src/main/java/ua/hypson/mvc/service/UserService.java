package ua.hypson.mvc.service;

import java.util.List;

import ua.hypson.mvc.entity.User;

public interface UserService {

  public void save(User user);

  public void deleteByLogin(String login);

  List<User> getAllUsers();

  public User findByLogin(String login);

  public void update(User user);

  public void registerRegular(User user);

}
