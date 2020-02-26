package battle.mvc;

import battle.droids.Droid;

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

    public Droid getDroidByNumber(int number){
        return fighters[number];
    }
}
