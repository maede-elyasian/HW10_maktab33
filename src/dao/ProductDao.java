package dao;

import connection.MyConnection;
import dto.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ProductDao {
    private Connection con;

    public ProductDao() {
        con = MyConnection.getConnection();
    }

    public Product getProductById(int id) throws SQLException {
        String select = "select * from products where product_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return showProdcuts(rs);

        }
        return null;
    }

    public Product insertProduct(Product product) throws SQLException {
        String insert = "insert into products(name,price,productNumber) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
        ps.setInt(3, product.getProductNumber());
        ps.executeUpdate();
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return showProdcuts(rs);
        }
        return null;
    }

    public boolean updateProduct(int id, Product product) throws SQLException {
        String update = "update products set product_name=?,price=?,productNumber=? where product_id=?";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
        ps.setInt(3, product.getProductNumber());
        ps.setInt(4, id);
        int row = ps.executeUpdate();

        return row == 1;


    }

    public Product deleteProduct(int id) throws SQLException {
        String delete = "delete from products where product_id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        ps.executeUpdate();

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return showProdcuts(rs);
        }
        return null;
    }

    public Product showProdcuts(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setPrice(rs.getDouble("price"));
        product.setProductNumber(rs.getInt("productNumber"));
        return product;
    }

    public HashSet<Product> allProducts() throws SQLException {
        HashSet<Product> products = new HashSet<>();
        String sql = "select * from products";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = showProdcuts(rs);
            products.add(product);
        }
        return products;

    }
}
