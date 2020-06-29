package org.mycompany.models.factory;

import org.mycompany.controllers.authorization.AuthorizationController;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.controllers.registration.RegistrationController;

/**
 * Basic interface for creating controllers
 */
public interface ControllerFactory {

    AuthorizationController getAuthorizationController();
    CreditCardDataController getCreditCardController();
    DateController getDateController();
    MailController getMailController(String emailTo, String token);
    RegistrationController getRegistrationController();

}
