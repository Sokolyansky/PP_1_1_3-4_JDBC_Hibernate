package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        Stream.of(
                new User("John", "Smith", (byte) 35),
                new User("Kate", "Smith", (byte) 32),
                new User("Emma", "Smith", (byte) 33),
                new User("Richard", "Smith", (byte) 38)
        ).forEach(user -> {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        });

        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();
    }
}
