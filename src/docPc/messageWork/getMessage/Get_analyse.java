package docPc.messageWork.getMessage;

import docPc.controllers.controllerInterface.ControllerAnalyse;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.Text;
import messageCommons.variosOfMessage.Visit;

public class Get_analyse implements GetInterface{
    private ControllerAnalyse controllerAnalyse;
    private Alert alert;

    public Get_analyse(ControllerAnalyse controllerAnalyse) {
        this.controllerAnalyse = controllerAnalyse;
    }


    @Override
    public void getInformation(Message message) {
        Visit visit = message.visit;

        switch (message.commandSecond){
            case VISIT_OK:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Visit is ok");
                alert.setContentText(visit.message);
                alert.showAndWait();
                ((Stage) controllerAnalyse.analyse_bloodAnalysis.getScene().getWindow()).close();
                break;
            case VISIT_NOK:
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Visit is not ok");
                alert.setContentText(visit.message);
                alert.showAndWait();
                break;

            case VISIT_ADDNEW:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Visit is not ok");
                alert.setContentText("There is no user: "
                        + Text.stringWithFirstLowLetter(visit.firstName) + " "
                        + Text.stringWithFirstLowLetter(visit.secondName) +" "
                        + Text.stringWithFirstLowLetter(visit.cityBirthday) +" "+ visit.dateBirthday);
                alert.showAndWait();
                break;
        }
    }
}