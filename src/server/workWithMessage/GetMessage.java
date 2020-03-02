package server.workWithMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import server.serverWork.ClientHandler;
import server.workWithSQL.BaseSQLServer;
import server.workWithSQL.RequirementSQL;
import server.workWithSQL.SQLServer;

import java.sql.SQLException;


public class GetMessage {
    private ClientHandler clientHandler;
    private RequirementSQL requirementSQL;
    private SendMessage sendMessage;

    public GetMessage(ClientHandler clientHandler, SQLServer sqlServer, SendMessage sendMessage) {
        this.clientHandler = clientHandler;
        this.requirementSQL = new RequirementSQL((BaseSQLServer) sqlServer);
        this.sendMessage = sendMessage;
    }

    public void workWithInformation(String clientMessage) throws SQLException {
            System.out.println(clientMessage);

            Message message = Message.fromJson(clientMessage);
            switch (message.command){
                case AUTH_MESSAGE:
                    AuthMessage authMessage = message.authMessage;
                    String login = authMessage.login;
                    String password = authMessage.password;
                    //requirementSQL.checkLogin(login,password);
                    sendMessage.sendAuthor(requirementSQL.checkLogin(login,password));
            }
    }
}
