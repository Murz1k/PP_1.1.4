package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {   // создать таблицу юзеров
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS test1"
                    + "(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age INT) ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() { // удалить таблицу
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS test1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) { // добавить юзеров
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test1(name, lastName, age) VALUE (?,?,?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) { // удалить по id
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM test1 WHERE id=?");

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {//получить всех юзеров
        List<User> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM test1";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                people.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public void cleanUsersTable() { //убрать данные с табл
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE test1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
