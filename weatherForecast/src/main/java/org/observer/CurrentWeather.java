package org.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class CurrentWeather implements Observer, DispalyData{

    private Observable observable;
    private double temperature;
    private double humidity;
    private double pressure;

    public CurrentWeather(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Subject subject = (Subject) o;
        this.temperature = subject.getTemperature();
        this.humidity = subject.getHumidity();
        this.pressure = subject.getPressure();
        display();
    }

    @Override
    public void display() {
        System.out.println("Current weather:" + " temp " + temperature +
                                                " C humidity " + humidity +
                                                " % pressure " + pressure + " hpa");
    }
}
