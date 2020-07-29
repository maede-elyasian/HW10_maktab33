package view;

import dao.AddressDao;
import dao.UserDao;
import entity.Address;
import entity.User;
import utility.GetIdByTable;
import java.util.Scanner;


public class CreateNewAccount {
    private static User user = new User();
    private static Address address = new Address();


    public static User createNewAccount(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your information to create new account");

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

        System.out.print("age: ");
        int age = in.nextInt();
        user.setAge(age);
        in.nextLine();

        System.out.print("email: ");
        String email = in.nextLine();
        user.setEmail(email);

        System.out.print("phone number: ");
        String phone = in.nextLine();
        user.setPhoneNumber(phone);

        System.out.print("country: ");
        String country = in.nextLine();
        address.setCountry(country);

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
        UserDao userDao = new UserDao();

        addressDao.saveAddress(address);
        userDao.saveUser(user);

        return user;
    }

    public static User getUser() {
        GetIdByTable getIdByTable = new GetIdByTable();
        user.setId(getIdByTable.getId("users"));
        return user;
    }


}
