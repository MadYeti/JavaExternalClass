package org.mycompany.models.dao.sendingPointDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.sendingPoint.SendingPoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SendingPointDAOMySql implements SendingPointDAO {

    private Connection connection;
    private static Logger logger = Logger.getLogger(SendingPointDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public SendingPointDAOMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SendingPoint read(int id) {
        SendingPoint sendingPoint = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTSENDINGPOINT.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                sendingPoint = new SendingPoint();
                sendingPoint.setId(resultSet.getInt("id"));
                sendingPoint.setSendingPointEN(resultSet.getString("city_name_en_EN"));
                sendingPoint.setSendingPointUA(resultSet.getString("city_name_uk_UA"));
            }
            resultSet.close();
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
        return sendingPoint;
    }

    enum SQLQuery{
        SELECTSENDINGPOINT("SELECT * FROM sending_points WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }
}
