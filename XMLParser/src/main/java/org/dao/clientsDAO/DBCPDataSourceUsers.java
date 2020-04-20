package org.dao.clientsDAO;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by MadYeti on 10.04.2020.
 */
public class DBCPDataSourceUsers {

    private static BasicDataSource dataSource = new BasicDataSource();
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader(new File("src/main/resources/dbUserConfig.properties")));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.user"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setInitialSize(Integer.parseInt(properties.getProperty("db.poolsize")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DBCPDataSourceUsers(){ }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
