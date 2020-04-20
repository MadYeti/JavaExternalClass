package org.parsers;

import org.bankAccount.Bank;
import org.bankAccount.BankAccount;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by MadYeti on 18.03.2020.
 */
public class SaxParser implements Parser {

    private SAXParserFactory saxParserFactory;
    private Handler handler;
    private ElementHandler elementHandler;
    private SAXParser saxParser;
    private String filename = "src/main/resources/account";

    public SaxParser() {
        try {
            saxParserFactory = SAXParserFactory.newInstance();
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    public Bank parseXMLList() {
        handler = new Handler();
        try {
            saxParser.parse(new File("src/main/resources/bank.xml"), handler);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return handler.getBank();
    }

    @Override
    public BankAccount parseXMLElement(int id) {
        elementHandler = new ElementHandler();
        try {
            saxParser.parse(new File(filename.concat(String.valueOf(id)).concat(".xml")), elementHandler);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return elementHandler.getBankAccount();
    }

    @Override
    public void createXMLElement(BankAccount bankAccount, int id) {

    }

    @Override
    public void createXMLList(List<BankAccount> bankAccountList) {

    }

    class Handler extends DefaultHandler {

        private Bank bank = new Bank();
        private BankAccount bankAccount = null;
        private BankAccount.AccountDetails accountDetails = null;
        private boolean isName = false;
        private boolean isCountry = false;
        private boolean isType = false;
        private boolean isDepositor = false;
        private boolean isAccountID = false;
        private boolean isAmount = false;
        private boolean isProfitability = false;
        private boolean isConstraintTime = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {

            if (qName.equals("bankAccount")) {
                bankAccount = new BankAccount();
            } else if (qName.equals("accountDetails")) {
                accountDetails = new BankAccount.AccountDetails();
            } else if (qName.equals("name")) {
                isName = true;
            } else if (qName.equals("country")) {
                isCountry = true;
            } else if (qName.equals("type")) {
                isType = true;
            } else if (qName.equals("depositor")) {
                isDepositor = true;
            } else if (qName.equals("accountID")) {
                isAccountID = true;
            } else if (qName.equals("amount")) {
                isAmount = true;
            } else if (qName.equals("profitability")) {
                isProfitability = true;
            } else if (qName.equals("constraintTime")) {
                isConstraintTime = true;
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("bankAccount")) {
                bank.getBankAccountList().add(bankAccount);
            } else if (qName.equals("accountDetails")) {
                bankAccount.setAccountDetails(accountDetails);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) {

            if (isName) {
                bankAccount.setName(new String(ch, start, length));
                isName = false;
            } else if (isCountry) {
                bankAccount.setCountry(new String(ch, start, length));
                isCountry = false;
            } else if (isType) {
                accountDetails.setType(new String(ch, start, length));
                isType = false;
            } else if (isDepositor) {
                accountDetails.setDepositor(new String(ch, start, length));
                isDepositor = false;
            } else if (isAccountID) {
                accountDetails.setAccountID(Integer.parseInt(new String(ch, start, length)));
                isAccountID = false;
            } else if (isAmount) {
                accountDetails.setAmount(Double.parseDouble(new String(ch, start, length)));
                isAmount = false;
            } else if (isProfitability) {
                accountDetails.setProfitability(Double.parseDouble(new String(ch, start, length)));
                isProfitability = false;
            } else if (isConstraintTime) {
                accountDetails.setConstraintTime(Integer.parseInt(new String(ch, start, length)));
                isConstraintTime = false;
            }

        }

        public Bank getBank() {
            return bank;
        }
    }

    class ElementHandler extends DefaultHandler {

        private BankAccount bankAccount = new BankAccount();
        private BankAccount.AccountDetails accountDetails = null;
        private boolean isName = false;
        private boolean isCountry = false;
        private boolean isType = false;
        private boolean isDepositor = false;
        private boolean isAccountID = false;
        private boolean isAmount = false;
        private boolean isProfitability = false;
        private boolean isConstraintTime = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {

            if (qName.equals("accountDetails")) {
                accountDetails = new BankAccount.AccountDetails();
            } else if (qName.equals("name")) {
                isName = true;
            } else if (qName.equals("country")) {
                isCountry = true;
            } else if (qName.equals("type")) {
                isType = true;
            } else if (qName.equals("depositor")) {
                isDepositor = true;
            } else if (qName.equals("accountID")) {
                isAccountID = true;
            } else if (qName.equals("amount")) {
                isAmount = true;
            } else if (qName.equals("profitability")) {
                isProfitability = true;
            } else if (qName.equals("constraintTime")) {
                isConstraintTime = true;
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("accountDetails")) {
                bankAccount.setAccountDetails(accountDetails);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) {
            if (isName) {
                bankAccount.setName(new String(ch, start, length));
                isName = false;
            } else if (isCountry) {
                bankAccount.setCountry(new String(ch, start, length));
                isCountry = false;
            } else if (isType) {
                accountDetails.setType(new String(ch, start, length));
                isType = false;
            } else if (isDepositor) {
                accountDetails.setDepositor(new String(ch, start, length));
                isDepositor = false;
            } else if (isAccountID) {
                accountDetails.setAccountID(Integer.parseInt(new String(ch, start, length)));
                isAccountID = false;
            } else if (isAmount) {
                accountDetails.setAmount(Double.parseDouble(new String(ch, start, length)));
                isAmount = false;
            } else if (isProfitability) {
                accountDetails.setProfitability(Double.parseDouble(new String(ch, start, length)));
                isProfitability = false;
            } else if (isConstraintTime) {
                accountDetails.setConstraintTime(Integer.parseInt(new String(ch, start, length)));
                isConstraintTime = false;
            }
        }

        public BankAccount getBankAccount() {
            return bankAccount;
        }
    }

}
