package server.workWithMessage.typeMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.Client;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingInterfaceMessage implements DifferentTypeMessage {
    private RequirementSQL requirementSQL;
    private SendMessage sendMessage;

    private Client client;
    private String firstName;
    private String secondName;
    private String cityOfBirthday;
    private String dateOfBirthday;


    public WorkingInterfaceMessage(SendMessage sendMessage, RequirementSQL requirementSQL) {
        this.requirementSQL = requirementSQL;
        this.sendMessage = sendMessage;
    }

    @Override
    public void getInformation(Message message) throws SQLException {
        switch (message.commandSecond){
            case LOOKING_FOR_CLIENT:
                addInfo(message);
                //здесь надо заполнить запрос в БД
                //client =
                if(firstName.equals("") && secondName.equals("")  && cityOfBirthday.equals("") && dateOfBirthday.equals("")){
                    client.secondName = null;
                    client.message = "Please fill first name and second name";
                    sendMessage.sendLookingClient(client);
                } else {
                    ResultSet resultSet = requirementSQL.lookingForClint(client.firstName,client.secondName);
                    if(!resultSet.isBeforeFirst()){
                        client.secondName = null;
                        client.message = "There is no client with this first name and second name";
                        sendMessage.sendLookingClient(client);
                    } else {
                        while (resultSet.next()){
                            client.firstName = resultSet.getString("FirstName");
                            client.secondName = resultSet.getString("SecondName");
                            client.cityOfBirthday = resultSet.getString("CityOfBirthday");
                            client.dateOfBirthday = resultSet.getString("DateOfBirthday");
                            System.out.println(client.toString());
                            sendMessage.sendLookingClient(client);
                        }
                    }
                }
                //подумать как отправлять много клиентов

                break;

            case ADD_NEW_CLIENT:
                addInfo(message);

                if(firstName.equals("") || secondName.equals("")  || cityOfBirthday.equals("") || dateOfBirthday.equals("")) {
                    client.secondName = null;
                    client.message = "Fill every field";
                    //sendMessage.sendClient();
                } else {
                    if(requirementSQL.addClient(firstName,secondName,cityOfBirthday,dateOfBirthday)){
                        client.message = "A new client added";
                    } else {
                        client.secondName = null;
                        client.message = "There is already this client";
                    }
                }
                sendMessage.sendAddClient(client);
                break;
        }
    }

    private void addInfo(Message message){
        client = message.client;
        firstName = client.firstName;
        secondName = client.secondName;
        cityOfBirthday = client.cityOfBirthday;
        dateOfBirthday = client.dateOfBirthday;
    }
}
