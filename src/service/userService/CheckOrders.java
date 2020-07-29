package service.userService;

import config.BeanConfig;
import dao.OrderDao;
import dao.ProductDao;
import entity.Order;
import entity.Product;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CheckOrders {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

    OrderDao orderDao = applicationContext.getBean(OrderDao.class);
    ProductDao productDao = applicationContext.getBean(ProductDao.class);

    public void addOrders(User user, Product product) {
        if (orderDao.getAllOrders(user.getId()).size() < 5) {
            if (productDao.getProductById(product.getId()).getProductNumber() > 0) {
                product.setProductNumber(product.getProductNumber() - 1);
                int newProductNumber = product.getProductNumber();
                productDao.updateProduct(product.getId(), newProductNumber);
                Order order = new Order();
                order.setProduct(product);
                order.setUser(user);
                order.setProductType(product.getClass().getSimpleName());
                orderDao.saveOrder(order);
            } else
                System.out.println("Sorry does not exist right now!!");
        } else
            System.out.println("Sorry you cant have  more than 5 orders\n if you want finalize your purchase or delete one of you orders");
    }
}

