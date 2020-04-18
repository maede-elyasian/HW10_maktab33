package app;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.*;
import dto.Order;
import dto.Product;
import dto.Reading;
import dto.User;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class Purchase {

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
                if (readingDao.readingHashSet() != null)
                    System.out.println(readingDao.readingHashSet().toString());
                if (electronicDeviceDao.electronicDeviceHashSet() != null)
                    System.out.println(electronicDeviceDao.electronicDeviceHashSet().toString());
                if (shoeDao.shoeHashSet() != null)
                    System.out.println(shoeDao.shoeHashSet().toString());
                break;

            case 2:
                System.out.print("Please enter an id of products you want: ");
                id = in.nextInt();
                if (readingDao.getReadingById(id) != null)
                    checkOrders.addOrders(user, readingDao.getReadingById(id));
                if (electronicDeviceDao.getElcDevById(id) != null)
                    checkOrders.addOrders(user, electronicDeviceDao.getElcDevById(id));
                if (shoeDao.getShoeById(id) != null)
                    checkOrders.addOrders(user, shoeDao.getShoeById(id));
                break;

            case 3:
                HashSet<Order> orders = orderDao.getAllOrders(user.getId());
                double total = 0.0;
                List<Product> products = new ArrayList<>();

                for (Order order : orders) {
                    products.add(order.getProduct());
                }
                for (Product product : products) {
                    total += product.getPrice();
                }
                System.out.println("total price: " + total);

                break;

            case 4:
                HashSet<Order> orders1 = orderDao.getAllOrders(user.getId());
                List<Product> products1 = new ArrayList<>();

                for (Order order : orders1) {
                    products1.add(order.getProduct());
                    System.out.print("{"+order.getProductType()+" id:" + order.getId() + "," + "}\n" );
                }
                System.out.print("please enter product id for deleting: ");
                id = in.nextInt();
                orderDao.delete(id);

                break;

            case 5:
                orderDao.delete(user.getId());
                break;

        }

    }
}


