package org.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void readData(){
        model.addObservators();
        view.printWelcomeMessage();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while(!(line = bufferedReader.readLine()).equals("exit")){
                model.getResponse();
                if(line.equals("weather")) {
                    model.getDataFromJSON();
                }else{
                    view.printInvalidInput();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
