package backEnd.dao;

import backEnd.model.User;

import java.util.List;

public interface UserDAO {
    void saveUser(User user);

    List<User> getUsers(Integer top, Integer skip);

    User getByEmail(String email);
}
