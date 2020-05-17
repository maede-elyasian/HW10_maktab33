package service.userService;

import dao.*;
import entity.*;
import view.Main;

import java.sql.SQLException;
import java.util.*;

public class GetNumberFromUser {

    public static void execute() throws SQLException {
        Scanner in = new Scanner(System.in);
        ReadingDao readingDao = new ReadingDao();
        ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();
        ShoeDao shoeDao = new ShoeDao();
        OrderDao orderDao = new OrderDao();
        CheckOrders checkOrders = new CheckOrders();
        OperationLib proxy = new OperationLogProxy();
        User user = Main.user;

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
                proxy.productLog("reading", user, "add");

                if (electronicDeviceDao.getElcDevById(id) != null)
                    checkOrders.addOrders(user, electronicDeviceDao.getElcDevById(id));
                proxy.productLog("electronic device", user, "add");

                if (shoeDao.getShoeById(id) != null)
                    checkOrders.addOrders(user, shoeDao.getShoeById(id));
                proxy.productLog("shoe", user, "add");

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
                    System.out.print("{" + order.getProductType() + " id:" + order.getId() + "," + "}\n");
                }
                System.out.print("please enter product id for deleting: ");
                id = in.nextInt();
                String productName = orderDao.getOrderById(id).getProduct().getName();
                proxy.productLog(productName, user, "delete");
                orderDao.delete(id);
                break;

            case 5:
                HashSet<Order> orders2 = orderDao.getAllOrders(user.getId());
                List<Product> productList = new ArrayList<>();

                for (Order order : orders2) {
                    productList.add(order.getProduct());
                }
                Collections.sort(productList, new PriceComparator());
                productList.forEach(System.out::println);
                break;

            case 6:
                double totalPrice = 0;
                HashSet<Order> totalOrders = orderDao.getAllOrders(user.getId());
                totalPrice = totalOrders.stream().map(order -> order.getProduct().getPrice()).mapToDouble(Double::doubleValue).sum();
                proxy.purchaseLog(user, totalPrice, "purchase");
                orderDao.delete(user.getId());
                break;

            case 7:
                proxy.login(user, "logOut");
                System.exit(0);

        }
    }
}


