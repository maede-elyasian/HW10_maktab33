import entity.Address;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.userService.validation.AddressValidationService;

public class AddressServiceTest {
   private Address address;

    @BeforeClass
    public void setUp(){
        address = new Address("tehran","1239812376");
    }

    @Test
    public void isValidCity(){
        boolean actualResult = AddressValidationService.isValidCity(address.getCity());
        Assert.assertEquals(actualResult,true);
    }

    @Test
    public void isValidPostalCode(){
        boolean actualResult = AddressValidationService.isValidPostalCode(address.getPostalCode());
        Assert.assertEquals(actualResult,true);
    }
}
