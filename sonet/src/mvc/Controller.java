package mvc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by MadYeti on 03.03.2020.
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void countWordsViaURL(){
        model.initSonetArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            model.getWordsAmount(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.printMap(model.getCountWordMap());
    }

}
