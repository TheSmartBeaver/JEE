package springapp.business;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MessageManager implements IMessageManager {

    @Autowired
    @PersistenceContext(unitName = "myData")
    EntityManager em;

    @Override
    public void add(String data) {
        Message m = new Message(data);
        em.persist(m);
        System.out.println("NEW MESSAGE = " + m);
    }

    @Override
    public int removeAll() {
        return em.createNamedQuery("Message.removeAll").executeUpdate();
    }

    @Override
    public Collection<Message> findAll() {
        return em.createNamedQuery("Message.findAll", Message.class).getResultList();
    }
}