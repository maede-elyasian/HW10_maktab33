package app;

import dao.ReadingDao;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
       // AddProducts.addProducts();
       // SignUp.createNewUser();

       // while (true){
      //      ShopMenu.menu();
     //       UserPurchase.purchase();
     //   }

       ReadingDao readingDao = new ReadingDao();

        System.out.println(readingDao.readingHashSet().toString());
    }
}
