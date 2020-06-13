package view;

import dao.*;
import entity.*;
import service.userService.CheckOrders;
import utility.PriceComparator;
import java.util.*;

public class GetNumberFromUser {

    public static void execute(){
        Scanner in = new Scanner(System.in);
        ReadingDao readingDao = new ReadingDao();
        ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();
        ShoeDao shoeDao = new ShoeDao();
        OrderDao orderDao = new OrderDao();
        CheckOrders checkOrders = new CheckOrders();
        OperationLogDao operationLogDao = new OperationLogDao();
        User user = Main.user;

        System.out.print("enter your choice: ");
        int choice = in.nextInt();
        int id;

        switch (choice) {
            case 1:
                System.out.println("Items");
                if (readingDao.getAllReadings() != null)
                    System.out.println(readingDao.getAllReadings().toString());
                if (electronicDeviceDao.getAllDevices() != null)
                    System.out.println(electronicDeviceDao.getAllDevices().toString());
                if (shoeDao.getAllShoes() != null)
                    System.out.println(shoeDao.getAllShoes().toString());
                break;

            case 2:
                System.out.print("Please enter an id of products you want: ");
                id = in.nextInt();
                if (readingDao.getReadingById(id) != null)
                    checkOrders.addOrders(user, readingDao.getReadingById(id));
                operationLogDao.productLog("reading",user,"Add");

                if (electronicDeviceDao.getElcDevById(id) != null)
                    checkOrders.addOrders(user, electronicDeviceDao.getElcDevById(id));
                operationLogDao.productLog("electronic device", user, "add");

                if (shoeDao.getShoeById(id) != null)
                    checkOrders.addOrders(user, shoeDao.getShoeById(id));
                operationLogDao.productLog("shoe", user, "add");

                break;

            case 3:
                List<Order> orders = orderDao.getAllOrders(user.getId());
                double total = 0.0;
                List<Product> products = new ArrayList<>();
                for (Order order : orders) {
                    products.add(order.getProduct());
                }
                for (Product product : products) {
                    total += product.getPrice();
                }
                orders.forEach(System.out::println);
                System.out.println("total price: " + total);
                break;

            case 4:
                List<Order> orders1 = orderDao.getAllOrders(user.getId());
                List<Product> products1 = new ArrayList<>();

                for (Order order : orders1) {
                    products1.add(order.getProduct());
                    System.out.print("{" + order.getProductType() + " id:" + order.getId() + "," + "}\n");
                }
                System.out.print("please enter product id for deleting: ");
                id = in.nextInt();
                String productName = orderDao.getOrderById(id).getProduct().getName();
                operationLogDao.productLog(productName, user, "delete");
                orderDao.deleteOrder(id);
                break;

            case 5:
                List<Order> orders2 = orderDao.getAllOrders(user.getId());
                List<Product> productList = new ArrayList<>();

                for (Order order : orders2) {
                    productList.add(order.getProduct());
                }
                Collections.sort(productList, new PriceComparator());
                productList.forEach(System.out::println);
                break;

            case 6:
                double totalPrice = 0;
                List<Order> totalOrders = orderDao.getAllOrders(user.getId());
                totalPrice = totalOrders.stream().map(order -> order.getProduct().getPrice()).mapToDouble(Double::doubleValue).sum();
                operationLogDao.purchaseLog(user, totalPrice, "purchase");
                orderDao.deleteOrder(user.getId());
                break;

            case 7:
                operationLogDao.login(user, "log Out");
                System.exit(0);

        }
    }
}


