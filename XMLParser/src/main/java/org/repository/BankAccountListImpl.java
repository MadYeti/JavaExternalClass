package org.repository;

import org.bankAccount.Bank;
import org.bankAccount.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by MadYeti on 09.04.2020.
 */
public class BankAccountListImpl implements Repository {

    private Connection connection;

    public BankAccountListImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<BankAccount> getBankAccountList() {
        Bank bank = new Bank();
        BankAccount bankAccount = null;
        BankAccount.AccountDetails accountDetails = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTBANKACCOUNTLIST.QUERY)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                bankAccount = new BankAccount();
                bankAccount.setName(resultSet.getString("name"));
                bankAccount.setCountry(resultSet.getString("country"));
                try(PreparedStatement innerPreparedStatement = connection.prepareStatement(SQLQuery.SELECTACCOUNTDETAILSLIST.QUERY)){
                    innerPreparedStatement.setInt(1, resultSet.getInt("accountDetail_id"));
                    ResultSet innerResultSet = innerPreparedStatement.executeQuery();
                    while(innerResultSet.next()) {
                        accountDetails = new BankAccount.AccountDetails();
                        accountDetails.setType(innerResultSet.getString("type"));
                        accountDetails.setDepositor(innerResultSet.getString("depositor"));
                        accountDetails.setAccountID(innerResultSet.getInt("id"));
                        accountDetails.setAmount(innerResultSet.getDouble("amount"));
                        accountDetails.setProfitability(innerResultSet.getDouble("profitability"));
                        accountDetails.setConstraintTime(innerResultSet.getInt("constraintTime"));
                        bankAccount.setAccountDetails(accountDetails);
                        bank.getBankAccountList().add(bankAccount);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bank.getBankAccountList();
    }

    enum SQLQuery{
        SELECTBANKACCOUNTLIST("SELECT * FROM bankaccounts"),
        SELECTACCOUNTDETAILSLIST("SELECT * FROM accountdetails WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
