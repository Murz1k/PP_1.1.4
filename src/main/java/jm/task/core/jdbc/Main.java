package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Lena", "Kirsa", (byte) 24);
    private static final User user2 = new User("Vova", "Gresh", (byte) 25);
    private static final User user3 = new User("Kisa", "Meow", (byte) 3);


    public static void main(String[] args) {


        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());


        System.out.println(userService.getAllUsers());


    }
}
