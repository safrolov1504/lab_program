package doctorPC.messageWorkWith;

import doctorPC.ChangeStage;
import doctorPC.iterfaceDocktor.Controller;
import doctorPC.networkCommunication.IService;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;

public class GetMessage {
    private IService messageService;
    private Controller controller;
    Alert alert = new Alert(Alert.AlertType.WARNING);

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public GetMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
    }

    public void sendMessageToWorkWith(String messageIn) {
        System.out.println(messageIn);
        Message message = Message.fromJson(messageIn);
        switch (message.commandSecond){
            case AUTH_OK:
                AuthMessage authMessage = message.authMessage;
                switch (authMessage.role){
                    case "Doctor":
                            ChangeStage.changeStageDo((Stage) controller.button_signIn.getScene().getWindow(),
                                "resources/workInterface.fxml", "DoctorPC "+
                                    authMessage.firstName+authMessage.secondName+" "+authMessage.role);
                        break;
                    case "Lab":

                        break;
                    case "Service":
                            ChangeStage.changeStageDo((Stage) controller.button_signIn.getScene().getWindow(),
                                "resources/serviceInterface.fxml", "ServicePC"+
                                            authMessage.firstName+authMessage.secondName+" "+authMessage.role);
                        break;
                }

                break;
            case AUTH_NOK:
                alert.setHeaderText("Authentication is failed");
                alert.setContentText(message.authMessage.message);
                alert.showAndWait();
                break;

            case ADD_NEW_OK:
                alert.setHeaderText("Adding new user is ok");
                alert.setContentText("New user " + message.authMessage.firstName+" "+message.authMessage.secondName + " was added");
                alert.showAndWait();
                break;

            case ADD_NEW_NOK:
                alert.setHeaderText("Adding new user is failed");
                alert.setContentText(message.authMessage.message);
                alert.showAndWait();
                break;

        }


    }
}
