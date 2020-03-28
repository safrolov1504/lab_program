package server.workWithMessage;

import messageCommons.CommandSecond;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import messageCommons.variosOfMessage.Client;
import messageCommons.variosOfMessage.Lab;
import messageCommons.variosOfMessage.Visit;
import server.serverWork.ClientHandler;
import server.workWithSQL.User;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            if(authMessage.role.equals("lab")){
                authMessage.lab_analyses1 = checkLogin.getLab_analyses1();
                authMessage.lab_analyses2 = checkLogin.getLab_analyses2();
            }
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

    public void sendLookingClient(Client client, CommandSecond command){
        if(command == CommandSecond.LOOKING_FOR_CLIENT_NOK){
            message = Message.creatLookingClientNok(client);
        } else if(command == CommandSecond.LOOKING_FOR_CLIENT_OK){
            message = Message.creatLookingClientOk(client);
        }
        clientHandler.sendMessage(message.toJson());
    }

    public void sendAddClient(Client client, CommandSecond command){
        if(command == CommandSecond.ADD_CLIENT_NOK){
            message = Message.creatAddClientNOk(client);
        } else if(command == CommandSecond.ADD_CLIENT_OK){
            message = Message.creatAddClientOk(client);
        }
        clientHandler.sendMessage(message.toJson());
    }

    public void sendVisit(Visit visit, CommandSecond resultVisit) {
        if(resultVisit == CommandSecond.VISIT_OK){
            message = Message.creatVisitOK(visit);
        } else if(resultVisit == CommandSecond.VISIT_ADDNEW){
            message = Message.creatVisitADDNew(visit);
        }

        clientHandler.sendMessage(message.toJson());
    }

    public void sendResult(String str){
        clientHandler.sendMessage(str);
    }

    public void sendResultAnalyses(Lab lab, CommandSecond commandSecond, ResultSet resultSet) throws SQLException {
        Message messageOut;
        if (!resultSet.isBeforeFirst()){
            switch (commandSecond){
                case LAB_SEND:
                    lab.message = "There is no unready analyses";
                    break;
                case LAB_READY:
                    lab.message = "There are no ready analyses";
                    break;
            }
            messageOut = Message.creatLabNoAnalyses(lab);
            clientHandler.sendMessage(messageOut.toJson());
        } else {
            while (resultSet.next()){
                lab.idAnalyses = resultSet.getString("id");
                lab.idClient = resultSet.getString("id_name");
                lab.dateVisit = resultSet.getString("dateVisit");
                lab.analyses1 = resultSet.getString("analyses1");
                lab.analyses2 = resultSet.getString("analyses2");
                switch (commandSecond){
                    case LAB_SEND:
                        messageOut = Message.creatLabSendAnalyses(lab);
                        clientHandler.sendMessage(messageOut.toJson());
                        break;
                    case LAB_READY:
                        messageOut = Message.creatLabReadyAnalyses(lab);
                        clientHandler.sendMessage(messageOut.toJson());
                        break;
                }

            }
        }
    }
}
