package org.parsers;

import org.bankAccount.Bank;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by MadYeti on 20.03.2020.
 */
public class JAXBParser {

    public JAXBParser(){

    }

    public Bank parseXMLList(){
        Bank bank = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            bank = (Bank)unmarshaller.unmarshal(new File("src/main/resources/bank.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return bank;
    }

}
