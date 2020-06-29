package org.mycompany.models.factory;

import org.mycompany.controllers.authorization.AuthorizationController;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.controllers.registration.RegistrationController;

/**
 * Implementation of controller factory
 */
public class ControllerFactoryImpl implements ControllerFactory{

    public ControllerFactoryImpl() {

    }

    @Override
    public AuthorizationController getAuthorizationController() {
        return new AuthorizationController();
    }

    @Override
    public CreditCardDataController getCreditCardController() {
        return new CreditCardDataController();
    }

    @Override
    public DateController getDateController() {
        return new DateController();
    }

    @Override
    public MailController getMailController(String emailTo, String token) {
        return new MailController(emailTo, token);
    }

    @Override
    public RegistrationController getRegistrationController() {
        return new RegistrationController();
    }
}
