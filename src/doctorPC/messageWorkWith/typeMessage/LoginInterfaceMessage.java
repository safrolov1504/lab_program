package doctorPC.messageWorkWith.typeMessage;

import doctorPC.ChangeStage;
import doctorPC.iterfaceDocktor.Controller;
import doctorPC.messageWorkWith.SendMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import messageCommons.variosOfMessage.Client;

public class LoginInterfaceMessage implements DifferentTypeMessage {

    private Controller controller;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    public LoginInterfaceMessage(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void getInformation(Message message) {
        switch (message.commandSecond){
            case AUTH_MESSAGE:
            case AUTH_OK:
                AuthMessage authMessage = message.authMessage;
                switch (authMessage.role){
                    case "Doctor":
                        ChangeStage.changeStageDo((Stage) controller.button_signIn.getScene().getWindow(),
                                "resources/workInterface.fxml", "DoctorPC "+
                                        authMessage.firstName+" "+authMessage.secondName);
                        //controller.docWork_nameDoc.setText("Doctor: ");
                        //controller.docWork_nameDoc.setText("Doctor: "+authMessage.firstName+" "+authMessage.secondName);
                        break;
                    case "Lab":
//                        controller.lab_nameDoc.setText("Lab PC: "+ authMessage.firstName+" "+authMessage.secondName);
                        ChangeStage.changeStageDo((Stage) controller.button_signIn.getScene().getWindow(),
                                "resources/analysisInterface.fxml", "Lab PC: "+
                                        authMessage.firstName+" "+authMessage.secondName);
                        //
                        break;
                    case "Service":
                        ChangeStage.changeStageDo((Stage) controller.button_signIn.getScene().getWindow(),
                                "resources/serviceInterface.fxml", "Service PC: "+
                                        authMessage.firstName+" "+authMessage.secondName+" "+authMessage.role);
                        break;
                }

                break;
            case AUTH_NOK:
                alert.setHeaderText("Authentication is failed");
                alert.setContentText(message.authMessage.message);
                alert.showAndWait();
                break;
        }
    }
}
