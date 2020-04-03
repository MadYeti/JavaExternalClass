package version1.mvc;

/**
 * Created by MadYeti on 26.03.2020.
 */
public class View {

    private static final String LOADING_SHIP_ARRIVES = "Loading ship came to doc";
    private static final String UNLOADING_SHIP_ARRIVES = "Unloading ship came to doc";

    public View(){

    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printLoadingShipArrives(){
        printMessage(LOADING_SHIP_ARRIVES);
    }

    public void printUnloadingShipArrives(){
        printMessage(UNLOADING_SHIP_ARRIVES);
    }

}
