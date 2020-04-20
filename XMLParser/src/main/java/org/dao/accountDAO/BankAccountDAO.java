package org.dao.accountDAO;

import org.bankAccount.Bank;
import org.bankAccount.BankAccount;
import org.repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by MadYeti on 23.03.2020.
 */
public class BankAccountDAO implements DAO, Repository{

    private Connection connection;

    public BankAccountDAO(Connection connection){
        this.connection = connection;
    }


    @Override
    public boolean create(BankAccount bankAccount) {//try catch with transaction
        BankAccount.AccountDetails accountDetails = bankAccount.getAccountDetails();
        int resultFirstQuery = -1;
        int resultSecondQuery = -1;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERTBANKACCOUNT.QUERY)){
            preparedStatement.setString(1, bankAccount.getName());
            preparedStatement.setString(2, bankAccount.getCountry());
            preparedStatement.setInt(3, accountDetails.getAccountID());
            resultFirstQuery = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();//logging
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.INSERTACCOUNTDETAILS.QUERY)){
            preparedStatement.setInt(1, accountDetails.getAccountID());
            preparedStatement.setString(2, accountDetails.getType());
            preparedStatement.setString(3, accountDetails.getDepositor());
            preparedStatement.setDouble(4, accountDetails.getAmount());
            preparedStatement.setDouble(5, accountDetails.getProfitability());
            preparedStatement.setInt(6, accountDetails.getConstraintTime());
            resultSecondQuery = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();//logging
        }
        return (resultFirstQuery == 1 && resultSecondQuery == 1);
    }

    @Override
    public BankAccount read(int id) {
        BankAccount bankAccount = null;
        BankAccount.AccountDetails accountDetails = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTBANKACCOUNT.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                bankAccount = new BankAccount();
                bankAccount.setName(resultSet.getString("name"));
                bankAccount.setCountry(resultSet.getString("country"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.SELECTACCOUNTDETAILS.QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                accountDetails = new BankAccount.AccountDetails();
                accountDetails.setAccountID(resultSet.getInt("id"));
                accountDetails.setType(resultSet.getString("type"));
                accountDetails.setDepositor(resultSet.getString("depositor"));
                accountDetails.setAmount(resultSet.getDouble("amount"));
                accountDetails.setProfitability(resultSet.getDouble("profitability"));
                accountDetails.setConstraintTime(resultSet.getInt("constraintTime"));
                bankAccount.setAccountDetails(accountDetails);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bankAccount;
    }

    @Override
    public boolean update(BankAccount bankAccount) {
        BankAccount.AccountDetails accountDetails = bankAccount.getAccountDetails();
        int resultFirstQuery = -1;
        int resultSecondQuery = -1;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.UPDATEBANKACCOUNT.QUERY)){
            preparedStatement.setString(1, bankAccount.getName());
            preparedStatement.setString(2, bankAccount.getCountry());
            preparedStatement.setInt(3, accountDetails.getAccountID());
            resultFirstQuery = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.UPDATEACCOUNTDETAILS.QUERY)){
            preparedStatement.setString(1, accountDetails.getType());
            preparedStatement.setString(2, accountDetails.getDepositor());
            preparedStatement.setDouble(3, accountDetails.getAmount());
            preparedStatement.setDouble(4, accountDetails.getProfitability());
            preparedStatement.setInt(5, accountDetails.getConstraintTime());
            preparedStatement.setInt(6, accountDetails.getAccountID());
            resultSecondQuery = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (resultFirstQuery == 1 && resultSecondQuery == 1);
    }

    @Override
    public boolean delete(BankAccount bankAccount) {
        BankAccount.AccountDetails accountDetails = bankAccount.getAccountDetails();
        int resultFirstQuery = -1;
        int resultSecondQuery = -1;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.DELETEACCOUNTDETAILS.QUERY)){
            preparedStatement.setInt(1, accountDetails.getAccountID());
            preparedStatement.setString(2, accountDetails.getType());
            preparedStatement.setString(3, accountDetails.getDepositor());
            preparedStatement.setDouble(4, accountDetails.getAmount());
            preparedStatement.setDouble(5, accountDetails.getProfitability());
            preparedStatement.setInt(6, accountDetails.getConstraintTime());
            resultSecondQuery = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.DELETEBANKACCOUNT.QUERY)){
            preparedStatement.setString(1, bankAccount.getName());
            preparedStatement.setString(2, bankAccount.getCountry());
            preparedStatement.setInt(3, accountDetails.getAccountID());
            resultFirstQuery = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (resultFirstQuery == 1 && resultSecondQuery == 1);
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
        INSERTBANKACCOUNT("INSERT INTO bankaccounts (name, country, accountDetail_id) VALUES ((?), (?), (?))"),
        INSERTACCOUNTDETAILS("INSERT INTO accountdetails (id, type, depositor, amount, profitability, constraintTime) VALUES ((?), (?), (?), (?), (?), (?))"),
        SELECTBANKACCOUNT("SELECT name, country FROM bankaccounts WHERE accountDetail_id = (?)"),
        SELECTACCOUNTDETAILS("SELECT id, type, depositor, amount, profitability, constraintTime FROM accountdetails WHERE id = (?)"),
        UPDATEBANKACCOUNT("UPDATE bankaccounts SET name = (?), country = (?) WHERE accountDetail_id = (?)"),
        UPDATEACCOUNTDETAILS("UPDATE accountdetails SET type = (?), depositor = (?), amount = (?), profitability = (?), constraintTime = (?) WHERE id = (?)"),
        DELETEBANKACCOUNT("DELETE FROM bankaccounts WHERE name = (?) AND country = (?) AND accountDetail_id = (?)"),
        DELETEACCOUNTDETAILS("DELETE FROM accountdetails WHERE id = (?) AND type = (?) AND depositor = (?) AND amount = (?) AND profitability = (?) AND constraintTime = (?)"),
        SELECTBANKACCOUNTLIST("SELECT * FROM bankaccounts"),
        SELECTACCOUNTDETAILSLIST("SELECT * FROM accountdetails WHERE id = (?)");

        String QUERY;

        SQLQuery(String QUERY){
            this.QUERY = QUERY;
        }
    }

}
