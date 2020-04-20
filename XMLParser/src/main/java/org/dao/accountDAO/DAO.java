package org.dao.accountDAO;

import org.bankAccount.BankAccount;

/**
 * Created by MadYeti on 23.03.2020.
 */
public interface DAO {

    boolean create(BankAccount bankAccount);
    BankAccount read(int id);
    boolean update(BankAccount bankAccount);
    boolean delete(BankAccount bankAccount);

}
