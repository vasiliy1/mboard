package ua.hypson.mvc.dao;

import java.util.List;

import ua.hypson.mvc.entity.Message;
import ua.hypson.mvc.entity.User;

public interface MessageDao {

  public void add(Message message);

  public void update(Message message);

  public void remove(Message message);

  public List<Message> findAll();

  public List<Message> findAllOrderByDate();

  public List<Message> findAllPrivateMessagesOrderByDate(User author, User recipient);

  public void markAsViewed(Long recipient_id);

  public List<Message> getNewMessages(User user);

  public void deleteById(Long id);

  public Message findById(Long id);

  public void delete(Message m);
}
