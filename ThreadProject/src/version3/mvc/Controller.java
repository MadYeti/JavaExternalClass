package version3.mvc;

import java.util.concurrent.ExecutorService;

/**
 * Created by MadYeti on 29.03.2020.
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void startProgramm(){
        ExecutorService executor = model.getExecutorService();
        for(int i = 0; i < 4; i++){
            executor.submit(() -> {
                view.printLoadingShipArrives();
                model.createLoadingShip();
            });
            executor.submit(() -> {
                view.printUnloadingShipArrives();
                model.createUnloadingShip();
            });
        }
        executor.shutdown();
    }

}
