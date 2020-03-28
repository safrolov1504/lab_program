package docPc.messageWork.getMessage;

import docPc.controllers.controllerInterface.ControllerService;
import javafx.scene.control.Alert;
import messageCommons.Message;

public class Get_service implements GetInterface{
    private ControllerService controllerService;
    private Alert alert;

    public Get_service(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    public void getInformation(Message message) {
        switch (message.commandSecond){
            case ADD_NEW_OK:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Adding new user is ok");
                alert.setContentText("New user " + message.authMessage.firstName+" "+message.authMessage.secondName + " was added");
                alert.showAndWait();
                break;

            case ADD_NEW_NOK:
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Adding new user is failed");
                alert.setContentText(message.authMessage.message);
                alert.showAndWait();
                break;
        }
    }
}
