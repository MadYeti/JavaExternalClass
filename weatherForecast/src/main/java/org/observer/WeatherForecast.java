package org.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class WeatherForecast implements Observer, DispalyData{

    private Observable observable;
    private boolean isBecomeHotter = false;
    private boolean isBecomeCooler = false;
    private boolean isWeatherWontChange = false;

    public WeatherForecast(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Subject subject = (Subject) o;
        int arrayListSize = subject.getTempList().size();
        if(arrayListSize == 1){
            isWeatherWontChange = true;
        }else if(subject.getTempList().get(arrayListSize - 1) > subject.getTempList().get(arrayListSize - 2)){
            isBecomeHotter = true;
        }else if(subject.getTempList().get(arrayListSize - 1) < subject.getTempList().get(arrayListSize - 2)){
            isBecomeCooler = true;
        }else{
            isWeatherWontChange = true;
        }
        display();
    }

    @Override
    public void display() {
        if(isBecomeHotter){
            System.out.println("Weather becomes warmer");
        }else if(isBecomeCooler){
            System.out.println("Weather becomes cooler");
        }else{
            System.out.println("Weather will not change");
        }
    }
}
