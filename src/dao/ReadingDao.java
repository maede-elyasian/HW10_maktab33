package dao;

import connection.MyConnection;
import dto.Product;
import dto.Reading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ReadingDao extends ProductDao {
    private Connection con;

    public ReadingDao(){
        con = MyConnection.getConnection();
    }

    public Reading getReadingById(int id) throws SQLException {
        String select="SELECT p.product_id,p.product_name,r.reading_name,r.author,r.publisher,p.price,p.productNumber" +
                " FROM readings r" +
                " JOIN products p" +
                " ON p.product_id = r.product_id where p.product_id=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return allReading(rs);
        }
        return null;
    }

    public Reading insertReading(Reading reading) throws SQLException {
        String insert="insert into readings(reading_name,author,publisher,product_id) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1,reading.getReadingName());
        ps.setString(2,reading.getAuthor());
        ps.setString(3,reading.getPublisher());
        ps.setInt(4,reading.getId());
        ps.executeUpdate();
        super.insertProduct(reading);

        return getReadingById(reading.getId());
    }
    public void deleteReading(int id,Reading reading) throws SQLException {
        Reading deletedReading = getReadingById(id);
        String delete="delete from readings where reading_id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1,id);
        super.deleteProduct(id);

        System.out.println(deletedReading + " deleted");
    }

    public Reading updateReading(int id,Reading reading) throws SQLException {
        String update="update reading set reading_name=?,author,publisher,product_id=?";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1,reading.getReadingName());
        ps.setString(2,reading.getAuthor());
        ps.setString(3,reading.getPublisher());
        ps.setInt(4,reading.getId());
        ps.executeUpdate();
        super.updateProduct(id,reading);

        return getReadingById(id);
    }

    public HashSet<Reading> readingHashSet() throws SQLException {
        HashSet<Reading> readings = new HashSet<>();

        String select="SELECT p.product_id,p.product_name,r.reading_name,r.author,r.publisher,p.price,p.productNumber FROM products p JOIN readings r ON p.product_id = r.product_id";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery(select);
        while (rs.next()){

            Reading reading = allReading(rs);
            readings.add(reading);

        }

        return readings;
    }
    public Reading allReading(ResultSet rs) throws SQLException {
        Reading reading = new Reading();
        reading.setId(rs.getInt("product_id"));
        reading.setName(rs.getString("product_name"));
        reading.setReadingName(rs.getString("reading_name"));
        reading.setAuthor(rs.getString("author"));
        reading.setPublisher(rs.getString("publisher"));
        reading.setPrice(rs.getDouble("price"));
        reading.setProductNumber(rs.getInt("productNumber"));

        return reading;
    }
}
