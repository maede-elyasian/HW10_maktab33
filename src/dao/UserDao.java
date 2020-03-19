package dao;

import connection.MyConnection;
import dto.User;
import dto.Address;
import dao.AddressDao;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.HashSet;

public class UserDao {
    private Connection con;
    AddressDao addressDao = new AddressDao();

    public UserDao() {
        con = MyConnection.getConnection();
    }

    public User getUserById(int id) throws SQLException {
        String getUser = "select * from users where id =?";
        PreparedStatement ps = con.prepareStatement(getUser);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return showUser(rs);
        }
        return null;
    }

    public HashSet<User> allUsers() throws SQLException {
        HashSet<User> users = new HashSet<>();
        String sql = "select * from users";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = showUser(rs);
            users.add(user);
        }
        return users;

    }

    public User insertUser(User user) throws SQLException {
        getIdByTable id = new getIdByTable();
        int addressId = id.getId("address");
        String insert = "insert into users(first_name,last_name,user_name,password,phone_number,email,address_id)" +
                "values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);

        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getUserName());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getPhoneNumber());
        ps.setString(6, user.getEmail());
        ps.setInt(7, addressId);
        ps.executeUpdate();

        return getUserById(user.getId());
    }

    /*
        public User updateUser(User user) throws SQLException {
            String update = "Update users set first_name = ?,last_name=?,user_name=?,password=?,phone_number=?,email=?,address_id=?"+
                             "where user_name =?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getEmail());
            ps.setInt(7, user.getAddress().getId());
           ps.setString(8,);
            /////////////////////////////////////////////
        }
    */
    public User deleteUser(int id, User user) throws SQLException {
        User deletedUser = getUserById(id);
        int getId = getUserById(id).getAddress().getId();
        String delete = "delete from users where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1, id);
        ps.executeUpdate();
        addressDao.deleteAddress(getId);

        return deletedUser;

    }

    public User showUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setFirstName(rs.getNString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setEmail(rs.getString("email"));
        user.setAddress(addressDao.getAddressbyId(rs.getInt("addrss_id")));
        return user;

    }
}
