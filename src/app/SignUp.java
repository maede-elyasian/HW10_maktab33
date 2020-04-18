package app;

import dao.AddressDao;
import dao.UserDao;
import dao.getIdByTable;
import dto.Address;
import dto.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class SignUp {
    private static User user = new User();
    private static Address address = new Address();


    public static void createNewUser() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome,Please enter your information to create new account");

        System.out.print("user name: ");
        String userName = in.nextLine();
        user.setUserName(userName);

        System.out.print("password: ");
        String password = in.nextLine();
        user.setPassword(password);

        System.out.print("first name: ");
        String fName = in.nextLine();
        user.setFirstName(fName);

        System.out.print("last name: ");
        String lastName = in.nextLine();
        user.setLastName(lastName);

        System.out.print("email: ");
        String email = in.nextLine();
        user.setEmail(email);

        System.out.print("phone number: ");
        String phone = in.nextLine();
        user.setPhoneNumber(phone);

        System.out.print("state: ");
        String state = in.nextLine();
        address.setState(state);

        System.out.print("city: ");
        String city = in.nextLine();
        address.setCity(city);

        System.out.print("street: ");
        String street = in.nextLine();
        address.setStreet(street);

        System.out.print("postal code: ");
        String pc = in.nextLine();
        address.setPostalCode(pc);


        user.setAddress(address);

        AddressDao addressDao = new AddressDao();
        addressDao.insertAddress(address);

        UserDao userDao = new UserDao();
        userDao.insertUser(user);



    }

    public static User getUser() throws SQLException {
        getIdByTable getIdByTable = new getIdByTable();
        user.setId(getIdByTable.getId("users"));
        return  user;
    }


}
