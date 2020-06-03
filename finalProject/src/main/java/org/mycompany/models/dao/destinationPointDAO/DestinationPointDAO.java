package org.mycompany.models.dao.destinationPointDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.models.destinationPoint.DestinationPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DestinationPointDAO implements DAO{

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(DestinationPointDAO.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public DestinationPointDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DestinationPoint read(int id) {
        DestinationPoint destinationPoint = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            destinationPoint = session.get(DestinationPoint.class, id);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return destinationPoint;
    }
}
