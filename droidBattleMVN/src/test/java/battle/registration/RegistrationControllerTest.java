package battle.registration;

import org.junit.Assert;
import org.junit.Test;

public class RegistrationControllerTest {
    @Test
    public void validatePasswordCorrectInput() {

        boolean result = RegistrationController.validatePassword("asd!23P");
        Assert.assertTrue(result);

    }

    @Test
    public void validatePasswordIncorrectInput() {

        boolean result = RegistrationController.validatePassword("asdasd");
        Assert.assertFalse(result);

    }

    @Test
    public void getHashPasswordCorrectComputation() {
        String hash = RegistrationController.getHashPassword("aaaA1!");
        Assert.assertEquals("-239d1af88e514eac43e979faca55330b", hash);
    }
}
