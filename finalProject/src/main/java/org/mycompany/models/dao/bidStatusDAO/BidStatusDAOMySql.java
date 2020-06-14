package org.mycompany.models.dao.bidStatusDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.bidStatus.BidStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BidStatusDAOMySql implements BidStatusDAO {

    private Connection connection;
    private static Logger logger = Logger.getLogger(BidStatusDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public BidStatusDAOMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public BidStatus read(int id) {
        BidStatus bidStatus = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTBIDSTATUS.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                bidStatus = new BidStatus();
                bidStatus.setId(resultSet.getInt("id"));
                bidStatus.setBidStatusEN(resultSet.getString("bid_status_en_EN"));
                bidStatus.setBidStatusUA(resultSet.getString("bid_status_uk_UA"));
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
        return bidStatus;
    }

    enum SQLQuery{
        SELECTBIDSTATUS("SELECT * FROM bid_status WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }
}
