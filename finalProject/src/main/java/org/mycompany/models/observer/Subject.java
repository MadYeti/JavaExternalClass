package org.mycompany.models.observer;


import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class Subject extends Observable{

    private int id;

    public Subject(){

    }

    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(int id){
        this.id = id;
        measurementsChanged();
    }

    public int getId() {
        return id;
    }
}
