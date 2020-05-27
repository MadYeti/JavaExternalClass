package org.mycompany.models.dao.clientDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationDataController;
import org.mycompany.models.client.Admin;
import org.mycompany.models.client.Client;
import org.mycompany.models.client.Customer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope("prototype")
public class ClientDAO implements DAO, DAOHelper {

    private Connection connection;
    private BeanFactory beanFactory;
    private static Logger logger = Logger.getLogger(ClientDAO.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    @Autowired
    public ClientDAO(Connection connection,
                     BeanFactory beanFactory){
        this.connection = connection;
        this.beanFactory = beanFactory;
    }

    @Override
    public Client getClient(String email, String password) {
        Client client = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTCLIENT.QUERY)){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, RegistrationDataController.getHash(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("role").equals("admin")){
                    client = beanFactory.getBean(Admin.class);
                }else{
                    client = beanFactory.getBean(Customer.class);
                }
                client.setId(resultSet.getInt("id"));
                client.setEmail(resultSet.getString("email"));
                client.setPassword(resultSet.getString("password"));
                client.setRole(resultSet.getString("role"));
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
        return client;
    }

    @Override
    public void addClient(String email, String password) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERTCLIENT.QUERY)){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, RegistrationDataController.getHash(password));
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
        INSERTCLIENT("INSERT INTO clients (email, password, role, token) VALUES ((?), (?), 'customer', '')"),
        SELECTCLIENT("SELECT * FROM clients WHERE email = (?) AND password = (?)"),
        CREATETOKEN("UPDATE clients SET token = (?) WHERE email = (?)"),
        RESETPASSWORD("UPDATE clients SET password = (?) WHERE token = (?)"),
        DELETETOKEN("UPDATE clients SET token = '' WHERE token = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
