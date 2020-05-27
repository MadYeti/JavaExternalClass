package org.mycompany.models.factory;

import org.mycompany.controllers.authorization.AuthorizationDataController;
import org.mycompany.controllers.creditCard.CreditCardDataController;
import org.mycompany.controllers.dateProccesing.DateController;
import org.mycompany.controllers.mail.MailController;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerFactoryImpl implements ControllerFactory{

    private BeanFactory beanFactory;

    @Autowired
    public ControllerFactoryImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public AuthorizationDataController getAuthorizationDataController() {
        return beanFactory.getBean(AuthorizationDataController.class);
    }

    @Override
    public CreditCardDataController getCreditCardController() {
        return beanFactory.getBean(CreditCardDataController.class);
    }

    @Override
    public DateController getDateController() {
        return beanFactory.getBean(DateController.class);
    }

    @Override
    public MailController getMailController(String emailTo, String token) {
        return beanFactory.getBean(MailController.class, emailTo, token);
    }

    @Override
    public RegistrationDataController getRegistrationDataController() {
        return beanFactory.getBean(RegistrationDataController.class);
    }
}
