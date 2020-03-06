package server.workWithMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import messageCommons.variosOfMessage.Client;
import server.serverWork.ClientHandler;
import server.workWithSQL.User;

public class SendMessage {
    private ClientHandler clientHandler;
    Message message;

    public SendMessage(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void sendAuthor(User checkLogin) {
        AuthMessage authMessage = new AuthMessage();

        if(checkLogin.getFirstName() == null){
            authMessage.message = "Wrong login or password";
            message = Message.creatAuthNOk(authMessage);
        } else {
            authMessage.firstName = checkLogin.getFirstName();
            authMessage.secondName = checkLogin.getSecondName();
            authMessage.role = checkLogin.getRole();
            authMessage.profession = checkLogin.getProfession();
            message = Message.creatAuthOk(authMessage);
        }
        System.out.println(message.toJson());
        clientHandler.sendMessage(message.toJson());
    }


    public void sendNewUser(AuthMessage authMessage) {
        if(authMessage.secondName == null){
           message = Message.creatNewUserNok(authMessage);
        } else {
            message = Message.creatNewUserOk(authMessage);
        }
        System.out.println(message.toJson());
        clientHandler.sendMessage(message.toJson());
    }

    public void sendLookingClient(Client client){
        if(client.secondName == null){
            message = Message.creatLookingClientNok(client);
        } else {
            message = Message.creatLookingClientOk(client);
        }
        clientHandler.sendMessage(message.toJson());
    }

    public void sendAddClient(Client client){
        if(client.secondName == null){
            message = Message.creatAddClientNOk(client);
        } else {
            message = Message.creatAddClientOk(client);
        }
        clientHandler.sendMessage(message.toJson());
    }
}
