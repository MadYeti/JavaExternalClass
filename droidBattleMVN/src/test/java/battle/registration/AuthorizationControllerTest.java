package battle.registration;

import org.junit.Assert;
import org.junit.Test;

public class AuthorizationControllerTest {


    @Test
    public void authorizeUserCorrectInput() {
        AuthorizationController authorizationController = new AuthorizationController();
        boolean result = authorizationController.authorizeUser("admin","admin");

        Assert.assertTrue(result);
    }

    @Test
    public void authorizeUserIncorrectInput() {
        AuthorizationController authorizationController = new AuthorizationController();
        boolean result = authorizationController.authorizeUser("zzzz","zzzz");

        Assert.assertFalse(result);
    }
}
