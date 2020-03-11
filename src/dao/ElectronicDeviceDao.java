package dao;

import connection.MyConnection;
import dto.ElectronicDevice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ElectronicDeviceDao extends ProductDao {
    private Connection con;
    ProductDao productDao = new ProductDao();

    public ElectronicDeviceDao(){
        con = MyConnection.getConnection();
    }

    public ElectronicDevice getElcDevById(int id) throws SQLException {
        String select = "select p.product_name,e.brand ,e.model,p.price, e.productionYear,p.productNumber from online_store.electronic_devices e JOIN online_store.products p on e.product_id=p.product_id where e.product_id =?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return allDevices(rs);
        }
       return null;

    }

    public ElectronicDevice inseertElectronicDev(ElectronicDevice el) throws SQLException {
        String insert = "insert into electronic_devices(model,brand,productionYear)" +
                        "values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1,el.getModel());
        ps.setString(2,el.getBrand());
        ps.setString(3,el.getProductionYear());
        super.insertProduct(el);
        ps.executeUpdate();
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return allDevices(rs);
        }
        return null;
    }

    public ElectronicDevice updateElectronicDev(int id,ElectronicDevice electronicDevice) throws SQLException {
        super.updateProduct(id,electronicDevice);
        String update ="update electronic_devices set model=?,brand=?,productionYear=? where id=?";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1,electronicDevice.getModel());
        ps.setString(2,electronicDevice.getBrand());
        ps.setString(3,electronicDevice.getProductionYear());
        ps.setInt(4,id);
        ps.executeUpdate();

            return getElcDevById(electronicDevice.getId());

    }

    public ElectronicDevice deleteElectronicDev(int id) throws SQLException {
        ElectronicDevice deletedDevice = getElcDevById(id);
        super.deleteProduct(id);
        String delete = "delete from electronic_devices where id=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setInt(1,id);
        ps.executeUpdate();

        return deletedDevice;
    } 

    public ElectronicDevice allDevices(ResultSet rs) throws SQLException {
        ElectronicDevice electronicDevice = new ElectronicDevice();
        electronicDevice.setName(rs.getString("name"));
        electronicDevice.setModel(rs.getString("model"));
        electronicDevice.setBrand(rs.getString("brand"));
        electronicDevice.setPrice(rs.getDouble("price"));
        electronicDevice.setProductionYear(rs.getString("productionYear"));
        electronicDevice.setProductNumber(rs.getInt("productNumber"));
        return electronicDevice;
    }

    public HashSet<ElectronicDevice> electronicDeviceHashSet() throws SQLException {
        HashSet<ElectronicDevice> devices = new HashSet();
        String sql = "select p.product_name,e.brand ,e.model,p.price, e.productionYear,p.productNumber from online_store.electronic_devices e JOIN online_store.products p on e.product_id=p.product_id ";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            ElectronicDevice electronicDevice = allDevices(rs);
            devices.add(electronicDevice);
        }
        return devices;

    }




}
