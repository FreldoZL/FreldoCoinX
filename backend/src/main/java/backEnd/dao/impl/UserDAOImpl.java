package backEnd.dao.impl;

import backEnd.dao.AbstractDAO;
import backEnd.dao.UserDAO;
import backEnd.model.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {

    @Override
    public void saveUser(User user) {
        getSession().saveOrUpdate(user);
    }

    @Override
    public List<User> getUsers(Integer top, Integer skip) {
        return (List<User>)getSession().createQuery("SELECT u FROM User u")
                .setMaxResults(top)
                .setFirstResult(skip).list();
    }

    @Override
    public User getByEmail(String email) {
        return (User)getSession().createQuery("SELECT u FROM User u WHERE u.email =:email")
                .setParameter("email", email).uniqueResult();
    }
}
