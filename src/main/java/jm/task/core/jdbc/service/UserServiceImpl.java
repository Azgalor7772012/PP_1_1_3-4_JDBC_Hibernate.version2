package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao users = new UserDaoJDBCImpl();
    public void createUsersTable() {
        users.createUsersTable();
    }

    public void dropUsersTable() {
        users.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        users.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        users.removeUserById(id);
    }

    public List<User> getAllUsers() {
       return users.getAllUsers();
    }

    public void cleanUsersTable() {
        users.cleanUsersTable();
    }
}
