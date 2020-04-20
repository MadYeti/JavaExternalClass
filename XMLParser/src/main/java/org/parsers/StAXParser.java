package org.parsers;

import org.bankAccount.Bank;
import org.bankAccount.BankAccount;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.List;

/**
 * Created by MadYeti on 18.03.2020.
 */
public class StAXParser implements Parser {

    private boolean isName = false;
    private boolean isCountry = false;
    private boolean isType = false;
    private boolean isDepositor = false;
    private boolean isAccountID = false;
    private boolean isAmount = false;
    private boolean isProfitability = false;
    private boolean isConstraintTime = false;
    private XMLInputFactory xmlInputFactory;
    private String name;
    private String country;
    private String type;
    private String depositor;
    private int accountID;
    private double amount;
    private double profitability;
    private int constraintTime;
    private String filename = "src/main/resources/account";

    public StAXParser() {
        xmlInputFactory = XMLInputFactory.newInstance();
    }

    public Bank parseXMLList() {
        Bank bank = new Bank();
        BankAccount bankAccount = null;
        BankAccount.AccountDetails accountDetails = null;
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileReader("src/main/resources/bank.xml"));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                switch (xmlEvent.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = xmlEvent.asStartElement();
                        String qName = startElement.getName().getLocalPart();
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
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = xmlEvent.asCharacters();
                        if (isName) {
                            bankAccount.setName(characters.getData());
                            isName = false;
                        } else if (isCountry) {
                            bankAccount.setCountry(characters.getData());
                            isCountry = false;
                        } else if (isType) {
                            accountDetails.setType(characters.getData());
                            isType = false;
                        } else if (isDepositor) {
                            accountDetails.setDepositor(characters.getData());
                            isDepositor = false;
                        } else if (isAccountID) {
                            accountDetails.setAccountID(Integer.parseInt(characters.getData()));
                            isAccountID = false;
                        } else if (isAmount) {
                            accountDetails.setAmount(Double.parseDouble(characters.getData()));
                            isAmount = false;
                        } else if (isProfitability) {
                            accountDetails.setProfitability(Double.parseDouble(characters.getData()));
                            isProfitability = false;
                        } else if (isConstraintTime) {
                            accountDetails.setConstraintTime(Integer.parseInt(characters.getData()));
                            isConstraintTime = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = xmlEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("bankAccount")) {
                            bank.getBankAccountList().add(bankAccount);
                        } else if (endElement.getName().getLocalPart().equals("accountDetails")) {
                            bankAccount.setAccountDetails(accountDetails);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return bank;
    }

    @Override
    public BankAccount parseXMLElement(int id) {
        BankAccount bankAccount = new BankAccount();
        BankAccount.AccountDetails accountDetails = null;
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileReader(filename.concat(String.valueOf(id)).concat(".xml")));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                switch (xmlEvent.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = xmlEvent.asStartElement();
                        String qName = startElement.getName().getLocalPart();
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
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = xmlEvent.asCharacters();
                        if (isName) {
                            bankAccount.setName(characters.getData());
                            isName = false;
                        } else if (isCountry) {
                            bankAccount.setCountry(characters.getData());
                            isCountry = false;
                        } else if (isType) {
                            accountDetails.setType(characters.getData());
                            isType = false;
                        } else if (isDepositor) {
                            accountDetails.setDepositor(characters.getData());
                            isDepositor = false;
                        } else if (isAccountID) {
                            accountDetails.setAccountID(Integer.parseInt(characters.getData()));
                            isAccountID = false;
                        } else if (isAmount) {
                            accountDetails.setAmount(Double.parseDouble(characters.getData()));
                            isAmount = false;
                        } else if (isProfitability) {
                            accountDetails.setProfitability(Double.parseDouble(characters.getData()));
                            isProfitability = false;
                        } else if (isConstraintTime) {
                            accountDetails.setConstraintTime(Integer.parseInt(characters.getData()));
                            isConstraintTime = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = xmlEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("accountDetails")) {
                            bankAccount.setAccountDetails(accountDetails);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return bankAccount;
    }

    @Override
    public void createXMLElement(BankAccount bankAccount, int id){
        BankAccount.AccountDetails accountDetails = bankAccount.getAccountDetails();
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xMLStreamWriter;
        try {
            xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("bank");
            xMLStreamWriter.writeStartElement("bankAccount");
            xMLStreamWriter.writeStartElement("name");
            xMLStreamWriter.writeCharacters(bankAccount.getName());
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("country");
            xMLStreamWriter.writeCharacters(bankAccount.getCountry());
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("accountDetails");
            xMLStreamWriter.writeStartElement("type");
            xMLStreamWriter.writeCharacters(accountDetails.getType());
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("depositor");
            xMLStreamWriter.writeCharacters(accountDetails.getDepositor());
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("accountID");
            xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getAccountID()));
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("amount");
            xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getAmount()));
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("profitability");
            xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getProfitability()));
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("constraintTime");
            xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getConstraintTime()));
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndDocument();
            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filename.concat(String.valueOf(id)).concat(".xml"))));
            bufferedWriter.write(stringWriter.getBuffer().toString(), 0, stringWriter.getBuffer().toString().length());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createXMLList(List<BankAccount> bankAccountList){
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xMLStreamWriter;
        try {
            xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("bank");
            for(BankAccount bankAccount: bankAccountList){
                BankAccount.AccountDetails accountDetails = bankAccount.getAccountDetails();
                xMLStreamWriter.writeStartElement("bankAccount");
                xMLStreamWriter.writeStartElement("name");
                xMLStreamWriter.writeCharacters(bankAccount.getName());
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeStartElement("country");
                xMLStreamWriter.writeCharacters(bankAccount.getCountry());
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeStartElement("accountDetails");
                xMLStreamWriter.writeStartElement("type");
                xMLStreamWriter.writeCharacters(accountDetails.getType());
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeStartElement("depositor");
                xMLStreamWriter.writeCharacters(accountDetails.getDepositor());
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeStartElement("accountID");
                xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getAccountID()));
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeStartElement("amount");
                xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getAmount()));
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeStartElement("profitability");
                xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getProfitability()));
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeStartElement("constraintTime");
                xMLStreamWriter.writeCharacters(String.valueOf(accountDetails.getConstraintTime()));
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeEndElement();
            }
            xMLStreamWriter.writeEndDocument();
            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("src/main/resources/banks.xml")));
            bufferedWriter.write(stringWriter.getBuffer().toString(), 0, stringWriter.getBuffer().toString().length());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

    }

}
