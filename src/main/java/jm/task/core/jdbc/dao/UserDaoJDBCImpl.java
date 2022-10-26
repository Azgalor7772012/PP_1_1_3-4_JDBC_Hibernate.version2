package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS user (id INT not null PRIMARY KEY auto_increment, name VARCHAR(100), " +
                "lastname VARCHAR(100), age INT)";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(create);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String delete = "DROP TABLE IF EXISTS User";
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String add = "INSERT INTO User (name, lastname, age) VALUES (?, ?, ?)";
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(add)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM User WHERE id = ?";
        try(Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String selectAll = "SELECT * FROM User";

        try(Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectAll);
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }
}
