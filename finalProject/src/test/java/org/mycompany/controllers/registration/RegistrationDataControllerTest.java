package org.mycompany.controllers.registration;


import org.junit.Assert;
import org.junit.Test;

public class RegistrationDataControllerTest {

    @Test
    public void successfulHashCalculating(){
        String result = RegistrationDataController.getHash("qqqQ1!");
        Assert.assertEquals("-300583c94ce97eca30867bf000cc9886", result);
    }

    @Test
    public void failedHashCalculating(){
        String result = RegistrationDataController.getHash("wer$3Q");
        Assert.assertNotEquals("-41dasc4c3xv0cv0cc9886xc0", result);
    }

    @Test
    public void successfulPasswordValidation(){
        boolean result = RegistrationDataController.validatePassword("5terV#");
        Assert.assertTrue(result);
    }

    @Test
    public void failedPasswordValidation(){
        boolean result = RegistrationDataController.validatePassword("qwerty");
        Assert.assertFalse(result);
    }

    @Test
    public void successfulEmailValidation(){
        boolean result = RegistrationDataController.validateEmail("baraba@gmail.com");
        Assert.assertTrue(result);
    }

    @Test
    public void failedEmailValidation(){
        boolean result = RegistrationDataController.validateEmail("baral11.net");
        Assert.assertFalse(result);
    }

    @Test
    public void successfulDataValidation(){
        RegistrationDataController registrationDataController = new RegistrationDataController();
        boolean result = registrationDataController.validateData("sokara@ukr.net", "ewrt@2Rt", "ewrt@2Rt");
        Assert.assertFalse(result);
    }

    @Test
    public void failedDataValidationWrongEmail(){
        RegistrationDataController registrationDataController = new RegistrationDataController();
        boolean result = registrationDataController.validateData("ukr.net", "e12r@!2Rtt", "e12r@!2Rtt");
        Assert.assertTrue(result);
    }

    @Test
    public void failedDataValidationWrongPassword(){
        RegistrationDataController registrationDataController = new RegistrationDataController();
        boolean result = registrationDataController.validateData("sokara@ukr.net", "u12r", "u12r");
        Assert.assertTrue(result);
    }

    @Test
    public void failedDataValidationWrongRetypedPassword(){
        RegistrationDataController registrationDataController = new RegistrationDataController();
        boolean result = registrationDataController.validateData("sokara@ukr.net", "tyuU7%", "asd");
        Assert.assertTrue(result);
    }

    @Test
    public void successfulPasswordsValidation(){
        RegistrationDataController registrationDataController = new RegistrationDataController();
        boolean result = registrationDataController.validatePasswords( "ewrt@2Rt", "ewrt@2Rt");
        Assert.assertFalse(result);
    }

    @Test
    public void failedPasswordsValidationWrongPassword(){
        RegistrationDataController registrationDataController = new RegistrationDataController();
        boolean result = registrationDataController.validatePasswords( "132Rt", "ewrt@2Rt");
        Assert.assertTrue(result);
    }

    @Test
    public void failedPasswordsValidationWrongRetypedPassword(){
        RegistrationDataController registrationDataController = new RegistrationDataController();
        boolean result = registrationDataController.validatePasswords( "ewrt@2Rt", "ewdst");
        Assert.assertTrue(result);
    }

}
