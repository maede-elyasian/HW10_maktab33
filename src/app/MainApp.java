package app;
import java.sql.SQLException;

public class MainApp {


    public static void main(String[] args) throws SQLException {

        SignUp.createNewUser();
        while (true) {
            ShopMenu.menu();
            Purchase.purchase();
        }


    }
}