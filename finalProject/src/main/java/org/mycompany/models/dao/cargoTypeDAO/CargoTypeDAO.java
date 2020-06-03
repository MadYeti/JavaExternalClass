package org.mycompany.models.dao.cargoTypeDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.models.cargoType.CargoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CargoTypeDAO implements DAO{

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(CargoTypeDAO.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public CargoTypeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public CargoType read(int id) {
        CargoType cargoType = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            cargoType = session.get(CargoType.class, id);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return cargoType;
    }
}