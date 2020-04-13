package docPc.messageWork.getMessage;

import docPc.controllers.controllerInterface.ControllerAnalyse;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;
import messageCommons.Message;
import messageCommons.Text;
import messageCommons.variosOfMessage.Visit;

import java.util.Optional;

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
                        + Text.stringWithFirstLowLetter(visit.cityBirthday) +" "+ visit.dateBirthday + " /n Add a new user and send analysis?") ;
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    System.out.println("press ok");
                    controllerAnalyse.getSendInterface().sendAnalysisAndClient(visit);
                }
                break;
        }
    }
}
