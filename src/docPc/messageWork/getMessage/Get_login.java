package docPc.messageWork.getMessage;

import docPc.*;
import docPc.controllers.ChangeStage;
import docPc.controllers.controllerInterface.ControllerLogin;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.Text;
import messageCommons.variosOfMessage.AuthMessage;

public class Get_login implements GetInterface{

    private ControllerLogin controllerLogin;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    public Get_login(ControllerLogin controllerLogin) {
        this.controllerLogin = controllerLogin;
    }

    public void getInformation(Message message) {
        switch (message.commandSecond){
            case AUTH_MESSAGE:
            case AUTH_OK:
                AuthMessage authMessage = message.authMessage;
                App.getController().setFirstName(Text.stringWithFirstLowLetter(authMessage.firstName));
                App.getController().setSecondName(Text.stringWithFirstLowLetter(authMessage.secondName));
                switch (authMessage.role){
                    case "doctor":
                        System.out.println("Doctor");
                        ChangeStage.changeStageDo((Stage) controllerLogin.button_signIn.getScene().getWindow(),
                                "/docPc/resources/docInterface.fxml", "DoctorPC "+
                                        authMessage.firstName+" "+authMessage.secondName);
                        break;
                    case "lab":
                        System.out.println("Lab");
                        App.getController().setMessage(message);
                        ChangeStage.changeStageDo((Stage) controllerLogin.button_signIn.getScene().getWindow(),
                                "/docPc/resources/labInterface.fxml", "Lab PC: "+
                                        authMessage.firstName+" "+authMessage.secondName);
                        //
                        break;
                    case "service":
                        System.out.println("Service");
                        ChangeStage.changeStageDo((Stage) controllerLogin.button_signIn.getScene().getWindow(),
                                "/docPc/resources/serviceInterface.fxml", "Service PC: "+
                                        authMessage.firstName+" "+authMessage.secondName+" "+authMessage.role);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + authMessage.role);
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
