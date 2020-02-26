package battle.mvc;

public class AdminView extends View {

    public static final String INPUT_OPERATIONS_TO_DO = "Enter operation you want to do:\n - read;\n - add;\n - modify;\n - delete;";
    public static final String INPUT_READ_OPERATION = "Enter droid number to read it's indicators:";
    public static final String INPUT_ADD_OPERATION = "Enter droid number to add droid:";
    public static final String INPUT_MODIFY_OPERATION = "Enter droid number to modify droids indicators:";
    public static final String INPUT_DELETE_OPERATION = "Enter droid number to delete droid:";
    public static final String INCORRECT_NUMBER = "Incorrect droid number, pick number from 1 to 4:";
    public static final String DROID_NAME = "Droid name: ";
    public static final String DROID_ATTACK = "Droid attack: ";
    public static final String DROID_HEALTH = "Droid health: ";
    public static final String DROID_ARMOR = "Droid armor: ";

    public AdminView(){

    }

    public void printIncorrectNumber(){
        printMessage(INCORRECT_NUMBER);
    }

    public void printOperationsToDo(){
        printMessage(INPUT_OPERATIONS_TO_DO);
    }

    public void printReadOperation(){
        printMessage(INPUT_READ_OPERATION);
    }

    public void printAddOperation(){
        printMessage(INPUT_ADD_OPERATION);
    }

    public void printModifyOperation(){
        printMessage(INPUT_MODIFY_OPERATION);
    }

    public void printDeleteOperation(){
        printMessage(INPUT_DELETE_OPERATION);
    }

    public void printDroidIndicators(String name, int attack, int health, int armor){
        printMessage(DROID_NAME + name + "\n" +
                     DROID_ATTACK + attack + "\n" +
                     DROID_HEALTH + health + "\n" +
                     DROID_ARMOR + armor + "\n");
    }

}
