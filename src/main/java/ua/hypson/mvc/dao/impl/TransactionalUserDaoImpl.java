package ua.hypson.mvc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.hypson.mvc.dao.UserDao;
import ua.hypson.mvc.entity.User;

/**
 * Created by admin on 02.06.2016.
 */
@Repository
public class TransactionalUserDaoImpl implements UserDao {

  @Autowired
  SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public void create(User user) {
    getSession().save(user);
  }

  @Override
  public void update(User user) {
    getSession().update(user);
  }

  @Override
  public void remove(User user) {
    getSession().delete(user);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> findAll() {
    return getSession().createQuery("from User").list();
  }

  @Override
  public User findByLogin(String login) {
    return (User) getSession().createQuery("from User u where u.login = :login").setString("login", login)
        .uniqueResult();
  }

  @Override
  public User findByEmail(String email) {
    return (User) getSession().createQuery("from User u where u.email = :email").setString("email", email)
        .uniqueResult();
  }

}
