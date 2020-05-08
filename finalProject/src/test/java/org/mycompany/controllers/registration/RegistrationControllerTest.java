package org.mycompany.controllers.registration;


import org.junit.Assert;
import org.junit.Test;

public class RegistrationControllerTest {

    @Test
    public void successfulHashCalculating(){
        String result = RegistrationController.getHash("qqqQ1!");
        Assert.assertEquals("-300583c94ce97eca30867bf000cc9886", result);
    }

    @Test
    public void failedHashCalculating(){
        String result = RegistrationController.getHash("wer$3Q");
        Assert.assertNotEquals("-41dasc4c3xv0cv0cc9886xc0", result);
    }

    @Test
    public void successfulPasswordValidation(){
        boolean result = RegistrationController.validatePassword("5terV#");
        Assert.assertTrue(result);
    }

    @Test
    public void failedPasswordValidation(){
        boolean result = RegistrationController.validatePassword("qwerty");
        Assert.assertFalse(result);
    }

    @Test
    public void successfulEmailValidation(){
        boolean result = RegistrationController.validateEmail("baraba@gmail.com");
        Assert.assertTrue(result);
    }

    @Test
    public void failedEmailValidation(){
        boolean result = RegistrationController.validateEmail("baral11.net");
        Assert.assertFalse(result);
    }

    @Test
    public void successfulDataValidation(){
        RegistrationController registrationController = new RegistrationController();
        boolean result = registrationController.validateData("sokara@ukr.net", "ewrt@2Rt", "ewrt@2Rt");
        Assert.assertFalse(result);
    }

    @Test
    public void failedDataValidationWrongEmail(){
        RegistrationController registrationController = new RegistrationController();
        boolean result = registrationController.validateData("ukr.net", "e12r@!2Rtt", "e12r@!2Rtt");
        Assert.assertTrue(result);
    }

    @Test
    public void failedDataValidationWrongPassword(){
        RegistrationController registrationController = new RegistrationController();
        boolean result = registrationController.validateData("sokara@ukr.net", "u12r", "u12r");
        Assert.assertTrue(result);
    }

    @Test
    public void failedDataValidationWrongRetypedPassword(){
        RegistrationController registrationController = new RegistrationController();
        boolean result = registrationController.validateData("sokara@ukr.net", "tyuU7%", "asd");
        Assert.assertTrue(result);
    }

    @Test
    public void successfulPasswordsValidation(){
        RegistrationController registrationController = new RegistrationController();
        boolean result = registrationController.validatePasswords( "ewrt@2Rt", "ewrt@2Rt");
        Assert.assertFalse(result);
    }

    @Test
    public void failedPasswordsValidationWrongPassword(){
        RegistrationController registrationController = new RegistrationController();
        boolean result = registrationController.validatePasswords( "132Rt", "ewrt@2Rt");
        Assert.assertTrue(result);
    }

    @Test
    public void failedPasswordsValidationWrongRetypedPassword(){
        RegistrationController registrationController = new RegistrationController();
        boolean result = registrationController.validatePasswords( "ewrt@2Rt", "ewdst");
        Assert.assertTrue(result);
    }

}
