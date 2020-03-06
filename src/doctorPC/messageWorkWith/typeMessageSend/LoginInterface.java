package doctorPC.messageWorkWith.typeMessageSend;

import doctorPC.networkCommunication.IService;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;

public class LoginInterface {

    private IService messageService;

    public LoginInterface(IService messageService) {
        this.messageService = messageService;
    }

    public void checkLogin(String login, String password) {
        AuthMessage authMessage = new AuthMessage();
        authMessage.login = login;
        authMessage.password = password;
        Message auth = Message.creatAuth(authMessage);

        messageService.sendMessage(auth.toJson());
    }
}
