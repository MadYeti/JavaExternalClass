package battle.mvc;

import battle.droids.Droid;
import battle.factory.DroidFactory;

import java.io.IOException;

public class AdminModel extends Model {

    public AdminModel(){

    }

    public String enterAdminOperation(){
        while(true) {
            try {
                String operation = bufferedReader.readLine();
                if(operation.equals("read") ||
                    operation.equals("add") ||
                    operation.equals("modify") ||
                    operation.equals("delete")){
                    return operation;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int pickNumber() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }

    public String enterDroidName(){
        while(true) {
            try {
                String droidName = bufferedReader.readLine();
                if(droidName.equals("BattleDroid") ||
                        droidName.equals("RepairDroid") ||
                        droidName.equals("TankDroid") ||
                        droidName.equals("SuperBattleDroid")){
                    return droidName;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Droid createDroid(String droidType){
        return DroidFactory.createDroid(droidType);
    }

    public int setIndicator() throws IOException {
        return Integer.parseInt(bufferedReader.readLine());
    }
}
