package app;

import dao.OrderDao;
import dao.ProductDao;
import dto.Order;
import dto.Product;
import dto.User;

import java.sql.SQLException;

public class CheckOrders {
    OrderDao orderDao = new OrderDao();
    ProductDao productDao = new ProductDao();

    public boolean addOrders(User user, Product product) throws SQLException {
        if (orderDao.getAllOrders(user.getId()).size() < 5) {
            if (productDao.getProductById(product.getId()).getProductNumber() > 0) {
                product.setProductNumber(product.getProductNumber() - 1);
                productDao.updateProduct(product.getId(), product);
                Order order = new Order();
                order.setProduct(product);
                order.setUser(user);
                order.setProductType(product.getClass().getSimpleName());
                return orderDao.inserOrder(order);
            } else
                System.out.println("Sorry does not exist right now!!");
        }else
            System.out.println("Sorry you cant have  more than 5 orders\n if you want finalize your purchase or delete one of you orders");
        return false;
    }
}
