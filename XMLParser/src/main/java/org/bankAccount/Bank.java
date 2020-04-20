package org.bankAccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadYeti on 19.03.2020.
 */
@XmlRootElement(name = "bank")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bank {

    @XmlElement(name = "bankAccount")
    private List<BankAccount> bankAccountList = new ArrayList<>();

    public Bank(){

    }

    public Bank(List<BankAccount> bankAccountList){
        this.bankAccountList = bankAccountList;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankAccountList=" + bankAccountList +
                '}';
    }
}
