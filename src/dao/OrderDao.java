package dao;

import entity.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class OrderDao {
    private Connection con;

    public OrderDao() {
        con = MyConnection.getConnection();
    }

    public Order getOrderById(int id) throws SQLException {
        String get = "select * from orders where id=?";
        PreparedStatement ps = con.prepareStatement(get);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return showOrders(rs);
        }
        return null;
    }

    public boolean inserOrder(Order order) throws SQLException {
        String insert = "insert into orders(product_type,product_id,user_id) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, order.getProductType());
        ps.setInt(2, order.getProduct().getId());
        ps.setInt(3, order.getUser().getId());
        int row = ps.executeUpdate();

        return row == 1;
    }

    public HashSet<Order> getAllOrders(int userId) throws SQLException {
        HashSet<Order> orders = new HashSet<>();
        String select = "select * from orders where user_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Order order = showOrders(rs);
            orders.add(order);
        }
        return orders;
    }

    public Order updateOrder(int id, Order order) throws SQLException {
        String update = "update orders set product_type=?,product_id=?,user_id=? where id=?";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1, order.getProductType());
        ps.setInt(2, order.getProduct().getId());
        ps.setInt(3, order.getUser().getId());
        ps.setInt(4, id);
        ps.executeUpdate();

        return getOrderById(order.getId());
    }

    public boolean deleteOrder(int id) throws SQLException {
        String delete = "delete from orders where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        int row = ps.executeUpdate();

        return row == 1;
    }

    public void delete(int id) throws SQLException {
        String delete = "delete from orders where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
       ps.executeUpdate();

    }
    public Order showOrders(ResultSet rs) throws SQLException {
        Order order = new Order();
        UserDao userDao = new UserDao();
        ProductDao productDao = new ProductDao();

        String productType = rs.getString("product_type");
        int productId = rs.getInt("product_id");
        int userId = rs.getInt("user_id");

        order.setId(rs.getInt("id"));
        order.setUser(userDao.getUserById(userId));
        order.setProductType(rs.getString("product_type"));
        System.out.println(productType);
        order.setProduct(productDao.getProductById(productId));

        switch (productType) {
            case "shoe":
                ShoeDao shoeDao = new ShoeDao();
                Shoe shoe = shoeDao.getShoeById(productId);
                order.setProduct(shoe);

                break;

            case "electronicDevice":
                ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();
                ElectronicDevice electronicDevice = electronicDeviceDao.getElcDevById(productId);
                order.setProduct(electronicDevice);

                break;

            case "reading":
                ReadingDao readingDao = new ReadingDao();
                Reading reading = readingDao.getReadingById(productId);
                order.setProduct(reading);
                break;
        }
        return order;

    }
}
