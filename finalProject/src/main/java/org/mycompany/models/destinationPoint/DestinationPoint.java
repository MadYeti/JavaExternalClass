package org.mycompany.models.destinationPoint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Component
@Scope("prototype")
@XmlAccessorType(XmlAccessType.NONE)
public class DestinationPoint {

    @XmlElement(name = "id")
    private int id;
    private String destinationPointEN;
    private String destinationPointUA;

    public DestinationPoint() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinationPointEN() {
        return destinationPointEN;
    }

    public void setDestinationPointEN(String destinationPointEN) {
        this.destinationPointEN = destinationPointEN;
    }

    public String getDestinationPointUA() {
        return destinationPointUA;
    }

    public void setDestinationPointUA(String destinationPointUA) {
        this.destinationPointUA = destinationPointUA;
    }

    @Override
    public String toString() {
        return "DestinationPoint{" +
                "id=" + id +
                ", destinationPointEN='" + destinationPointEN + '\'' +
                ", destinationPointUA='" + destinationPointUA + '\'' +
                '}';
    }
}
