package org.mycompany.models.dao.destinationPointDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.destinationPoint.DestinationPoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationPointDAOMySql implements DestinationPointDAO {

    private Connection connection;
    private static Logger logger = Logger.getLogger(DestinationPointDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public DestinationPointDAOMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DestinationPoint read(int id) {
        DestinationPoint destinationPoint = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTDESTINATIONPOINT.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                destinationPoint = new DestinationPoint();
                destinationPoint.setId(resultSet.getInt("id"));
                destinationPoint.setDestinationPointEN(resultSet.getString("city_name_en_EN"));
                destinationPoint.setDestinationPointUA(resultSet.getString("city_name_uk_UA"));
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
        return destinationPoint;
    }

    enum SQLQuery{
        SELECTDESTINATIONPOINT("SELECT * FROM destination_points WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }
}
