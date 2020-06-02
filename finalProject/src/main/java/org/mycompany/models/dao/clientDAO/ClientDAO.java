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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Scope("prototype")
public class ClientDAO implements DAO, DAOHelper {

    private Connection connection;
    private BeanFactory beanFactory;
    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(ClientDAO.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public ClientDAO(Connection connection,
                     BeanFactory beanFactory,
                     SessionFactory sessionFactory){
        this.connection = connection;
        this.beanFactory = beanFactory;
        this.sessionFactory = sessionFactory;
    }

    public ClientDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

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

    @Override
    public void addClient(String email, String password) {
        Client client = beanFactory.getBean(Customer.class);
        client.setEmail(email);
        client.setPassword(RegistrationDataController.getHash(password));
        client.setToken("");
        System.out.println(client);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public String createToken(String email){
        String token = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.CREATETOKEN.QUERY)){
            token = RegistrationDataController.getHash(email + System.currentTimeMillis());
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return token;
    }

    @Override
    public void resetPasswordAndDeleteToken(String password, String token){
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.RESETPASSWORD.QUERY)){
            preparedStatement.setString(1, RegistrationDataController.getHash(password));
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
            try(PreparedStatement innerPreparedStatement = connection.prepareStatement(SQLQuery.DELETETOKEN.QUERY)){
                innerPreparedStatement.setString(1, token);
                innerPreparedStatement.executeUpdate();
            }
            connection.commit();
        }catch (SQLException e){
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage());
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    enum SQLQuery{
        CREATETOKEN("UPDATE clients SET token = (?) WHERE email = (?)"),
        RESETPASSWORD("UPDATE clients SET password = (?) WHERE token = (?)"),
        DELETETOKEN("UPDATE clients SET token = '' WHERE token = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
