package backEnd.dao.impl;

import backEnd.dao.AbstractDAO;
import backEnd.dao.SubscriberDAO;
import backEnd.model.Subscriber;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SubscriberDAOImpl extends AbstractDAO<Integer, Subscriber> implements SubscriberDAO {
    @Override
    public void saveSubscriber(Subscriber subscriber) {
        getSession().saveOrUpdate(subscriber);
    }

    @Override
    public List<Subscriber> getSubscribers(Integer top, Integer skip) {
        return (List<Subscriber>)getSession().createQuery("SELECT s FROM Subscriber s")
                .setMaxResults(top)
                .setFirstResult(skip).list();
    }
}
