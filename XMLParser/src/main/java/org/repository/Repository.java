package org.repository;

import org.bankAccount.BankAccount;

import java.util.List;

/**
 * Created by MadYeti on 09.04.2020.
 */
public interface Repository {

    List<BankAccount> getBankAccountList();

}
