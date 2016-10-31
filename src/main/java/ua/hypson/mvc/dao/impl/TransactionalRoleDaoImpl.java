package ua.hypson.mvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.hypson.mvc.dao.RoleDao;
import ua.hypson.mvc.entity.Role;

/**
 * Created by admin on 02.06.2016.
 */
@Repository
public class TransactionalRoleDaoImpl implements RoleDao {

  @Autowired
  SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public void create(Role role) {
    getSession().save(role);
  }

  @Override
  public void update(Role role) {
    getSession().update(role);
  }

  @Override
  public void remove(Role role) {
    getSession().delete(role);
  }

  @Override
  public Role findByName(String name) {
    return (Role) getSession().createQuery("from Role r where r.name = :name").setString("name", name).uniqueResult();
  }

}
