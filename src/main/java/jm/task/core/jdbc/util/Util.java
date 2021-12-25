package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
//     public static void main(String[] args) throws SQLException {
//         getConnection();
//     }
    // реализуйте настройку соеденения с БД

    private static final String USERNAME = "root";
    private static final String PASS = "5123";
    private static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/testbase"; // ссылка из подключения


    public static Connection getConnection()  {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASS);//куда передаем юрл
            System.out.println("Коннект есть");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}



