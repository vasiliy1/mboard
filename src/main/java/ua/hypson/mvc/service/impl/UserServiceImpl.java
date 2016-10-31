package ua.hypson.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.hypson.mvc.dao.RoleDao;
import ua.hypson.mvc.dao.UserDao;
import ua.hypson.mvc.entity.User;
import ua.hypson.mvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private RoleDao roleDao;

  public UserServiceImpl() {
  }

  @Autowired
  private UserDao userDao;

  @Override
  @Transactional
  public void save(User user) {
    userDao.create(user);
  }

  @Override
  @Transactional
  public void deleteByLogin(String login) {
    userDao.remove(userDao.findByLogin(login));
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userDao.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public User findByLogin(String login) {
    return userDao.findByLogin(login);
  }

  @Override
  @Transactional
  public void update(User user) {
    userDao.update(user);
  }

  @Override
  @Transactional
  public void registerRegular(User user) {
    user.setRole(roleDao.findByName("regular"));
    userDao.create(user);
  }

}
