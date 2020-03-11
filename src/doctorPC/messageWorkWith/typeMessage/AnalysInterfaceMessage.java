package doctorPC.messageWorkWith.typeMessage;

import doctorPC.ChangeStage;
import doctorPC.iterfaceDocktor.Controller;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.variosOfMessage.Visit;

public class AnalysInterfaceMessage implements DifferentTypeMessage {

    private Controller controller;
    private Alert alert = new Alert(Alert.AlertType.WARNING);
    private Visit visit;

    public AnalysInterfaceMessage(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void getInformation(Message message) {
        visit = message.visit;

        switch (message.commandSecond){
            case VISIT_OK:
                alert.setHeaderText("Visit is ok");
                alert.setContentText(visit.message);
                alert.showAndWait();
                break;
            case VISIT_NOK:
                alert.setHeaderText("Visit is not ok");
                alert.setContentText(visit.message);
                alert.showAndWait();
                break;

            case VISIT_ADDNEW:
                controller.sendMessegeForAddNew(visit);
                ChangeStage.changeStageDoWithoutClose((Stage) controller.analy_bloodAnalysis.getScene().getWindow(),
                        "resources/addNewFromAnalysisInterface.fxml", "Add new user");
                break;
        }
    }
}
