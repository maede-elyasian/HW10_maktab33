package service.adminService;

import config.BeanConfig;
import dao.OperationLogDao;
import dao.UserDao;
import entity.OperationLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import utility.AgeComparator;

import java.util.List;

public class AdminPage {
    public static void displayUsers() {
            System.out.println("Sort users by age");
            System.out.println(AgeComparator.sortUsersAge().toString());
        }
        public static void userLog(int id){
            ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
            UserDao userDao = context.getBean(UserDao.class);

            String username = userDao.getUserById(id).getUserName();
            System.out.println("displaying " + username + " logs");
            OperationLogDao operationLogDao = context.getBean(OperationLogDao.class);
            List<OperationLog> operationLogs = operationLogDao.getAllOperations(id);
            operationLogs.forEach(System.out::println);
        }
        public static void execute(){
            while (true) {
                AdminMenu.displayMenu();
            }
        }
    }

