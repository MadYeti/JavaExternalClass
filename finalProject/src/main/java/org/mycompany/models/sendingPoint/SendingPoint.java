package org.mycompany.models.sendingPoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class SendingPoint {

    @XmlElement(name = "id")
    private int id;
    private String sendingPointEN;
    private String sendingPointUA;

    public SendingPoint() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendingPointEN() {
        return sendingPointEN;
    }

    public void setSendingPointEN(String sendingPointEN) {
        this.sendingPointEN = sendingPointEN;
    }

    public String getSendingPointUA() {
        return sendingPointUA;
    }

    public void setSendingPointUA(String sendingPointUA) {
        this.sendingPointUA = sendingPointUA;
    }

    @Override
    public String toString() {
        return "SendingPoint{" +
                "id=" + id +
                ", sendingPointEN='" + sendingPointEN + '\'' +
                ", sendingPointUA='" + sendingPointUA + '\'' +
                '}';
    }
}
