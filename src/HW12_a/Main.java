package HW12_a;

import HW12_a.AgeSort;
import dao.UserDao;
import dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {



    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
        HashSet<User> users = userDao.allUsers();
        List<User> user = new ArrayList<>(users);
        System.out.println("before sort: \n" + user.toString());
        Collections.sort(user,new AgeSort());
        System.out.println("after:\n" + user.toString());




    }
}
