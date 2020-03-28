package docPc.messageWork.sendMessage;

import docPc.networkCommunication.IService;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;

public class Send_service extends SendInterface{
    private IService messageService;
    private Alert alert;

    public Send_service(IService messageService) {
        this.messageService = messageService;
    }

    public void addNewUser(String role, String login, String password, String firstName, String secondName, String profession) {
        if(login.equals("") || password.equals("") || firstName.equals("") || secondName.equals("") ||
                role.equals("") || profession.equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Authentication is failed");
            alert.setContentText("Fill in all fields");
            alert.showAndWait();
        } else {
            AuthMessage authMessage = new AuthMessage();
            authMessage.login = login;
            authMessage.password = password;
            authMessage.firstName = firstName;
            authMessage.secondName = secondName;
            authMessage.role = role;
            authMessage.profession = profession;

            Message message = Message.creatNewUser(authMessage);
            messageService.sendMessage(message.toJson());
        }
    }
}
