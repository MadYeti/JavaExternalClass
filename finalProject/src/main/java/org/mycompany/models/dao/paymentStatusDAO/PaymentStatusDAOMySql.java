package org.mycompany.models.dao.paymentStatusDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.paymentStatus.PaymentStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentStatusDAOMySql implements PaymentStatusDAO {

    private Connection connection;
    private static Logger logger = Logger.getLogger(PaymentStatusDAOMySql.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public PaymentStatusDAOMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PaymentStatus read(int id) {
        PaymentStatus paymentStatus = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTPAYMENTSTATUS.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                paymentStatus = new PaymentStatus();
                paymentStatus.setId(resultSet.getInt("id"));
                paymentStatus.setPaymentStatusEN(resultSet.getString("payment_status_en_EN"));
                paymentStatus.setPaymentStatusUA(resultSet.getString("payment_status_uk_UA"));
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
        return paymentStatus;
    }

    enum SQLQuery{
        SELECTPAYMENTSTATUS("SELECT * FROM payment_status WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }
}
