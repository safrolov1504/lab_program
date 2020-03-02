package doctorPC.messageWorkWith;

import doctorPC.iterfaceDocktor.Controller;
import doctorPC.networkCommunication.IService;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;

public class SendMessage {
    private IService messageService;
    private Controller controller;

    public SendMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
    }

    public void checkLogin(String loginIn, String passwordIn) {
        String login = loginIn;
        String password = passwordIn;

        AuthMessage authMessage = new AuthMessage();
        authMessage.login = login;
        authMessage.password = password;
        Message auth = Message.creatAuth(authMessage);

        messageService.sendMessage(auth.toJson());
    }
}
