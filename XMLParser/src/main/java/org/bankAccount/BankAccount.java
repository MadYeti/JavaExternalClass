package org.bankAccount;

import javax.xml.bind.annotation.*;

/**
 * Created by MadYeti on 18.03.2020.
 */
@XmlRootElement(name = "bankAccount")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bankAccount", propOrder = {"name", "country", "accountDetails"})
public class BankAccount {

    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "country", required = true)
    private String country;
    @XmlElement(name = "accountDetails", required = true)
    private AccountDetails accountDetails;

    public BankAccount(){

    }

    public BankAccount(String name, String country, AccountDetails accountDetails){
        this.name = name;
        this.country = country;
        this.accountDetails = accountDetails;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", accountDetails=" + accountDetails +
                '}';
    }

    @XmlRootElement(name = "accountDetails")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AccountDetails {

        @XmlElement(name = "type", required = true)
        private String type;
        @XmlElement(name = "depositor", required = true)
        private String depositor;
        @XmlElement(name = "accountID", required = true)
        private int accountID;
        @XmlElement(name = "amount", required = true)
        private double amount;
        @XmlElement(name = "profitability", required = true)
        private double profitability;
        @XmlElement(name = "constraintTime", required = true)
        private int constraintTime;

        public AccountDetails(){

        }

        public AccountDetails(String type, String depositor, int accountID, double amount, double profitability, int constraintTime){
            this.type = type;
            this.depositor = depositor;
            this.accountID = accountID;
            this.amount = amount;
            this.profitability = profitability;
            this.constraintTime = constraintTime;
        }

        public String getType() {
            return type;
        }

        public String getDepositor() {
            return depositor;
        }

        public int getAccountID() {
            return accountID;
        }

        public double getAmount() {
            return amount;
        }

        public double getProfitability() {
            return profitability;
        }

        public int getConstraintTime() {
            return constraintTime;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDepositor(String depositor) {
            this.depositor = depositor;
        }

        public void setAccountID(int accountID) {
            this.accountID = accountID;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setProfitability(double profitability) {
            this.profitability = profitability;
        }

        public void setConstraintTime(int constraintTime) {
            this.constraintTime = constraintTime;
        }

        @Override
        public String toString() {
            return "AccountDetails{" +
                    "type='" + type + '\'' +
                    ", depositor='" + depositor + '\'' +
                    ", accountID=" + accountID +
                    ", amount=" + amount +
                    ", profitability=" + profitability +
                    ", constraintTime=" + constraintTime +
                    '}';
        }
    }

}
