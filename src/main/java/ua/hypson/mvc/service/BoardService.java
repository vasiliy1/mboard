package ua.hypson.mvc.service;

import java.util.List;

import ua.hypson.mvc.entity.Message;
import ua.hypson.mvc.entity.User;

public interface BoardService {

  void addMessage(Message message);

  List<Message> getAllMessages();

  List<Message> getMessagesOrderByDate();

  List<Message> findAllPrivateMessagesOrderByDate(User author, User recipient);

  void markAsViewed(Long recipient_id);

  List<Message> getNewMessages(User user);

  void markAsViewed(List<Message> messages);

  void deleteById(Long id);

  Message findById(Long id);

  void delete(Message m);

}
