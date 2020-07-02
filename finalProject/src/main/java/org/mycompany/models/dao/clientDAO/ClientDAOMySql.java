package org.mycompany.models.dao.clientDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.models.client.Admin;
import org.mycompany.models.client.Client;
import org.mycompany.models.client.Customer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.criteria.*;

/**
 * Implementation of CRUD and helper interface
 */
@Component
@Scope("prototype")
public class ClientDAOMySql implements ClientDAO, ClientDAOHelper {

    private BeanFactory beanFactory;
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(ClientDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public ClientDAOMySql(BeanFactory beanFactory,
                          SessionFactory sessionFactory){
        this.beanFactory = beanFactory;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get client object from clients table in DB
     * @param email email of client that needs to be received
     * @param password password of client that needs to be received
     * @return client object from DB
     */
    @Override
    public Client getClient(String email, String password) {
        Client client = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            Predicate predicateForEmail = criteriaBuilder.equal(root.get("email"), email);
            Predicate predicateForPassword = criteriaBuilder.equal(root.get("password"), RegistrationDataController.getHash(password));
            Predicate finalPredicate = criteriaBuilder.and(predicateForEmail, predicateForPassword);
            criteriaQuery.select(root);
            criteriaQuery.where(finalPredicate);
            Query query = session.createQuery(criteriaQuery);
            client = (Customer)query.getSingleResult();
            if(client.getRole().equals("admin")){
                Client admin = beanFactory.getBean(Admin.class);
                admin.setId(client.getId());
                admin.setEmail(client.getEmail());
                admin.setPassword(client.getPassword());
                admin.setRole(client.getRole());
                client = admin;
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return client;
    }

    /**
     * Create client record in clients table in DB
     * @param email client email that needs to be stored in DB
     * @param password client password that needs to be stored in DB
     */
    @Override
    public void addClient(String email, String password) {
        Client client = beanFactory.getBean(Customer.class);
        client.setEmail(email);
        client.setPassword(RegistrationDataController.getHash(password));
        client.setToken("");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * Create random token of specific client
     * @param email client email for whom creating the token
     * @return token of specific client
     */
    @Override
    public String createToken(String email){
        String token = RegistrationDataController.getHash(email + System.currentTimeMillis());
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Client> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Client.class);
            Root<Client> root = criteriaUpdate.from(Client.class);
            criteriaUpdate.set(root.get("token"), token);
            criteriaUpdate.where(criteriaBuilder.equal(root.get("email"), email));
            Query query = session.createQuery(criteriaUpdate);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return token;
    }

    /**
     * Method reset new password and delete token record in DB of specific client
     * @param password new password that needs to be reset
     * @param token token that needs to be deleted
     */
    @Override
    public void resetPasswordAndDeleteToken(String password, String token){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Client> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Client.class);
            Root<Client> root = criteriaUpdate.from(Client.class);
            criteriaUpdate.set(root.get("password"), RegistrationDataController.getHash(password));
            criteriaUpdate.set(root.get("token"), "");
            criteriaUpdate.where(criteriaBuilder.equal(root.get("token"), token));
            Query query = session.createQuery(criteriaUpdate);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
