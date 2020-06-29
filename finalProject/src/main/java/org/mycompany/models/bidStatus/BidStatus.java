package org.mycompany.models.bidStatus;

import javax.xml.bind.annotation.*;

/**
 * Model class for bid status object. Represents by bid_status table in DB
 */
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
