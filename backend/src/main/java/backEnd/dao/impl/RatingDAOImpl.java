package backEnd.dao.impl;

import backEnd.dao.AbstractDAO;
import backEnd.dao.RatingDAO;
import backEnd.model.Rating;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RatingDAOImpl extends AbstractDAO<Integer, Rating> implements RatingDAO {

    @Override
    public void save(Rating rating) {
        getSession().saveOrUpdate(rating);
    }

    @Override
    public Rating getRatingByName(String name) {
        return (Rating)getSession().createQuery("SELECT r FROM Rating r WHERE r.name =:name")
                .setParameter("name", name).uniqueResult();
    }

    @Override
    public List<Rating> getAll() {
        return (List<Rating>)getSession().createQuery("SELECT r FROM Rating r").list();
    }
}
