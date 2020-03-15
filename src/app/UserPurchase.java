package app;

import dao.*;
import dto.User;


import java.sql.SQLException;
import java.util.Scanner;

public class UserPurchase {

    public static void purchase() throws SQLException {
        Scanner in = new Scanner(System.in);
        ProductDao productDao = new ProductDao();
        ReadingDao readingDao = new ReadingDao();
        ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();
        ShoeDao shoeDao = new ShoeDao();
        OrderDao orderDao = new OrderDao();
        User user = SignUp.getUser();

        System.out.print("enter your choice: ");
        int choice = in.nextInt();
        int id;

        switch (choice){
            case 1:
                System.out.println("Items");
                if(readingDao.readingHashSet() != null)
                    System.out.println(readingDao.readingHashSet().toString());
                if(electronicDeviceDao.electronicDeviceHashSet() != null)
                    System.out.println(electronicDeviceDao.electronicDeviceHashSet().toString());
                if(shoeDao.shoeHashSet() != null)
                    System.out.println(shoeDao.shoeHashSet().toString());
                break;

            case 2:
                System.out.println("Please enter an id of products you want");
                id = in.nextInt();
                if (readingDao.getReadingById(id) != null)
                    orderDao.inserOrder();





        }
    }

}
