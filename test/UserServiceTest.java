import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.userService.validation.UserValidationService;

public class UserServiceTest {
    private User user;
    @BeforeClass
    public void setUp(){
        user = new User("23r43","09198053172","maede@gmail.com",20);
    }

    @Test()
    public void isValidPassword(){
        boolean actualResult = UserValidationService.isValidPassword(user.getPassword());
        Assert.assertEquals(actualResult,true);
    }

    @Test
    public void isValidAge(){
        boolean actualResult  = UserValidationService.isValidAge(user.getAge());
        Assert.assertEquals(actualResult,true);
    }

    @Test
    public void isValidPhoneNumber(){
        boolean actualResult = UserValidationService.isValidPhoneNumber(user.getPhoneNumber());
        Assert.assertEquals(actualResult,true);
    }

    @Test
    public void isValidEmail(){
        boolean actualResult = UserValidationService.isValidEmail(user.getEmail());
        Assert.assertEquals(actualResult,true);
    }
}
