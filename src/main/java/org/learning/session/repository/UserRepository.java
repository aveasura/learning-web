package org.learning.session.repository;

import org.learning.session.dao.UserDAO;
import org.learning.session.model.User;

// Класс предоставляет данные из DAO
public class UserRepository {

    private final UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findUserById(long id) {
        return userDAO.findUserById(id);
    }

    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    public long createUser(String username, String password) {
        return userDAO.createUser(username, password);
    }

    public boolean isUserExist(User user) {
        return userDAO.isUserExist(user);
    }

    public void deleteByUserId(long userId) {
        userDAO.deleteById(userId);
    }
}
