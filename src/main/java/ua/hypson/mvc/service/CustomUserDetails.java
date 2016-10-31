package ua.hypson.mvc.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ua.hypson.mvc.entity.Role;
import ua.hypson.mvc.entity.User;

public class CustomUserDetails extends User implements UserDetails {

  private static final long serialVersionUID = 1L;

  public CustomUserDetails(User user) {
    super(user);
  }

  @SuppressWarnings("serial")
  private Collection<GrantedAuthority> createAuthorityList(final Role role) {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new GrantedAuthority() {

      @Override
      public String getAuthority() {
        return role.getName();
      }
    });
    return authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return createAuthorityList(getRole());
  }

  @Override
  public String getUsername() {

    return getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
