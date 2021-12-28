package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Util {
//     public static void main(String[] args) throws SQLException {
//         getConnection();
//     }
    // реализуйте настройку соеденения с БД

    private static final String USERNAME = "root";
    private static final String PASS = "5123";
    private static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/testbase"; // ссылка из подключения

    //public static SessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASS);//куда передаем юрл
            //System.out.println("Коннект есть");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();


        properties.setProperty("hibernate.connection.url", CONNECTIONURL);
        properties.setProperty("hibernate.connection.username", USERNAME);
        properties.setProperty("hibernate.connection.password", PASS);
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("show_sql", "true");


        //sessionFactory
        return new Configuration().addProperties(properties)
                .addAnnotatedClass(User.class).buildSessionFactory();
    }

}
