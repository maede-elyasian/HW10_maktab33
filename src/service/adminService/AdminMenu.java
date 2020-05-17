package service.adminService;

import java.util.Scanner;

public class AdminMenu {
    public static void displayMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("1)show users order by age");
        System.out.println("2)show user log");
        System.out.println("3)exit");
        System.out.print("enter: ");
        int answer = input.nextInt();
            switch (answer) {
                case 1:
                    AdminPage.displayUsers();
                    break;
                case 2:
                    System.out.print("enter user id: ");
                    int id = input.nextInt();
                    AdminPage.userLog(id);
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
    }
}
