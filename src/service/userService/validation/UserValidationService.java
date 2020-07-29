package service.userService.validation;

import entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("prototype")
public class UserValidationService {

    public static boolean isValidPassword(String password) {
        boolean isValidPass;
        if (password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*") && password.length() >= 4) {
            isValidPass = true;
        } else {
            System.out.println("invalid password!! password should be numerical,alphabetic and has more than 4 characters");
            isValidPass = false;
        }
        return isValidPass;
    }

    public static boolean isValidPhoneNumber(String num) {
        boolean isValid;
        if (num.matches(".*[0-9].*") && (num.length() == 11 || num.length() == 8)) {
            isValid = true;
        } else {
            System.out.println(num + " is not valid phone number");
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidEmail(String email) {
        if (!email.contains("@")) {
            System.out.println(email + "is not valid address");
            return false;
        }
        return true;
    }

    public static boolean isValidAge(int age){
        if (age > 0 && age < 100){
            return true;
        }
        return false;
    }

    public static boolean isValidUser(User user){
        if (isValidAge(user.getAge())
                && isValidEmail(user.getEmail())
                && isValidPassword(user.getPassword())
                && isValidPhoneNumber(user.getPhoneNumber()))
            return true;
        return false;
    }

}
