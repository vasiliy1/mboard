package ua.hypson.mvc.dao;

import java.util.List;

import ua.hypson.mvc.entity.User;

public interface UserDao {

  public void create(User user);

  public void update(User user);

  public void remove(User user);

  public List<User> findAll();

  public User findByLogin(String login);

  public User findByEmail(String email);

}
