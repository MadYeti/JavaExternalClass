package org.mycompany.controllers.authorization;


import org.junit.Assert;
import org.junit.Test;

public class AuthorizationControllerTest {

    @Test
    public void correctEmailRegexValidation(){
        boolean result = AuthorizationController.validateEmail("ivancov13@bigmir.net");
        Assert.assertTrue(result);
    }

    @Test
    public void incorrectEmailRegexValidation(){
        boolean result = AuthorizationController.validateEmail("baraba@.net");
        Assert.assertFalse(result);
    }

    @Test
    public void passDataValidation(){
        AuthorizationController authorizationController = new AuthorizationController();
        boolean result = authorizationController.validateData("madbone@gmail.com", "zzzZ1!");
        Assert.assertTrue(result);
    }

    @Test
    public void failedDataValidationWithIncorrectPassword(){
        AuthorizationController authorizationController = new AuthorizationController();
        boolean result = authorizationController.validateData("madbone@gmail.com", "123");
        Assert.assertFalse(result);
    }

    @Test
    public void failedDataValidationWithIncorrectEmail(){
        AuthorizationController authorizationController = new AuthorizationController();
        boolean result = authorizationController.validateData("madbone.com", "zzzZ1!");
        Assert.assertFalse(result);
    }

    @Test
    public void failedDataValidationWithIncorrectEmailAndPassword(){
        AuthorizationController authorizationController = new AuthorizationController();
        boolean result = authorizationController.validateData("madbone.com", "zzz!");
        Assert.assertFalse(result);
    }

}
