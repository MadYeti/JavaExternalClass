package org.mvc;

/**
 * Created by MadYeti on 07.03.2020.
 */
public class Main {

    public static void main(String[] args) {

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(model, view);
        controller.readData();

    }

}
