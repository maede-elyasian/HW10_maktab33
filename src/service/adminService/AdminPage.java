package service.adminService;

import dao.OperationLogDao;
import dao.UserDao;
import entity.OperationLog;
import java.util.List;

public class AdminPage {
    public static void displayUsers() {
            System.out.println("Sort users by age");
            System.out.println(AgeComparator.sortUsersAge().toString());
        }
        public static void userLog(int id){
            UserDao userDao = new UserDao();
            String username = userDao.getUserById(id).getUserName();
            System.out.println("displaying " + username + " logs");
            OperationLogDao operationLogDao = new OperationLogDao();
            List<OperationLog> operations = operationLogDao.getAllOperations(id);
            operations.forEach(System.out::println);
        }
        public static void execute(){
            while (true) {
                AdminMenu.displayMenu();
            }
        }
    }

