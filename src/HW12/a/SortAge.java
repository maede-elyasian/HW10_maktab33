package HW12.a;

import dao.UserDao;
import dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SortAge {
    static ArrayList<User> userList = new ArrayList<>();

    public static ArrayList<User> sortUsersAge() {
        UserDao userDao = new UserDao();

        try {
            HashSet<User> users = userDao.allUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
