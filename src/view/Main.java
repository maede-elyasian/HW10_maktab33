package view;

import entity.OperationLib;
import entity.OperationLogProxy;
import entity.User;
import service.userService.GetNumberFromUser;
import service.userService.Registration;

import java.sql.SQLException;

public class Main {
    public static User user;

    public static void main(String[] args) {
        user = Registration.signIn();
        OperationLib proxy = new OperationLogProxy();
        proxy.login(user, "login");
        menu();
    }

    private static void menu() {
        while (true) {
            System.out.println("-------------------------");
            System.out.println("1)Items");
            System.out.println("2)Add Order");
            System.out.println("3)Show your orders");
            System.out.println("4)Delete order with id");
            System.out.println("5)sorted orders by price");
            System.out.println("6)purchase");
            System.out.println("7)exit");
            System.out.println("--------------------------");
            try {
                GetNumberFromUser.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}