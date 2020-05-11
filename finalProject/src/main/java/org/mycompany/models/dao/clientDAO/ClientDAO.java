package org.mycompany.models.dao.clientDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.controllers.registration.RegistrationController;
import org.mycompany.models.client.Admin;
import org.mycompany.models.client.Client;
import org.mycompany.models.client.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientDAO implements DAO {

    private Connection connection;
    private static Logger logger = Logger.getLogger(ClientDAO.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public ClientDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Client getClient(String email, String password) {
        Client client = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTCLIENT.QUERY)){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, RegistrationController.getHash(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("role").equals("admin")){
                    client = new Admin();
                }else{
                    client = new Customer();
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
            preparedStatement.setString(2, RegistrationController.getHash(password));
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

    public String createToken(String email){
        String token = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.CREATETOKEN.QUERY)){
            token = RegistrationController.getHash(email + System.currentTimeMillis());
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

    public void resetPasswordAndDeleteToken(String password, String token){
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.RESETPASSWORD.QUERY)){
            preparedStatement.setString(1, RegistrationController.getHash(password));
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
        INSERTCLIENT("INSERT INTO clients (email, password, role) VALUES ((?), (?), 'customer')"),
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