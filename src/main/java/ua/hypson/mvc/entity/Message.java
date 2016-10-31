package ua.hypson.mvc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Message")
@GenericGenerator(name = "inc-gen", strategy = "increment")
public class Message implements Serializable {

  private static final long serialVersionUID = 7225462139214873153L;

  @Id
  @GeneratedValue(generator = "inc-gen")
  @Column(name = "MESSAGE_ID")
  private Long id;

  @Column(name = "BODY")
  @Size(min = 1, max = 255)
  private String body;

  @Column(name = "DATE")
  private Date date;

  @ManyToOne
  @JoinColumn(name = "AUTHOR_ID")
  private User author;

  @ManyToOne
  @JoinColumn(name = "RECIPIENT_ID")
  private User recipient;

  @Column(name = "VIEWED")
  private Boolean viewed;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public User getReceiver() {
    return recipient;
  }

  public void setReceiver(User receiver) {
    this.recipient = receiver;
  }

  public Boolean getViewed() {
    return viewed;
  }

  public void setViewed(Boolean viewed) {
    this.viewed = viewed;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Message [id=");
    builder.append(id);
    builder.append(", body=");
    builder.append(body);
    builder.append(", date=");
    builder.append(date);
    builder.append(", author=");
    builder.append(author);
    builder.append("]");
    return builder.toString();
  }

}
