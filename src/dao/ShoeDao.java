package dao;

import connection.MyConnection;
import dto.Shoe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ShoeDao extends ProductDao {
    private Connection con;

    public ShoeDao() {
        con = MyConnection.getConnection();
    }

    public Shoe getShoeById(int id) throws SQLException {
        String sql = "SELECT p.product_id,p.product_name,sh.brand,sh.size,sh.color,p.price,p.productNumber\n" +
                "FROM online_store.shoes sh\n" +
                "join online_store.products p\n" +
                "on p.product_id = sh.product_id WHERE sh.product_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return allShoes(rs);
        }

        return null;
    }

    public Shoe insertShoe(Shoe shoe) throws SQLException {
        String insert = "insert into shoes(brand,size,color,product_id) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, shoe.getBrand());
        ps.setString(2, shoe.getSize());
        ps.setString(3, shoe.getColor());
        ps.setInt(4, shoe.getId());
        ps.executeUpdate();
        super.insertProduct(shoe);
        return getShoeById(shoe.getId());
    }

    public Shoe updateShoe(int id,Shoe shoe) throws SQLException {
        String update="update shoes set brand=?,size=?,color=? where product_id=?";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1,shoe.getBrand());
        ps.setString(2,shoe.getSize());
        ps.setString(3,shoe.getColor());
        ps.setInt(4,id);
        ps.executeUpdate();
        super.updateProduct(id,shoe);

        return getShoeById(id);
    }

    public Shoe deleteShoe(int id) throws SQLException {
        Shoe deletedShoe = getShoeById(id);
        String delete="delete from shoes where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.executeUpdate();
        super.deleteProduct(id);
        return deletedShoe;
    }

    public Shoe allShoes(ResultSet rs) throws SQLException {
        Shoe shoe = new Shoe();
        shoe.setId(rs.getInt("product_id"));
        shoe.setName(rs.getString("product_name"));
        shoe.setBrand(rs.getString("brand"));
        shoe.setSize(rs.getString("size"));
        shoe.setColor(rs.getString("color"));
        shoe.setPrice(rs.getDouble("price"));
        shoe.setProductNumber(rs.getInt("productNumber"));
        return shoe;
    }

    public HashSet<Shoe> shoeHashSet() throws SQLException {
        HashSet<Shoe> shoes = new HashSet<>();
        String sql ="SELECT p.product_id,p.product_name,sh.brand,sh.size,sh.color,p.price,p.productNumber\n" +
                "FROM online_store.shoes sh\n" +
                "join online_store.products p\n" +
                "on p.product_id = sh.product_id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Shoe shoe = allShoes(rs);
            shoes.add(shoe);
        }
        return shoes;

    }
}
