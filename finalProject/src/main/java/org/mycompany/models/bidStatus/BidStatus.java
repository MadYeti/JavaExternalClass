package org.mycompany.models.bidStatus;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Component
@Scope("prototype")
@XmlAccessorType(XmlAccessType.NONE)
public class BidStatus {

    @XmlElement(name = "id")
    private int id;
    private String bidStatusEN;
    private String bidStatusUA;

    public BidStatus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBidStatusEN() {
        return bidStatusEN;
    }

    public void setBidStatusEN(String bidStatusEN) {
        this.bidStatusEN = bidStatusEN;
    }

    public String getBidStatusUA() {
        return bidStatusUA;
    }

    public void setBidStatusUA(String bidStatusUA) {
        this.bidStatusUA = bidStatusUA;
    }

    @Override
    public String toString() {
        return "BidStatus{" +
                "id=" + id +
                ", bidStatusEN='" + bidStatusEN + '\'' +
                ", bidStatusUA='" + bidStatusUA + '\'' +
                '}';
    }
}
