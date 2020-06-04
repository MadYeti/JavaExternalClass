package org.mycompany.models.dao.sendingPointDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.models.sendingPoint.SendingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SendingPointDAOMySql implements SendingPointDAO {

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(SendingPointDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public SendingPointDAOMySql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SendingPoint read(int id) {
        SendingPoint sendingPoint = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            sendingPoint = session.get(SendingPoint.class, id);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return sendingPoint;
    }
}
