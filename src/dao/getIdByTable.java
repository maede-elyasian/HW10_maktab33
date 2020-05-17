package dao;

import java.sql.*;

public class getIdByTable {
    private static Connection con;


    public getIdByTable() {
        con = MyConnection.getConnection();
    }
    public static int getId(String tableName) throws SQLException {
        String getId = "SELECT max(id) as id from " + tableName ;
        PreparedStatement ps = con.prepareStatement(getId);
        ResultSet resultset = ps.executeQuery();
        if (resultset.next()) {
            return resultset.getInt("id");
        }
        return 0;

    }
}
