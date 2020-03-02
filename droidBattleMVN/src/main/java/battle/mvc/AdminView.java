package battle.mvc;

public class AdminView extends View {

    public static final String INPUT_OPERATIONS_TO_DO = "input.operations.todo";
    public static final String INPUT_READ_OPERATION = "input.number.read.droid";
    public static final String INPUT_ADD_OPERATION = "input.droid.name.add";
    public static final String INPUT_ADD_OPERATION_NUMBER = "input.droid.number.add";
    public static final String INPUT_MODIFY_OPERATION = "input.droid.number.modify";
    public static final String INPUT_DELETE_OPERATION = "input.droid.number.delete";
    public static final String INCORRECT_NUMBER = "wrong.droid.number";
    public static final String DROID_NAME = "droid.name";
    public static final String DROID_ATTACK = "droid.attack";
    public static final String DROID_HEALTH = "droid.health";
    public static final String DROID_ARMOR = "droid.armor";
    public static final String SET_DROID_ATTACK = "set.droid.attack";
    public static final String SET_DROID_HEALTH = "set.droid.health";
    public static final String SET_DROID_ARMOR = "set.droid.armor";
    public static final String NULL_DROID = "null.droid";
    public static final String DELETE_DROID = "delete.droid.success";
    public static final String NOT_A_NUMBER = "wrong.number.input";

    public AdminView(){

    }

    public void printIncorrectNumber(){
        printMessage(resourceBundle.getString(INCORRECT_NUMBER));
    }

    public void printDeleteDroid(){
        printMessage(resourceBundle.getString(DELETE_DROID));
    }

    public void printNotANumber(){
        printMessage(resourceBundle.getString(NOT_A_NUMBER));
    }

    public void printSetDroidAttack(){
        printMessage(resourceBundle.getString(SET_DROID_ATTACK));
    }

    public void printSetDroidArmor(){
        printMessage(resourceBundle.getString(SET_DROID_ARMOR));
    }

    public void printSetDroidHealth(){
        printMessage(resourceBundle.getString(SET_DROID_HEALTH));
    }

    public void printNullDroid(){
        printMessage(resourceBundle.getString(NULL_DROID));
    }

    public void printOperationsToDo(){
        printMessage(resourceBundle.getString(INPUT_OPERATIONS_TO_DO));
    }

    public void printReadOperation(){
        printMessage(resourceBundle.getString(INPUT_READ_OPERATION));
    }

    public void printAddOperationNumber(){
        printMessage(resourceBundle.getString(INPUT_ADD_OPERATION_NUMBER));
    }

    public void printAddOperation(){
        printMessage(resourceBundle.getString(INPUT_ADD_OPERATION));
    }

    public void printModifyOperation(){
        printMessage(resourceBundle.getString(INPUT_MODIFY_OPERATION));
    }

    public void printDeleteOperation(){
        printMessage(resourceBundle.getString(INPUT_DELETE_OPERATION));
    }

    public void printDroidIndicators(String name, int attack, int health, int armor){
        printMessage(resourceBundle.getString(DROID_NAME) + name + "\n" +
                     resourceBundle.getString(DROID_ATTACK) + attack + "\n" +
                     resourceBundle.getString(DROID_HEALTH) + health + "\n" +
                     resourceBundle.getString(DROID_ARMOR) + armor + "\n");
    }

}
