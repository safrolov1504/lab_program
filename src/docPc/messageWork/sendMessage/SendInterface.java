package docPc.messageWork.sendMessage;

import messageCommons.CommandSecond;
import messageCommons.variosOfMessage.Client;
import messageCommons.variosOfMessage.Lab;
import messageCommons.variosOfMessage.Visit;

import java.util.ArrayList;

public abstract class SendInterface {

    public void lookingForClient(CommandSecond lookingForClient, String toLowerCase, String toLowerCase1, String toLowerCase2, String toLowerCase3){
    }

    public void lookingForClientAll(){
    }

    public void getResult(Client client){
    }

    public void sendAnalysis(String toLowerCase, String toLowerCase1, String toLowerCase2, String toLowerCase3, String toLowerCase4, String toLowerCase5, String toLowerCase6, String toLowerCase7, String toLowerCase8, String toLowerCase9, String toLowerCase10, String toLowerCase11, String toLowerCase12, String toLowerCase13){};

    public void sendRequestAddNewAnalyses(){};

    public void sendRequestAddReadyAnalyses(){};

    public void sendUpdate(ArrayList<Lab> labs){};

    public void checkLogin(String text, String text1){};

    public void addNewUser(String typeUser, String text, String text1, String toLowerCase, String toLowerCase1, String toLowerCase2){};

    public void sendAnalysisAndClient(Visit visit){}
}
