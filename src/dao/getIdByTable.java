package dao;

import java.sql.*;

public class getIdByTable {
    private Connection con;

    private ResultSet resultset;


    public int getId(String tableName) throws SQLException {
        String getId = "select max(id) as id from " + tableName;
        PreparedStatement ps = con.prepareStatement(getId);
        resultset = ps.executeQuery();
        if (resultset.next()) {
            return resultset.getInt("id");
        }

        return 0;

    }
}
