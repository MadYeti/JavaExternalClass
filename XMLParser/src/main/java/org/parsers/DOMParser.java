package org.parsers;

import org.bankAccount.Bank;
import org.bankAccount.BankAccount;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by MadYeti on 18.03.2020.
 */
public class DOMParser implements Parser {

    private DocumentBuilderFactory documentBuilderFactory;
    private Document document;
    private DocumentBuilder documentBuilder;
    private String filename = "src/main/resources/account";

    public DOMParser() {
        try {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Bank parseXMLList() {
        try {
            document = documentBuilder.parse(new File(("src/main/resources/bank.xml")));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        Bank bank = new Bank();
        NodeList nodeList = document.getElementsByTagName("bankAccount");
        for (int i = 0; i < nodeList.getLength(); i++) {
            BankAccount bankAccount = new BankAccount();
            BankAccount.AccountDetails accountDetails = new BankAccount.AccountDetails();
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String country = element.getElementsByTagName("country").item(0).getTextContent();
                String type = element.getElementsByTagName("type").item(0).getTextContent();
                String depositor = element.getElementsByTagName("depositor").item(0).getTextContent();
                int accountID = Integer.parseInt(element.getElementsByTagName("accountID").item(0).getTextContent());
                double amount = Double.parseDouble(element.getElementsByTagName("amount").item(0).getTextContent());
                double profitability = Double.parseDouble(element.getElementsByTagName("profitability").item(0).getTextContent());
                int constraintTime = Integer.parseInt(element.getElementsByTagName("constraintTime").item(0).getTextContent());
                bankAccount.setName(name);
                bankAccount.setCountry(country);
                accountDetails.setType(type);
                accountDetails.setDepositor(depositor);
                accountDetails.setAccountID(accountID);
                accountDetails.setAmount(amount);
                accountDetails.setProfitability(profitability);
                accountDetails.setConstraintTime(constraintTime);
            }
            bankAccount.setAccountDetails(accountDetails);
            bank.getBankAccountList().add(bankAccount);
        }
        return bank;
    }

    @Override
    public BankAccount parseXMLElement(int id) {
        try {
            document = documentBuilder.parse(new File(this.filename.concat(String.valueOf(id)).concat(".xml")));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        BankAccount bankAccount = new BankAccount();
        NodeList nodeList = document.getElementsByTagName("bankAccount");
        BankAccount.AccountDetails accountDetails = new BankAccount.AccountDetails();
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            String country = element.getElementsByTagName("country").item(0).getTextContent();
            String type = element.getElementsByTagName("type").item(0).getTextContent();
            String depositor = element.getElementsByTagName("depositor").item(0).getTextContent();
            int accountID = Integer.parseInt(element.getElementsByTagName("accountID").item(0).getTextContent());
            double amount = Double.parseDouble(element.getElementsByTagName("amount").item(0).getTextContent());
            double profitability = Double.parseDouble(element.getElementsByTagName("profitability").item(0).getTextContent());
            int constraintTime = Integer.parseInt(element.getElementsByTagName("constraintTime").item(0).getTextContent());
            bankAccount.setName(name);
            bankAccount.setCountry(country);
            accountDetails.setType(type);
            accountDetails.setDepositor(depositor);
            accountDetails.setAccountID(accountID);
            accountDetails.setProfitability(profitability);
            accountDetails.setAmount(amount);
            accountDetails.setConstraintTime(constraintTime);
            bankAccount.setAccountDetails(accountDetails);
        }
        return bankAccount;
    }

    @Override
    public void createXMLElement(BankAccount bankAccount, int id){
        BankAccount.AccountDetails accountDetails = bankAccount.getAccountDetails();
        document = documentBuilder.newDocument();
        Element root = document.createElement("bank");
        document.appendChild(root);
        Element bankAccountElement = document.createElement("bankAccount");
        root.appendChild(bankAccountElement);
        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(bankAccount.getName()));
        bankAccountElement.appendChild(nameElement);
        Element countryElement = document.createElement("country");
        countryElement.appendChild(document.createTextNode(bankAccount.getCountry()));
        bankAccountElement.appendChild(countryElement);
        Element accountDetailsElement = document.createElement("accountDetails");
        bankAccountElement.appendChild(accountDetailsElement);
        Element typeElement = document.createElement("type");
        typeElement.appendChild(document.createTextNode(accountDetails.getType()));
        accountDetailsElement.appendChild(typeElement);
        Element depositorElement = document.createElement("depositor");
        depositorElement.appendChild(document.createTextNode(accountDetails.getDepositor()));
        accountDetailsElement.appendChild(depositorElement);
        Element accountIDElement = document.createElement("accountID");
        accountIDElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getAccountID())));
        accountDetailsElement.appendChild(accountIDElement);
        Element amountElement = document.createElement("amount");
        amountElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getAmount())));
        accountDetailsElement.appendChild(amountElement);
        Element profitabilityElement = document.createElement("profitability");
        profitabilityElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getProfitability())));
        accountDetailsElement.appendChild(profitabilityElement);
        Element constraintTimeElement = document.createElement("constraintTime");
        constraintTimeElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getConstraintTime())));
        accountDetailsElement.appendChild(constraintTimeElement);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("src/main/resources/account" + id + ".xml"));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createXMLList(List<BankAccount> bankAccountList){
        document = documentBuilder.newDocument();
        Element root = document.createElement("bank");
        document.appendChild(root);
        for(BankAccount bankAccount: bankAccountList){
            BankAccount.AccountDetails accountDetails = bankAccount.getAccountDetails();
            Element bankAccountElement = document.createElement("bankAccount");
            root.appendChild(bankAccountElement);
            Element nameElement = document.createElement("name");
            nameElement.appendChild(document.createTextNode(bankAccount.getName()));
            bankAccountElement.appendChild(nameElement);
            Element countryElement = document.createElement("country");
            countryElement.appendChild(document.createTextNode(bankAccount.getCountry()));
            bankAccountElement.appendChild(countryElement);
            Element accountDetailsElement = document.createElement("accountDetails");
            bankAccountElement.appendChild(accountDetailsElement);
            Element typeElement = document.createElement("type");
            typeElement.appendChild(document.createTextNode(accountDetails.getType()));
            accountDetailsElement.appendChild(typeElement);
            Element depositorElement = document.createElement("depositor");
            depositorElement.appendChild(document.createTextNode(accountDetails.getDepositor()));
            accountDetailsElement.appendChild(depositorElement);
            Element accountIDElement = document.createElement("accountID");
            accountIDElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getAccountID())));
            accountDetailsElement.appendChild(accountIDElement);
            Element amountElement = document.createElement("amount");
            amountElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getAmount())));
            accountDetailsElement.appendChild(amountElement);
            Element profitabilityElement = document.createElement("profitability");
            profitabilityElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getProfitability())));
            accountDetailsElement.appendChild(profitabilityElement);
            Element constraintTimeElement = document.createElement("constraintTime");
            constraintTimeElement.appendChild(document.createTextNode(String.valueOf(accountDetails.getConstraintTime())));
            accountDetailsElement.appendChild(constraintTimeElement);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("src/main/resources/banks.xml"));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
