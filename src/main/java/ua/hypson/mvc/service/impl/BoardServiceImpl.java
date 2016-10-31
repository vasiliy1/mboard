package ua.hypson.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.hypson.mvc.dao.MessageDao;
import ua.hypson.mvc.entity.Message;
import ua.hypson.mvc.entity.User;
import ua.hypson.mvc.service.BoardService;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

  @Autowired
  MessageDao messageDao;

  @Override
  public List<Message> getAllMessages() {
    return messageDao.findAll();
  }

  @Override
  public void addMessage(Message message) {
    messageDao.add(message);
  }

  @Override
  public List<Message> getMessagesOrderByDate() {
    return messageDao.findAllOrderByDate();
  }

  @Override
  public List<Message> findAllPrivateMessagesOrderByDate(User author, User recipient) {

    return messageDao.findAllPrivateMessagesOrderByDate(author, recipient);
  }

  @Override
  public void markAsViewed(Long recipient_id) {
    messageDao.markAsViewed(recipient_id);
  }

  @Override
  public List<Message> getNewMessages(User user) {
    return messageDao.getNewMessages(user);
  }

  @Override
  public void markAsViewed(List<Message> messages) {
    for(Message m : messages) {
      m.setViewed(true);
      messageDao.update(m);
    }


  }

  @Override
  public void deleteById(Long id) {
    messageDao.deleteById(id);

  }

  @Override
  public Message findById(Long id) {

    return messageDao.findById(id);
  }

  @Override
  public void delete(Message m) {
    messageDao.delete(m);
  }

}
