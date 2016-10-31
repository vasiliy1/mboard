package ua.hypson.mvc.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.hypson.mvc.dao.UserDao;
import ua.hypson.mvc.entity.User;

@Service("hibernateUserDetailsService")
@Transactional
public class HibernateUserDetailsService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(HibernateUserDetailsService.class);

  @Autowired
  private UserDao userDao;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    logger.debug("user login = " + login);
    User user = userDao.findByLogin(login);
    CustomUserDetails customUserDetails = new CustomUserDetails(user);
    ArrayList<GrantedAuthority> list = new ArrayList<>(customUserDetails.getAuthorities());
    logger.debug("userDetails authorities: " + list.get(0).getAuthority());
    return customUserDetails;
  }

}
