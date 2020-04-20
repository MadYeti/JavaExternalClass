package org.parsers;

import org.bankAccount.Bank;
import org.bankAccount.BankAccount;

import java.util.List;

/**
 * Created by MadYeti on 20.03.2020.
 */
public interface Parser {

    void createXMLElement(BankAccount bankAccount, int id);
    void createXMLList(List<BankAccount> bankAccountList);
    BankAccount parseXMLElement(int id);


}
