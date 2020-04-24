package HW12.a;

import dao.UserDao;
import dto.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;


public class SortAge {
    static ArrayList<User> userList;

    public static ArrayList<User> sortUsersAge() {
        UserDao userDao = new UserDao();
        try {
            HashSet<User> users = userDao.allUsers();
            userList = new ArrayList<>(users);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Comparator<User> userComparator =(u1,u2)->u1.getAge()>u2.getAge()? 1 : u1.getAge() < u2.getAge() ? -1 : 0;
        Collections.sort(userList,userComparator);
        return userList;
    }



}
