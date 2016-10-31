package ua.hypson.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.hypson.mvc.dao.RoleDao;
import ua.hypson.mvc.entity.Role;
import ua.hypson.mvc.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleDao roleDao;

  @Override
  @Transactional(readOnly = true)
  public Role getRoleByName(String name) {

    return roleDao.findByName(name);
  }

}
