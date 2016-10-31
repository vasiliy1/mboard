package ua.hypson.mvc.dto;

import java.util.Date;

import javax.validation.constraints.Size;



public class MessageDTO {

  private Long id;

  @Size(min = 1, max = 255)
  private String body;

  private Date date;

  private String author;

  private String receiver;

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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
}
