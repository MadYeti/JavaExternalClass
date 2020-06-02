package org.mycompany.models.paymentStatus;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PaymentStatus {

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
