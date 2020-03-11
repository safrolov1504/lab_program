package doctorPC.messageWorkWith.typeMessage;

import doctorPC.iterfaceDocktor.Controller;
import javafx.scene.control.Alert;
import messageCommons.Message;

public class ServiceInterfaceMessage implements DifferentTypeMessage {

    private Controller controller;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    public ServiceInterfaceMessage(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void getInformation(Message message) {
        switch (message.commandSecond){
            case ADD_NEW:
                addNewUser(message);
                break;
        }
    }

    private void addNewUser(Message message) {
        switch (message.commandSecond){
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
