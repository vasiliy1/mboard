package ua.hypson.mvc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "USERS")
@GenericGenerator(name = "inc-gen", strategy = "increment")
public class User implements Serializable {

  private static final long serialVersionUID = 3017106929616806723L;

  @Id
  @GeneratedValue(generator = "inc-gen")
  @Column(name = "USER_ID")
  private Long id;

  @NotNull
  @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$")
  @Column(name = "LOGIN", nullable = false, unique = true)
  private String login;

  @NotNull
  @Size(min = 6, max = 30)
  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @NotNull
  @Email
  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;

  @Size(min = 2, max = 20)
  @Column(name = "FIRST_NAME")
  private String firstName;

  @Size(min = 2, max = 20)
  @Column(name = "LAST_NAME")
  private String lastName;

  @Past
  @Temporal(TemporalType.DATE)
  @Column(name = "BIRTHDAY")
  private Date birthday;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ROLE_ID")
  private Role role;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "author")
  private List<Message> messages;

  public static User createNewUser(String login, String password, String email, String firstName, String lastName,
      Date birthday, Role role) {
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    user.setEmail(email);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setBirthday(birthday);
    user.setRole(role);
    return user;
  }

  public User() {
  }

  public User(User user) {
    super();
    this.id = user.getId();
    this.login = user.getLogin();
    this.password = user.getPassword();
    this.email = user.getEmail();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.birthday = user.getBirthday();
    this.role = user.getRole();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(final Date birthday) {
    this.birthday = birthday;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", firstName="
        + firstName + ", lastName=" + lastName + ", birthday=" + birthday + ", role=" + role + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((birthday == null) ? 0 : birthday.hashCode());
    result = (prime * result) + ((id == null) ? 0 : id.hashCode());
    result = (prime * result) + ((login == null) ? 0 : login.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    User other = (User) obj;
    if (birthday == null) {
      if (other.birthday != null) {
        return false;
      }
    } else if (!birthday.equals(other.birthday)) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (login == null) {
      if (other.login != null) {
        return false;
      }
    } else if (!login.equals(other.login)) {
      return false;
    }
    return true;
  }

}
