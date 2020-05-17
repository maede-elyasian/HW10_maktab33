package service.userService;

import dao.MyConnection;
import dao.UserDao;
import dao.getIdByTable;
import entity.User;
import service.adminService.AdminPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {
   private static Connection connection = MyConnection.getConnection();
    public static User signIn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to our page");
        System.out.print("username: ");
        String username = in.nextLine();
        System.out.print("password: ");
        String password = in.nextLine();

        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
             AdminPage.execute();
        } else {
            String search = "select * from users where user_name=? and password=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(search);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                User user = null;
                int id = 0;
                UserDao userDao = new UserDao();
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                    user = userDao.getUserById(id);
                    return user;
                } else {
                    System.out.println("lets create new account");
                    CreateNewUser.createNewAccount();
                    id = getIdByTable.getId("users");
                    user = userDao.getUserById(id);
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
