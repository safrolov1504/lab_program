package docPc.controllers.controllerInterface;

import docPc.*;
import docPc.messageWork.getMessage.Get_analyse;
import docPc.messageWork.sendMessage.Send_analyse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import messageCommons.variosOfMessage.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAnalyse extends ControllerInterface implements Initializable {

    //private IService messageService;
    //private Send_analyse sendInterface;
    //private Get_analyse getInterface;

    //analyse
    public @FXML TextField analyse_fieldFirstName;
    public @FXML TextField analyse_fieldSecondName;
    public @FXML TextField analyse_fieldDiagnose;
    public @FXML TextField analyse_fieldCityBirth;
    public @FXML DatePicker analyse_fieldDateBirthday;
    public @FXML DatePicker analyse_dateVisit;
    public @FXML CheckBox analyse_bloodAnalysis;
    public @FXML CheckBox analyse_urinAnalysis;
    public @FXML CheckBox analyse_frcalAnalysis;
    public @FXML CheckBox analyse_smearAnalysis;
    public @FXML CheckBox analyse_DNAAnalysis;
    public @FXML CheckBox analyse_paterAnalysis;
    public @FXML Label lab_nameDoc;

    private TableView.TableViewSelectionModel<Client> selectionModel;
    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize analyse");
        messageService = App.getController().getMessageService();
        selectionModel = App.getController().getSelectionModel();
        this.getInterface = new Get_analyse(this);
        this.sendInterface = new Send_analyse(messageService);
        this.client = App.getController().getClient();
        App.getController().setGet_analyse(getInterface);
        System.out.println(selectionModel.isEmpty());

        lab_nameDoc.setText("A new visit of doctor: " + App.getController().getFirstName()+" " + App.getController().getSecondName());
        if(client != null){
            writeDateOfClient();
        }
    }

    private void writeDateOfClient() {
        analyse_fieldFirstName.setText(client.firstName);
        analyse_fieldSecondName.setText(client.secondName);
        analyse_fieldCityBirth.setText(client.cityOfBirthday);
        analyse_fieldDateBirthday.getEditor().setText(client.dateVisit);
        //analyse_fieldDateBirthday.setUserData(client.dateVisit); // это не работате, посмотреть как сделать
    }


    public void analyse_send(ActionEvent actionEvent) {
            sendInterface.sendAnalysis(
                analyse_fieldFirstName.getText().toLowerCase(),
                analyse_fieldSecondName.getText().toLowerCase(),
                    App.getController().getFirstName().toLowerCase(),
                    App.getController().getSecondName().toLowerCase(),
                analyse_fieldDiagnose.getText().toLowerCase(),
                analyse_dateVisit.getEditor().getText().toLowerCase(),
                analyse_fieldCityBirth.getText().toLowerCase(),
                analyse_fieldDateBirthday.getEditor().getText().toLowerCase(),
                String.valueOf(analyse_bloodAnalysis.isSelected()).toLowerCase(),
                String.valueOf(analyse_urinAnalysis.isSelected()).toLowerCase(),
                String.valueOf(analyse_frcalAnalysis.isSelected()).toLowerCase(),
                String.valueOf(analyse_smearAnalysis.isSelected()).toLowerCase(),
                String.valueOf(analyse_DNAAnalysis.isSelected()).toLowerCase(),
                String.valueOf(analyse_paterAnalysis.isSelected()).toLowerCase());
    }

    public void analyse_back(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) analyse_bloodAnalysis.getScene().getWindow();
        stage.close();
    }

}
