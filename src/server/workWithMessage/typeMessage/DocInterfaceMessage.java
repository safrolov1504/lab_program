package server.workWithMessage.typeMessage;

import messageCommons.CommandSecond;
import messageCommons.Message;
import messageCommons.variosOfMessage.Client;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocInterfaceMessage implements DifferentTypeMessage {
    private RequirementSQL requirementSQL;
    private SendMessage sendMessage;

    private Client client;
    private String firstName;
    private String secondName;
    private String cityOfBirthday;
    private String dateOfBirthday;
    private ResultSet resultSet;


    public DocInterfaceMessage(SendMessage sendMessage, RequirementSQL requirementSQL) {
        this.requirementSQL = requirementSQL;
        this.sendMessage = sendMessage;
    }

    @Override
    public void getInformation(Message message) throws SQLException {
        client = message.client;
        firstName = client.firstName;
        secondName = client.secondName;
        cityOfBirthday = client.cityOfBirthday;
        dateOfBirthday = client.dateOfBirthday;

        switch (message.commandSecond){
            case LOOKING_FOR_CLIENT:
                resultSet = requirementSQL.lookingForClint(client.firstName,client.secondName,client.cityOfBirthday,client.dateOfBirthday);
                sendClients(resultSet);
                break;
            case LOOKING_FOR_ALL:
                resultSet = requirementSQL.lookingForClintAll();
                sendClients(resultSet);
                break;
            case ADD_NEW_CLIENT:
                    if(requirementSQL.addClient(firstName,secondName,cityOfBirthday,dateOfBirthday)){
                        client.message = "A new client added";
                        sendMessage.sendAddClient(client,CommandSecond.ADD_CLIENT_OK);
                    } else {
                        client.secondName = null;
                        client.message = "There is already this client";
                        sendMessage.sendAddClient(client,CommandSecond.ADD_CLIENT_NOK);
                    }
                break;
        }


    }

    private void sendClients(ResultSet resultSet) throws SQLException {
        if(!resultSet.isBeforeFirst()){
            client.message = "There is no client with this first name and second name";
            sendMessage.sendLookingClient(client, CommandSecond.LOOKING_FOR_CLIENT_NOK);
        } else {
            while (resultSet.next()){
                client.firstName = resultSet.getString("FirstName");
                client.secondName = resultSet.getString("SecondName");
                client.cityOfBirthday = resultSet.getString("CityOfBirthday");
                client.dateOfBirthday = resultSet.getString("DateOfBirthday");
                client.dateVisit = resultSet.getString("dateVisit");
                sendMessage.sendLookingClient(client, CommandSecond.LOOKING_FOR_CLIENT_OK);
            }
        }
    }
}
