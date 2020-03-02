package doctorPC.messageWorkWith;

import doctorPC.ChangeStage;
import doctorPC.iterfaceDocktor.Controller;
import doctorPC.networkCommunication.IService;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messageCommons.Command;
import messageCommons.Message;

import java.io.IOException;

public class GetMessage {
    private IService messageService;
    private Controller controller;

    public GetMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
    }

    public void sendMessageToWorkWith(String messageIn) {
        System.out.println(messageIn);
        Message message = Message.fromJson(messageIn);
        switch (message.command){
            case AUTH_OK:
                ChangeStage.changeStageDo((Stage) controller.button_signIn.getScene().getWindow(),
                        "resources/workInterface.fxml", "Work window for doctor PC");
                break;
            case AUTH_NOK:
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Authentication is failed");
                alert.setContentText(message.authMessage.message);
                alert.showAndWait();
                break;
        }


    }
}
