package org.mycompany.controllers.authorization;


import org.junit.Assert;
import org.junit.Test;

public class AuthorizationDataControllerTest {

    @Test
    public void correctEmailRegexValidation(){
        //boolean result = AuthorizationDataController.validateEmail("ivancov13@bigmir.net");
        //Assert.assertTrue(result);
    }

    @Test
    public void incorrectEmailRegexValidation(){
        //boolean result = AuthorizationDataController.validateEmail("baraba@.net");
        //Assert.assertFalse(result);
    }

    @Test
    public void passDataValidation(){
        AuthorizationDataController authorizationDataController = new AuthorizationDataController();
        boolean result = authorizationDataController.validateData("madbone@gmail.com", "zzzZ1!");
        Assert.assertTrue(result);
    }

    @Test
    public void failedDataValidationWithIncorrectPassword(){
        AuthorizationDataController authorizationDataController = new AuthorizationDataController();
        boolean result = authorizationDataController.validateData("madbone@gmail.com", "123");
        Assert.assertFalse(result);
    }

    @Test
    public void failedDataValidationWithIncorrectEmail(){
        AuthorizationDataController authorizationDataController = new AuthorizationDataController();
        boolean result = authorizationDataController.validateData("madbone.com", "zzzZ1!");
        Assert.assertFalse(result);
    }

    @Test
    public void failedDataValidationWithIncorrectEmailAndPassword(){
        AuthorizationDataController authorizationDataController = new AuthorizationDataController();
        boolean result = authorizationDataController.validateData("madbone.com", "zzz!");
        Assert.assertFalse(result);
    }

}
