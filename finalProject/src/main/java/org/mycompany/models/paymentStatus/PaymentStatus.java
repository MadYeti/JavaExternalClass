package org.mycompany.models.paymentStatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Model class for payment status object. Represents by payment_status table in DB
 */
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentStatus {

    @XmlElement(name = "id")
    private int id;
    private String paymentStatusEN;
    private String paymentStatusUA;

    public PaymentStatus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentStatusEN() {
        return paymentStatusEN;
    }

    public void setPaymentStatusEN(String paymentStatusEN) {
        this.paymentStatusEN = paymentStatusEN;
    }

    public String getPaymentStatusUA() {
        return paymentStatusUA;
    }

    public void setPaymentStatusUA(String paymentStatusUA) {
        this.paymentStatusUA = paymentStatusUA;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "id=" + id +
                ", paymentStatusEN='" + paymentStatusEN + '\'' +
                ", paymentStatusUA='" + paymentStatusUA + '\'' +
                '}';
    }
}
