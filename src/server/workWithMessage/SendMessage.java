package server.workWithMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import server.serverWork.ClientHandler;
import server.workWithSQL.User;

public class SendMessage {
    private ClientHandler clientHandler;

    public SendMessage(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void sendAuthor(User checkLogin) {
        AuthMessage authMessage = new AuthMessage();
        Message message;
        if(checkLogin.getFirstName().equals("")){
            authMessage.message = "Wrong login or password";
            message = Message.creatAuthNOk(authMessage);
        } else {
            authMessage.firstName = checkLogin.getFirstName();
            authMessage.secondName = checkLogin.getSecondName();
            message = Message.creatAuthOk(authMessage);
        }
        System.out.println(message.toJson());
        clientHandler.sendMessage(message.toJson());
    }
}
