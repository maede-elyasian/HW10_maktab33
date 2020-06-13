package utility;

import dao.UserDao;
import entity.User;
import java.util.*;

public class AgeComparator {
    static ArrayList<User> userList;

    public static ArrayList<User> sortUsersAge() {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getAllUsers();
        userList = new ArrayList<>(users);

        Comparator<User> userComparator = (u1, u2) -> u1.getAge() > u2.getAge() ? 1 : u1.getAge() < u2.getAge() ? -1 : 0;
        Collections.sort(userList, userComparator);
        return userList;
    }
}
