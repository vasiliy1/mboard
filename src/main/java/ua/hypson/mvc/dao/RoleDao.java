package ua.hypson.mvc.dao;

import ua.hypson.mvc.entity.Role;

public interface RoleDao {

  public void create(Role role);

  public void update(Role role);

  public void remove(Role role);

  public Role findByName(String name);
}
