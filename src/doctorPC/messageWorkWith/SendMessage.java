package doctorPC.messageWorkWith;

import doctorPC.iterfaceDocktor.Controller;
import doctorPC.networkCommunication.IService;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;

public class SendMessage {
    private IService messageService;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public SendMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
    }

    public void checkLogin(String login, String password) {
        AuthMessage authMessage = new AuthMessage();
        authMessage.login = login;
        authMessage.password = password;
        Message auth = Message.creatAuth(authMessage);

        messageService.sendMessage(auth.toJson());
    }

    public void addNewUser(String role, String login, String password, String firstName, String secondName, String profession) {
        AuthMessage authMessage = new AuthMessage();
        authMessage.login = login;
        authMessage.password = password;
        authMessage.firstName = firstName;
        authMessage.secondName = secondName;
        authMessage.role = role;
        authMessage.profession = profession;

        Message message = Message.creatNewUser(authMessage);
        messageService.sendMessage(message.toJson());
    }
}
