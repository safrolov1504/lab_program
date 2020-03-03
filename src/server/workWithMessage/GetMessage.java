package server.workWithMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import server.serverWork.ClientHandler;
import server.workWithMessage.typeMessage.DifferentTypeMessage;
import server.workWithMessage.typeMessage.LoginInterfaceMessage;
import server.workWithMessage.typeMessage.ServiceInterfaceMessage;
import server.workWithSQL.BaseSQLServer;
import server.workWithSQL.RequirementSQL;
import server.workWithSQL.SQLServer;

import java.sql.SQLException;


public class GetMessage {
    private ClientHandler clientHandler;
    private RequirementSQL requirementSQL;
    private SendMessage sendMessage;
    private LoginInterfaceMessage loginInterfaceMessage;
    private ServiceInterfaceMessage serviceInterfaceMessage;

    private AuthMessage authMessage;
    private String messageOut;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String role;
    private String profession;


    public GetMessage(ClientHandler clientHandler, SQLServer sqlServer, SendMessage sendMessage) {
        this.clientHandler = clientHandler;
        this.requirementSQL = new RequirementSQL((BaseSQLServer) sqlServer);
        this.sendMessage = sendMessage;
        loginInterfaceMessage = new LoginInterfaceMessage(sendMessage,requirementSQL);
        serviceInterfaceMessage = new ServiceInterfaceMessage(sendMessage,requirementSQL);
    }

    public void workWithInformation(String clientMessage) throws SQLException {
            System.out.println(clientMessage);

            Message message = Message.fromJson(clientMessage);

            switch (message.commandFirst){
                case LOGIN:
                    loginInterfaceMessage.getIntormation(message);
                    break;
                case DOC:
                    break;
                case LAB:

                    break;

                case SERVICE:
                    serviceInterfaceMessage.getIntormation(message);
                    break;
            }
    }
}
