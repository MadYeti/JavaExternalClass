package battle.mvc;

public class AdminView extends View {

    public static final String INPUT_OPERATIONS_TO_DO = "Enter operation you want to do:\n - read;\n - add;\n - modify;\n - delete;";

    public AdminView(){

    }

    public void printOperationsToDo(){
        printMessage(INPUT_OPERATIONS_TO_DO);
    }

}
