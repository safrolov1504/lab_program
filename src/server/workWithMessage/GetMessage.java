package server.workWithMessage;

import messageCommons.Message;
import server.serverWork.ClientHandler;
import server.workWithMessage.typeMessage.AnalisInterfaceMessage;
import server.workWithMessage.typeMessage.LoginInterfaceMessage;
import server.workWithMessage.typeMessage.ServiceInterfaceMessage;
import server.workWithMessage.typeMessage.WorkingInterfaceMessage;
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
    private WorkingInterfaceMessage workingInterfaceMessage;
    private AnalisInterfaceMessage labInterfaceMessage;

    public GetMessage(ClientHandler clientHandler, SQLServer sqlServer, SendMessage sendMessage) {
        this.clientHandler = clientHandler;
        this.requirementSQL = new RequirementSQL((BaseSQLServer) sqlServer);
        this.sendMessage = sendMessage;
        loginInterfaceMessage = new LoginInterfaceMessage(sendMessage,requirementSQL);
        serviceInterfaceMessage = new ServiceInterfaceMessage(sendMessage,requirementSQL);
        workingInterfaceMessage = new WorkingInterfaceMessage(sendMessage,requirementSQL);
        labInterfaceMessage = new AnalisInterfaceMessage(sendMessage,requirementSQL);
    }

    public void workWithInformation(String clientMessage) throws SQLException {
            System.out.println(clientMessage);

            Message message = Message.fromJson(clientMessage);

            switch (message.commandFirst){
                case LOGIN:
                    loginInterfaceMessage.getInformation(message);
                    break;
                case DOC:
                    workingInterfaceMessage.getInformation(message);
                    break;
                case LAB:
                    labInterfaceMessage.getInformation(message);
                    break;
                case SERVICE:
                    serviceInterfaceMessage.getInformation(message);
                    break;
            }
    }
}
