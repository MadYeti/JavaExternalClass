package org.mycompany.models.dao.cargoTypeDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.mycompany.models.cargoType.CargoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of CargoTypeDAO interface
 */
public class CargoTypeDAOMySql implements CargoTypeDAO {

    private Connection connection;
    private static Logger logger = Logger.getLogger(CargoTypeDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public CargoTypeDAOMySql(Connection connection) {
        this.connection = connection;
    }

    /**
     * Get cargo type object from cargo_type table in DB
     * @param id cargo type id that needs to be received
     * @return cargo type objects from DB
     */
    @Override
    public CargoType read(int id) {
        CargoType cargoType = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTCARGOTYPE.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                cargoType = new CargoType();
                cargoType.setId(resultSet.getInt("id"));
                cargoType.setCargoTypeEN(resultSet.getString("type_en_EN"));
                cargoType.setCargoTypeUA(resultSet.getString("type_uk_UA"));
                cargoType.setCoefficient(resultSet.getDouble("coefficient"));
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
        return cargoType;
    }

    /**
     * Enum class that encapsulate SQL queries
     */
    enum SQLQuery{
        SELECTCARGOTYPE("SELECT * FROM cargo_type WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }
}
