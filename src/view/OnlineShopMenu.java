package view;

import config.BeanConfig;
import dao.*;
import entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.userService.CheckOrders;
import utility.PriceComparator;

import java.util.*;

public class OnlineShopMenu {

    public static void execute() {
        Scanner in = new Scanner(System.in);
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        OperationLogDao operationLogDao = context.getBean(OperationLogDao.class);
        ProductDao productDao = context.getBean(ProductDao.class);
        OrderDao orderDao = context.getBean(OrderDao.class);
        CheckOrders checkOrders = new CheckOrders();

        User user = Main.user;

        System.out.print("enter your choice: ");
        int choice = in.nextInt();
        int id;

        switch (choice) {
            case 1:
                System.out.println("Items");
                if (productDao.getAllProducts() != null)
                    System.out.println(productDao.getAllProducts().toString());
                break;

            case 2:
                System.out.print("Please enter an id of product you want: ");
                id = in.nextInt();
                if (productDao.getProductById(id) != null)
                    checkOrders.addOrders(user, productDao.getProductById(id));
                Product product1 = productDao.getProductById(id);
                operationLogDao.productLog(product1.getName(), user, "Add");

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
                Product product2 = productDao.getProductById(id);
                operationLogDao.productLog(product2.getName(), user, "delete");
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


