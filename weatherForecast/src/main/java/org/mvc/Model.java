package org.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.observer.CurrentWeather;
import org.observer.StatisticData;
import org.observer.Subject;
import org.observer.WeatherForecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class Model {

    private String location = "Rome";
    private String APIKey = "7b516cdfe52dc22c64eea05abde93e2e";
    private String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + location + ",it&appid=" + APIKey;
    private StringBuilder stringBuilder = new StringBuilder();
    private ObjectMapper objectMapper = new ObjectMapper();
    private Subject subject;
    private URLConnection urlConnection;

    public Model(){
         subject = new Subject();
         try {
             URL url = new URL(urlString);
             urlConnection = url.openConnection();
             urlConnection.setUseCaches(false);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public void getResponse(){
        try {
            urlConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDataFromJSON(){
        try {
            JsonNode root = objectMapper.readTree(stringBuilder.toString());
            JsonNode nameNode = root.path("main");
            if (!nameNode.isMissingNode()) {
                double temp = nameNode.path("temp").asDouble() - 273.0;
                double humidity = nameNode.path("humidity").asDouble();
                double pressure = nameNode.path("pressure").asDouble();
                subject.getTempList().add(temp);
                subject.getHumididtyList().add(humidity);
                subject.getPressureList().add(pressure);
                subject.setMeasurements(temp, humidity, pressure);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void addObservators(){
        CurrentWeather currentWeather = new CurrentWeather(subject);
        StatisticData statisticData = new StatisticData(subject);
        WeatherForecast weatherForecast = new WeatherForecast(subject);
    }

}
