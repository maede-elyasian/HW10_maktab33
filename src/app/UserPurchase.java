package app;

import dao.*;
import dto.Reading;
import dto.User;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

public class UserPurchase {

    public static void purchase() throws SQLException {
        Scanner in = new Scanner(System.in);
        ReadingDao readingDao = new ReadingDao();
        ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();
        ShoeDao shoeDao = new ShoeDao();
        OrderDao orderDao = new OrderDao();
        CheckOrders checkOrders = new CheckOrders();
        User user = SignUp.getUser();

        System.out.print("enter your choice: ");
        int choice = in.nextInt();
        int id;

        switch (choice) {
            case 1:
                System.out.println("Items");
                if (readingDao.readingHashSet() != null) {
                    HashSet<Reading> readings = readingDao.readingHashSet();
                    for (Reading reading : readings){
                        System.out.println(reading);
                    }
                }
                if (electronicDeviceDao.electronicDeviceHashSet() != null)
                    System.out.println(electronicDeviceDao.electronicDeviceHashSet().toString());
                if (shoeDao.shoeHashSet() != null)
                    System.out.println(shoeDao.shoeHashSet().toString());
                break;

            case 2:
                System.out.println("Please enter an id of products you want");
                id = in.nextInt();
                if (readingDao.getReadingById(id) != null)
                    checkOrders.addOrders(user, readingDao.getReadingById(id));
                if (electronicDeviceDao.getElcDevById(id) != null)
                    checkOrders.addOrders(user, electronicDeviceDao.getElcDevById(id));
                if (shoeDao.getShoeById(id) != null)
                    checkOrders.addOrders(user, shoeDao.getShoeById(id));
                break;

            case 3:
                orderDao.getAllOrders(user.getId());
                break;

            case 4:
                System.out.println("please enter an id of product you want to delete");
                id = in.nextInt();
                orderDao.deleteOrder(id);
                break;

            case 5:
                orderDao.getAllOrders(user.getId());

            case 6:
                orderDao.deleteOrder(user.getId());
                break;


        }
    }

}
