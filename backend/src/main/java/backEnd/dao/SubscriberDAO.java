package backEnd.dao;

import backEnd.model.Subscriber;

import java.util.List;

public interface SubscriberDAO {
    void saveSubscriber(Subscriber subscriber);

    List<Subscriber> getSubscribers(Integer top, Integer skip);
}
