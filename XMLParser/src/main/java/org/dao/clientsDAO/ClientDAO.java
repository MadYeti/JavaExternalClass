package org.dao.clientsDAO;

import org.authorization.RegistrationController;
import org.clients.Admin;
import org.clients.Client;
import org.clients.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MadYeti on 10.04.2020.
 */
public class ClientDAO implements DAO{

    private Connection connection;

    public ClientDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Client getClient(String login, String password) {
        Client client = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTUSER.QUERY)){
            preparedStatement.setString(1, RegistrationController.getHash(login));
            preparedStatement.setString(2, RegistrationController.getHash(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("role").equals("user")){
                    client = new User();
                }else{
                    client = new Admin();
                }
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setRole(resultSet.getString("role"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public int insertClient(String login, String password) {
        int result = -1;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERTUSER.QUERY)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    enum SQLQuery{
        INSERTUSER("INSERT INTO clients (login, password, role) VALUES ((?), (?), 'user')"),
        SELECTUSER("SELECT * FROM clients WHERE login = (?) AND password = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
