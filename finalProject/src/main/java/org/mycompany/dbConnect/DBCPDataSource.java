package org.mycompany.dbConnect;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Component
@Scope("prototype")
public class DBCPDataSource {

    private static BasicDataSource dataSource = new BasicDataSource();
    private static Properties properties = new Properties();
    private static Connection connection;
    private static Logger logger = Logger.getLogger(DBCPDataSource.class);

    static {
        try {
            properties.load(new FileReader(new File("src/main/resources/dbConfig.properties")));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.user"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setInitialSize(Integer.parseInt(properties.getProperty("db.poolsize")));
            PropertyConfigurator.configure("src/main/resources/logConfig.properties");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public DBCPDataSource(){ }

    @Bean
    @Scope("prototype")
    public static Connection getConnection(){
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

}
