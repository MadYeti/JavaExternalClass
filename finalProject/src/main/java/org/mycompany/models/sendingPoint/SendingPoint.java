package org.mycompany.models.sendingPoint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SendingPoint {

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
