package org.dao.accountDAO;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by MadYeti on 07.04.2020.
 */
public class DBCPDataSource {

    private static BasicDataSource dataSource = new BasicDataSource();
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader(new File("src/main/resources/dbConfig.properties")));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.user"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setInitialSize(Integer.parseInt(properties.getProperty("db.poolsize")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DBCPDataSource(){ }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


}
