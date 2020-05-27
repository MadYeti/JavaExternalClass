package org.mycompany.controllers.xmlParser;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mycompany.models.bid.Bid;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class JAXBParser {

    private static Logger logger = Logger.getLogger(JAXBParser.class);

    static{
        PropertyConfigurator.configure("src/main/resources/logConfig.properties");
    }

    public JAXBParser(){

    }

    public void creteXMLBasedOnObject(Bid bid){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bid.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(bid, new File("src/main/resources/bid".concat(String.valueOf(bid.getId())).concat(".xml")));
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    public Bid createObjectBasedOnXML(int id){
        Bid bid = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bid.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            bid = (Bid) unmarshaller.unmarshal(new File("src/main/resources/bid".concat(String.valueOf(id)).concat(".xml")));
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
        return bid;
    }

}
