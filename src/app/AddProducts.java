package app;

import dao.ElectronicDeviceDao;
import dao.ReadingDao;
import dao.ShoeDao;
import dto.ElectronicDevice;
import dto.Reading;
import dto.Shoe;

import java.sql.SQLException;

public class AddProducts {

    public static void addProducts() throws SQLException {
        ShoeDao shoeDao = new ShoeDao();
        ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();
        ReadingDao readingDao = new ReadingDao();

        Shoe nike = new Shoe("Sneakers", 850000, 2, "Nike", "black", "39");
        ElectronicDevice samsongTv = new ElectronicDevice("TV", 22000000, 9, "gf32X200", "samsung", "2019");
        Reading book = new Reading("book", 89000, 6, "asare morakab", "darren hardy", "no andish");

        shoeDao.insertShoe(nike);
        electronicDeviceDao.inseertElectronicDev(samsongTv);
        readingDao.insertReading(book);
    }
}
