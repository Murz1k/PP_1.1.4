package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    static UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Lena", "Ivanova", (byte) 24);
    private static final User user2 = new User("Vova", "Ivanov", (byte) 25);
    private static final User user3 = new User("Kisa", "Meow", (byte) 3);
    private static final User user4 = new User("Gena", "Ivanov", (byte) 21);


    public static void main(String[] args) {

        userService.createUsersTable();
        userService.dropUsersTable();

        userService.cleanUsersTable();



        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());


        userService.getAllUsers();
        userService.removeUserById(2);

        userService.cleanUsersTable();


        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

    }
}
