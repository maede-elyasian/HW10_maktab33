package service.userService.validation;

import entity.Address;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class AddressValidationService {

    public static boolean isValidCity(String city) {
        boolean isValid;
        if (!city.matches(".*[0-9].*")) {
            isValid = true;
        } else {
            System.out.println(city + " is not valid city name");
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPostalCode(String postCode) {
        boolean isvalid;
        if (postCode.length() == 10 && postCode.matches(".*[0-9].*")) {
            isvalid = true;
        } else {
            System.out.println(postCode + " is not valid!postal code should numerical with length 10");
            isvalid = false;
        }

        return isvalid;
    }

    public static boolean isValidAddress(Address address) {
        if (isValidCity(address.getCity())
                && isValidPostalCode(address.getPostalCode())) {
            return true;
        }
        return false;
    }


}
