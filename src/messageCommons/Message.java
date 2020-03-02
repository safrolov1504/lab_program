package messageCommons;

import com.google.gson.Gson;
import messageCommons.variosOfMessage.*;

import java.util.List;

public class Message {
    public Command command;


    public AuthMessage authMessage;
    public NewUser newUser;

    public String toJson() {
        return new Gson().toJson(this);
    }
    public static Message fromJson(String json){
        return new Gson().fromJson(json, Message.class);
    }

    private static Message create(Command cmd) {
        Message m = new Message();
        m.command=cmd;
        return m;
    }


    public static Message creatAuth(AuthMessage msg){
        Message m = create(Command.AUTH_MESSAGE);
        m.authMessage = msg;
        return m;
    }

    public static Message creatAuthOk(AuthMessage msg){
        Message m = create(Command.AUTH_OK);
        m.authMessage = msg;
        return m;
    }

    public static Message creatAuthNOk(AuthMessage msg){
        Message m = create(Command.AUTH_NOK);
        m.authMessage = msg;
        return m;
    }


    public static Message creatNewUser(Command command, NewUser nu){
        Message m = create(command);
        m.newUser = nu;
        return m;
    }

//    public static Message creatNewUserOk(Command command, ChangeNickMessage cnn){
//        Message m = create(command);
//        m.changeNickMessage = cnn;
//        return m;
//    }
}
