package service.userService;

import config.BeanConfig;
import dao.UserDao;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import service.adminService.AdminPage;
import utility.GetIdByTable;
import view.CreateNewAccount;

import java.util.Scanner;

@Component
@Lazy
@Scope("prototype")
public class Registration {
    public User signIn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to our page");
        System.out.print("username: ");
        String username = in.nextLine();
        System.out.print("password: ");
        String password = in.nextLine();

        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        GetIdByTable getIdByTable = context.getBean(GetIdByTable.class);

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
