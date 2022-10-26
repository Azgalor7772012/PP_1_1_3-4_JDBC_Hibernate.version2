package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }



    @Override
    public void createUsersTable() {
        UserDaoJDBCImpl makeTable = new UserDaoJDBCImpl();
        makeTable.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        UserDaoJDBCImpl dropTable = new UserDaoJDBCImpl();
        dropTable.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {

            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        UserDaoJDBCImpl table = new UserDaoJDBCImpl();
        table.cleanUsersTable();
    }
}
