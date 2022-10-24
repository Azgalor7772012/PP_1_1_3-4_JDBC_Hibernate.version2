package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        //таблица
        user.createUsersTable();

        //4юзера
        user.saveUser("Lena", "tuk", (byte) 23);
        user.saveUser("Konstantin", "gyte", (byte) 24);
        user.saveUser("Olga", "bit", (byte) 50);
        user.saveUser("Misha", "otk", (byte) 43);

        //Получаем всех юзеров
        List<User> listOfUsers =  user.getAllUsers();
        System.out.println(listOfUsers);

        //чистим таблицу
        user.cleanUsersTable();

        //удаляем таблицу
        user.dropUsersTable();
    }
}
