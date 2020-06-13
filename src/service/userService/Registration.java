package service.userService;

import dao.UserDao;
import dao.getIdByTable;
import entity.User;
import service.adminService.AdminPage;
import view.CreateNewAccount;

import java.util.Scanner;

public class Registration {
    public static User signIn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to our page");
        System.out.print("username: ");
        String username = in.nextLine();
        System.out.print("password: ");
        String password = in.nextLine();

        UserDao userDao = new UserDao();

        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            AdminPage.execute();
        } else if (userDao.searchUser(username, password) != null) {
            return userDao.searchUser(username, password);
        } else {
            System.out.println("lets create new account");
            CreateNewAccount.createNewAccount();
            int id = getIdByTable.getId("users");
            return userDao.getUserById(id);
        }
        return null;
    }
}
