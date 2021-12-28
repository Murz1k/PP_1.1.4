package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.security.auth.login.AppConfigurationEntry;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS test1 (id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(45), lastName VARCHAR(45), age INT)";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Таблицу создать не получилось");
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS test1";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

            session.close();
        } catch (Exception e) {
            System.out.println("Таблицу удалить не получилось");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        //User user = new User(name, lastName, age);
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();

            session.close();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (HibernateException e) {
            System.out.println("Не удалось добавить юзеров в таблицу");
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();

            session.close();
        } catch (HibernateException e) {
            System.out.println("Не удалось удалить юзера по id");
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            userList = session.createQuery("from User")
                    .getResultList();
            transaction.commit();

            session.close();

        } catch (HibernateException e) {
            System.out.println("Не удалось получить юзеров");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.createQuery("delete User").executeUpdate();
            transaction.commit();

            session.close();
        } catch (HibernateException e) {
            System.out.println("Не удалось очистить таблицу");
        }


    }
}
