package ua.hypson.mvc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.hypson.mvc.dao.MessageDao;
import ua.hypson.mvc.entity.Message;
import ua.hypson.mvc.entity.User;

@Repository
public class MessageDaoImpl implements MessageDao {

  @Autowired
  SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public void add(Message message) {
    getSession().save(message);

  }

  @Override
  public void update(Message message) {
    getSession().update(message);
  }

  @Override
  public void remove(Message message) {
    getSession().delete(message);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Message> findAll() {
    return getSession().createQuery("FROM Message").list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Message> findAllOrderByDate() {
                                      //null recipient means that message sent to the general board
    return getSession().createQuery("FROM Message m WHERE m.recipient IS NULL ORDER BY date DESC").list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Message> findAllPrivateMessagesOrderByDate(User author, User recipient) {

    return getSession()
        .createQuery("FROM Message m WHERE (m.author = :author_id AND m.recipient = :recipient_id)"
            + "OR (m.recipient = :author_id AND m.author = :recipient_id) ORDER BY date DESC")
        .setLong("author_id", author.getId()).setLong("recipient_id", recipient.getId())
        .setLong("author_id", author.getId()).setLong("recipient_id", recipient.getId()).list();
  }

  @Override
  public void markAsViewed(Long recipient_id) {
    getSession().createQuery("UPDATE Message m SET viewed = TRUE where m.recipient = :recipient_id")
        .setLong("recipient_id", recipient_id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Message> getNewMessages(User user) {

    return getSession()
        .createQuery("FROM Message m WHERE m.viewed = FALSE AND m.recipient = :user_id ORDER BY date DESC")
        .setLong("user_id", user.getId()).list();
  }

  @Override
  public void deleteById(Long id) {
    getSession().createQuery("DELETE FROM Message m WHERE m.id = :id").setLong("id", id);

  }

  @Override
  public Message findById(Long id) {
    return (Message)getSession().createQuery("FROM Message m WHERE m.id = :id").setLong("id", id).uniqueResult();
  }

  @Override
  public void delete(Message m) {
    getSession().delete(m);

  }

}
