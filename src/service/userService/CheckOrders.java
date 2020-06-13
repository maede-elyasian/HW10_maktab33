package service.userService;

import dao.OrderDao;
import dao.ProductDao;
import entity.Order;
import entity.Product;
import entity.User;

public class CheckOrders {
    OrderDao orderDao = new OrderDao();
    ProductDao productDao = new ProductDao();

    public void addOrders(User user, Product product){
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

