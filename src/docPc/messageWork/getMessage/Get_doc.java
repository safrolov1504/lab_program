package docPc.messageWork.getMessage;

import docPc.App;
import docPc.controllers.ChangeStage;
import docPc.controllers.controllerInterface.ControllerDoc;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.Text;
import messageCommons.variosOfMessage.Client;

import java.io.IOException;

public class Get_doc implements GetInterface{
    private ControllerDoc controllerDoc;
    private Alert alert;

    private String firstName;
    private String secondName;
    private String cityOfBirthday;
    private String dateOfBirthday;
    private String dateVisit;

    public Get_doc(ControllerDoc controllerDoc) {
        this.controllerDoc = controllerDoc;
    }

    public void getInformation(Message message) {
        switch (message.commandSecond){
            case ADD_CLIENT_OK:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Adding new client is ok");
                alert.setContentText("New user "
                        + Text.stringWithFirstLowLetter(message.client.firstName) +" "
                        + Text.stringWithFirstLowLetter(message.client.secondName) + " was added");
                clear();
                alert.showAndWait();
                break;

            case ADD_CLIENT_NOK:
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Adding new user is not ok");
                alert.setContentText(message.client.message);
                alert.showAndWait();
                break;

            case LOOKING_FOR_CLIENT_OK:
                this.firstName = Text.stringWithFirstLowLetter(message.client.firstName);
                this.secondName = Text.stringWithFirstLowLetter(message.client.secondName);
                this.cityOfBirthday = Text.stringWithFirstLowLetter(message.client.cityOfBirthday);
                this.dateOfBirthday = Text.stringWithFirstLowLetter(message.client.dateOfBirthday);
                if(message.client.dateVisit == null){
                    this.dateVisit = "no visits";
                } else{
                    this.dateVisit = message.client.dateVisit;
                }


                clear();

                Client client = new Client(firstName,secondName,cityOfBirthday, dateOfBirthday,dateVisit);
                controllerDoc.clientData.add(client);
                controllerDoc.docWork_table.setItems(controllerDoc.clientData);
                break;

            case LOOKING_FOR_CLIENT_NOK:
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Problem with search");
                alert.setContentText(message.client.message);
                alert.showAndWait();
                break;
            case RESULT_OK:
                App.getController().setMessage(message);
                    ChangeStage.changeStageDo((Stage) controllerDoc.docWork_table.getScene().getWindow(),
                            "/docPc/resources/resultInterface.fxml", "Results "+
                                    message.visit.firstName+" "+message.visit.secondName,
                            false,false);
                break;
            case RESULT_NOK:
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Problem with result");
                alert.setContentText(message.visit.message);
                alert.showAndWait();
                break;
        }
    }

    private void clear(){
        controllerDoc.docWork_fieldCityBirth.clear();
        controllerDoc.docWork_fieldFirstName.clear();
        controllerDoc.docWork_fieldSecondName.clear();
        controllerDoc.docWork_dateBirthday.getEditor().clear();
    }
}
