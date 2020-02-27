package battle.mvc;

public class AdminView extends View {

    public static final String INPUT_OPERATIONS_TO_DO = "Enter operation you want to do:\n - read;\n - add;\n - modify;\n - delete;";
    public static final String INPUT_READ_OPERATION = "Enter droid number to read it's indicators:";
    public static final String INPUT_ADD_OPERATION = "Enter droid name to add:\n - BattleDroid;\n - RepairDroid;\n - TankDroid;\n - SuperBattleDroid;";
    public static final String INPUT_ADD_OPERATION_NUMBER = "Enter droid number to replace droid:";
    public static final String INPUT_MODIFY_OPERATION = "Enter droid number to modify droids indicators:";
    public static final String INPUT_DELETE_OPERATION = "Enter droid number to delete droid:";
    public static final String INCORRECT_NUMBER = "Incorrect droid number, pick number from 1 to 4:";
    public static final String DROID_NAME = "Droid name: ";
    public static final String DROID_ATTACK = "Droid attack: ";
    public static final String DROID_HEALTH = "Droid health: ";
    public static final String DROID_ARMOR = "Droid armor: ";
    public static final String SET_DROID_ATTACK = "Set droid attack: ";
    public static final String SET_DROID_HEALTH = "Set droid health: ";
    public static final String SET_DROID_ARMOR = "Set droid armor: ";
    public static final String NULL_DROID = "There is no droid with this index";
    public static final String DELETE_DROID = "Droid has been deleted";
    public static final String NOT_A_NUMBER = "Not a number. Indicator set to 10 by default";

    public AdminView(){

    }

    public void printIncorrectNumber(){
        printMessage(INCORRECT_NUMBER);
    }

    public void printDeleteDroid(){
        printMessage(DELETE_DROID);
    }

    public void printNotANumber(){
        printMessage(NOT_A_NUMBER);
    }

    public void printSetDroidAttack(){
        printMessage(SET_DROID_ATTACK);
    }

    public void printSetDroidArmor(){
        printMessage(SET_DROID_ARMOR);
    }

    public void printSetDroidHealth(){
        printMessage(SET_DROID_HEALTH);
    }

    public void printNullDroid(){
        printMessage(NULL_DROID);
    }

    public void printOperationsToDo(){
        printMessage(INPUT_OPERATIONS_TO_DO);
    }

    public void printReadOperation(){
        printMessage(INPUT_READ_OPERATION);
    }

    public void printAddOperationNumber(){
        printMessage(INPUT_ADD_OPERATION_NUMBER);
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
