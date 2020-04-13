package messageCommons;

import com.google.gson.Gson;
import messageCommons.variosOfMessage.*;

import javax.swing.plaf.PanelUI;

public class Message {
    public CommandFirst commandFirst;
    public CommandSecond commandSecond;


    public AuthMessage authMessage;
    public Client client;
    public Visit visit;
    public Lab lab;

    public static Message creatEnd(){
        Message m = create(CommandFirst.END,CommandSecond.END);
        return m;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Message fromJson(String json){
        return new Gson().fromJson(json, Message.class);
    }

    private static Message create(CommandFirst cmd1, CommandSecond cmd2) {
        Message m = new Message();
        m.commandFirst=cmd1;
        m.commandSecond=cmd2;
        return m;
    }

    //message for authentication
    public static Message creatAuth(AuthMessage msg){
        Message m = create(CommandFirst.LOGIN, CommandSecond.AUTH_MESSAGE);
        m.authMessage = msg;
        return m;
    }
    public static Message creatAuthOk(AuthMessage msg){
        Message m = create(CommandFirst.LOGIN,CommandSecond.AUTH_OK);
        m.authMessage = msg;
        return m;
    }
    public static Message creatAuthNOk(AuthMessage msg){
        Message m = create(CommandFirst.LOGIN, CommandSecond.AUTH_NOK);
        m.authMessage = msg;
        return m;
    }

    //message for adding a new user
    public static Message creatNewUser(AuthMessage msg){
        Message m = create(CommandFirst.SERVICE, CommandSecond.ADD_NEW);
        m.authMessage = msg;
        return m;
    }
    public static Message creatNewUserOk(AuthMessage msg){
        Message m = create(CommandFirst.SERVICE, CommandSecond.ADD_NEW_OK);
        m.authMessage = msg;
        return m;
    }
    public static Message creatNewUserNok(AuthMessage msg){
        Message m = create(CommandFirst.SERVICE, CommandSecond.ADD_NEW_NOK);
        m.authMessage = msg;
        return m;
    }

    //messages for looking clients
    public static Message creatLookingClient(Client client){
        Message m = create(CommandFirst.DOC,CommandSecond.LOOKING_FOR_CLIENT);
        m.client = client;
        return m;
    }
    public static Message creatLookingClientALL(Client client){
        Message m = create(CommandFirst.DOC,CommandSecond.LOOKING_FOR_ALL);
        m.client = client;
        return m;
    }
    public static Message creatLookingClientOk(Client client){
        Message m = create(CommandFirst.DOC,CommandSecond.LOOKING_FOR_CLIENT_OK);
        m.client = client;
        return m;
    }
    public static Message creatLookingClientNok(Client client){
        Message m = create(CommandFirst.DOC,CommandSecond.LOOKING_FOR_CLIENT_NOK);
        m.client = client;
        return m;
    }


    public static Message creatAddClient(Client client){
        Message m = create(CommandFirst.DOC,CommandSecond.ADD_NEW_CLIENT);
        m.client = client;
        return m;
    }

    public static Message creatAddClientOk(Client client){
        Message m = create(CommandFirst.DOC,CommandSecond.ADD_CLIENT_OK);
        m.client = client;
        return m;
    }

    public static Message creatAddClientNOk(Client client){
        Message m = create(CommandFirst.DOC,CommandSecond.ADD_CLIENT_NOK);
        m.client = client;
        return m;
    }

    public static Message creatVisit(Visit visit){
        Message m = create(CommandFirst.ANALYSE,CommandSecond.VISIT);
        m.visit = visit;
        return m;
    }

    public static Message creatVisitOK(Visit visit){
        Message m = create(CommandFirst.ANALYSE,CommandSecond.VISIT_OK);
        m.visit = visit;
        return m;
    }

    public static Message creatVisitNok(Visit visit){
        Message m = create(CommandFirst.ANALYSE,CommandSecond.VISIT_NOK);
        m.visit = visit;
        return m;
    }

    public static Message creatVisitADDNew(Visit visit){
        Message m = create(CommandFirst.ANALYSE,CommandSecond.VISIT_ADDNEW);
        m.visit = visit;
        return m;
    }

    public static Message creatResult (Client client){
        Message m = create(CommandFirst.RESULT, CommandSecond.RESULT);
        m.client = client;
        return m;
    }

    public static Message creatResultOk (Visit visit){
        Message m = create(CommandFirst.RESULT, CommandSecond.RESULT_OK);
        m.visit = visit;
        return m;
    }

    public static Message creatResultNOk (Visit visit){
        Message m = create(CommandFirst.RESULT, CommandSecond.RESULT_NOK);
        m.visit = visit;
        return m;
    }

    public static Message creatLabRequest(Lab lab){
        Message m = create(CommandFirst.LAB, CommandSecond.LAB_SEND);
        m.lab = lab;
        return m;
    }

    public static Message creatLabNoAnalyses(Lab lab){
        Message m = create(CommandFirst.LAB, CommandSecond.LAB_NO_ANALYSES);
        m.lab = lab;
        return m;
    }

    public static Message creatLabSendAnalyses(Lab lab){
        Message m = create(CommandFirst.LAB, CommandSecond.LAB_SEND);
        m.lab = lab;
        return m;
    }

    public static Message creatLabReadyAnalyses(Lab lab){
        Message m = create(CommandFirst.LAB, CommandSecond.LAB_READY);
        m.lab = lab;
        return m;
    }

    public static Message creatLabUpdate(Lab lab){
        Message m = create(CommandFirst.LAB, CommandSecond.LAB_UPDATE);
        m.lab = lab;
        return m;
    }

}
