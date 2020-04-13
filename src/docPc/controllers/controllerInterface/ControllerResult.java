package docPc.controllers.controllerInterface;

import docPc.App;
import docPc.messageWork.getMessage.Get_result;
import docPc.networkCommunication.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerResult extends ControllerInterface implements Initializable {

    public @FXML Label result_nameDoc;
    public @FXML Label resultFirstName;
    public @FXML Label resultSecondName;
    public @FXML Label resultCityOfBirthday;
    public @FXML Label resultDateOfBirthday;
    public @FXML Label resultDiagnosis;
    public @FXML Label resultDateVisit;
    public @FXML Label resultBlood;
    public @FXML Label resultUrine;
    public @FXML Label resultFecal;
    public @FXML Label resultSmear;
    public @FXML Label resultDNA;
    public @FXML Label resultPaternity;

    private Message message;
    private int resultInt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize result");
        message = App.getController().getMessage();
        prepareResult();
    }

    private void prepareResult() {

        result_nameDoc.setText("A result visit: " + Text.stringWithFirstLowLetter(message.visit.firstName)+
                " " + Text.stringWithFirstLowLetter(message.visit.secondName));
        resultFirstName.setText(Text.stringWithFirstLowLetter(message.visit.firstName));
        resultSecondName.setText(Text.stringWithFirstLowLetter(message.visit.secondName));
        resultCityOfBirthday.setText(Text.stringWithFirstLowLetter(message.visit.cityBirthday));
        resultDateOfBirthday.setText(message.visit.dateBirthday);
        resultDiagnosis.setText(Text.stringWithFirstLowLetter(message.visit.diagnose));
        resultDateVisit.setText((message.visit.dateVisit));
        resultBlood.setText(checkResult(message.visit.bloodAnalysis));
        resultUrine.setText(checkResult(message.visit.urinAnalysis));
        resultFecal.setText(checkResult(message.visit.frcalAnalysis));
        resultSmear.setText(checkResult(message.visit.smearAnalysis));
        resultDNA.setText(checkResult(message.visit.dNAAnalysis));
        resultPaternity.setText(checkResult(message.visit.paterAnalysis));
    }

    private String checkResult(String result){
        if(result.equals("false")){
            return "not need";
        } else {
            resultInt = Integer.parseInt(result);
            if(resultInt < 20){
                return result +" is low";
            } else if(resultInt<55){
                return result +" is normal";
            } else {
                return result + " is high";
            }
        }
    }


    public void result_back(ActionEvent actionEvent) {
        Stage stage = (Stage) resultBlood.getScene().getWindow();
        stage.close();
    }
}
