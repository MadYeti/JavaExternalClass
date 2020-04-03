package version2.mvc;


/**
 * Created by MadYeti on 26.03.2020.
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void startProgramm(){
        for(int i = 0; i < 4; i++){
            view.printLoadingShipArrives();
            model.createLoadingShip();
            view.printUnloadingShipArrives();
            model.createUnloadingShip();
        }
    }

}
