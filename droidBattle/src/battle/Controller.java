package battle;

/**
 * Created by MadYeti on 18.02.2020.
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startBattle(){
        model.startFirstRound();
        view.printWinnerOfTheFirstRound(model.getWinnerOfTheFirstRound());
        model.startSecondRound();
        view.printWinnerOfTheSecondRound(model.getWinnerOfTheSecondRound());
        model.startFinalRound();
        view.printWinnerOfTheBattle(model.getWinnerOfTheBattle());
    }

}
