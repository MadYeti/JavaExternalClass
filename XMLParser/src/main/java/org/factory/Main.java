package org.factory;

import org.authorization.RegistrationController;
import org.bankAccount.BankAccount;
import org.dao.accountDAO.BankAccountDAO;
import org.dao.accountDAO.DBCPDataSource;
import org.dao.clientsDAO.ClientDAO;
import org.dao.clientsDAO.DBCPDataSourceUsers;
import org.parsers.DOMParser;
import org.parsers.SaxParser;
import org.parsers.StAXParser;
import org.repository.BankAccountListImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Created by MadYeti on 18.03.2020.
 */
public class Main {

    public static void main(String[] args) {

        try {
            BankAccountDAO bankAccountDAO = new BankAccountDAO(DBCPDataSource.getConnection());
            StAXParser stAXParser = new StAXParser();
            bankAccountDAO.update(stAXParser.parseXMLElement(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
