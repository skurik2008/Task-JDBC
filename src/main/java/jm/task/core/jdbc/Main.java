package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static UserService userS = new UserServiceImpl();
    public static void main(String[] args) {
        userS.createUsersTable();
        userS.saveUser("Александр", "Лукашенко", (byte) 70);
        userS.saveUser("Владимир", "Путин", (byte) 70);
        userS.saveUser("Си", "Цзиньпин", (byte) 70);
        userS.saveUser("Владимир", "Зеленский", (byte) 45);
        List<User> allUsers = userS.getAllUsers();
        allUsers.forEach(System.out::println);
        userS.cleanUsersTable();
        userS.dropUsersTable();
    }
}
