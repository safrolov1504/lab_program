package server.workWithMessage;

import messageCommons.Message;
import server.serverWork.ClientHandler;
import server.workWithMessage.typeMessage.*;
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
    private DocInterfaceMessage docInterfaceMessage;
    private AnalisInterfaceMessage analisInterfaceMessage;
    private ResultInterfaceMessage resultInterfaceMessage;
    private LabInterfaceMessage labInterfaceMessage;

    public GetMessage(ClientHandler clientHandler, SQLServer sqlServer, SendMessage sendMessage) {
        this.clientHandler = clientHandler;
        this.requirementSQL = new RequirementSQL((BaseSQLServer) sqlServer);
        this.sendMessage = sendMessage;
        loginInterfaceMessage = new LoginInterfaceMessage(sendMessage,requirementSQL);
        serviceInterfaceMessage = new ServiceInterfaceMessage(sendMessage,requirementSQL);
        docInterfaceMessage = new DocInterfaceMessage(sendMessage,requirementSQL);
        analisInterfaceMessage = new AnalisInterfaceMessage(sendMessage,requirementSQL);
        resultInterfaceMessage = new ResultInterfaceMessage(sendMessage, requirementSQL);
        labInterfaceMessage = new LabInterfaceMessage(sendMessage,requirementSQL);
    }

    public void workWithInformation(String clientMessage) throws SQLException {
            System.out.println(clientMessage);

            Message message = Message.fromJson(clientMessage);

            switch (message.commandFirst){
                case LOGIN:
                    loginInterfaceMessage.getInformation(message);
                    break;
                case DOC:
                    docInterfaceMessage.getInformation(message);
                    break;
                case ANALYSE:
                    analisInterfaceMessage.getInformation(message);
                    break;
                case SERVICE:
                    serviceInterfaceMessage.getInformation(message);
                    break;
                case RESULT:
                    resultInterfaceMessage.getInformation(message);
                    break;
                case LAB:
                    labInterfaceMessage.getInformation(message);
                    break;
            }
    }
}
