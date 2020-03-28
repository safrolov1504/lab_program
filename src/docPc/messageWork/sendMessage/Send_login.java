package docPc.messageWork.sendMessage;

import docPc.networkCommunication.IService;
import javafx.scene.control.Alert;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;

public class Send_login extends SendInterface{
    private IService messageService;
    private Alert alert;

    public Send_login(IService messageService) {
        this.messageService = messageService;
    }

    public void checkLogin(String login, String password) {
       if(login.equals("") || password.equals("")){
           alert = new Alert(Alert.AlertType.WARNING);
           alert.setHeaderText("Authentication is failed");
           alert.setContentText("Fill in login and password");
           alert.showAndWait();
       } else {
           AuthMessage authMessage = new AuthMessage();
           authMessage.login = login;
           authMessage.password = password;
           Message auth = Message.creatAuth(authMessage);

           messageService.sendMessage(auth.toJson());
       }
    }
}
