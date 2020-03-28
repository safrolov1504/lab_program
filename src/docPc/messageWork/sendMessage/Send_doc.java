package docPc.messageWork.sendMessage;

import docPc.networkCommunication.IService;
import javafx.scene.control.Alert;
import messageCommons.CommandSecond;
import messageCommons.Message;
import messageCommons.variosOfMessage.Client;

public class Send_doc extends SendInterface{
    private IService messageService;
    private Alert alert;

    public Send_doc(IService messageService) {
        this.messageService = messageService;
    }

    public void lookingForClient(CommandSecond commandSecond,String firstName, String secondName, String cityOfBirthday, String dataOfBirthday) {
        if(commandSecond == CommandSecond.ADD_NEW_CLIENT &&
                (firstName.equals("") || secondName.equals("") || cityOfBirthday.equals("") || dataOfBirthday.equals(""))){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Problem with adding a new client");
            alert.setContentText("Fill in all data");
            alert.showAndWait();
        } else if(firstName.equals("") || secondName.equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Problem with search");
            alert.setContentText("Fill in first name and second name");
            alert.showAndWait();
        } else {
            Client client = new Client();
            client.firstName = firstName;
            client.secondName = secondName;
            client.cityOfBirthday = cityOfBirthday;
            client.dateOfBirthday = dataOfBirthday;

            Message message = new Message();
            if (commandSecond == CommandSecond.LOOKING_FOR_CLIENT) {
                message = Message.creatLookingClient(client);
            } else if (commandSecond == CommandSecond.ADD_NEW_CLIENT) {
                message = Message.creatAddClient(client);
            }
            messageService.sendMessage(message.toJson());
        }
    }
    public void lookingForClientAll() {
        Message message = Message.creatLookingClientALL(new Client());
        messageService.sendMessage(message.toJson());
    }

    public void getResult(Client client) {
        if(client == null){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Problem with showing the result");
            alert.setContentText("Please choose a client");
            alert.showAndWait();
        } else if(client.dateVisit.equals("no visits")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Problem with showing the result");
            alert.setContentText("There are no visits");
            alert.showAndWait();
        } else {
            Message message = Message.creatResult(client);
            messageService.sendMessage(message.toJson());
        }
    }


}
