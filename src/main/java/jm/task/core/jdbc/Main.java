package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao uD = new UserDaoJDBCImpl();
        uD.createUsersTable();
        uD.saveUser("John","Smith", (byte) 35);
        uD.saveUser("Kate","Smith", (byte) 32);
        uD.saveUser("Emma","Smith", (byte) 3);
        uD.saveUser("Richard","Smith", (byte) 8);
        for(User user : uD.getAllUsers()){
            System.out.println(user);
        }
        uD.cleanUsersTable();
        uD.dropUsersTable();// реализуйте алгоритм здесь
    }
}
