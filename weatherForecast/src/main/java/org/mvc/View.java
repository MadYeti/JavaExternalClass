package org.mvc;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class View{

    public View(){

    }

    public void printWelcomeMessage(){
        System.out.println("Print \"weather\" to get weather forecast or \"exit\" to close the app");
    }

    public void printInvalidInput(){
        System.out.println("Invalid input. Repeat your try");
    }

}
