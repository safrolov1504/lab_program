package doctorPC.iterfaceDocktor;

import doctorPC.ChangeStage;
import doctorPC.networkCommunication.IService;
import doctorPC.networkCommunication.MyServerDoctor;
import doctorPC.messageWorkWith.SendMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import messageCommons.CommandSecond;
import messageCommons.variosOfMessage.Client;
import messageCommons.variosOfMessage.Visit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public @FXML AnchorPane window;
    //Login window
    public @FXML VBox loginBox;
    public @FXML TextField textField_login;
    public @FXML PasswordField testField_pass;
    public @FXML Button button_signIn;

    //Working service
    public @FXML Button add_button_addNewDoctor;
    public @FXML Button add_button_addNewLab;

    public @FXML TextField add_field_login;
    public @FXML PasswordField add_field_pass;
    public @FXML TextField add_field_firstName;
    public @FXML TextField add_field_secondName;
    public @FXML TextField add_field_profName;

    //Working window
    public @FXML TextField docWork_nameDoc;
    public @FXML TextField docWork_fieldFirstName;
    public @FXML TextField docWork_fieldSecondName;
    public @FXML TextField docWork_fieldCityBirth;
    public @FXML DatePicker docWork_dateBirthday;
    public @FXML TableView<Client> docWork_table;
    public @FXML TableColumn<Client,String> docWork_table_firstName;
    public @FXML TableColumn<Client,String> docWork_table_secondName;
    public @FXML TableColumn<Client,String> docWork_table_city;
    public @FXML TableColumn<Client,String> docWork_table_dateBirth;
    public @FXML TableColumn<Client,String> docWork_table_dateVisit;
    public ObservableList<Client> clientData = FXCollections.observableArrayList();

    //lab
    public @FXML TextField analy_fieldFirstName;
    public @FXML TextField analy_fieldSecondName;
    public @FXML TextField analy_fieldDiagnose;
    public @FXML TextField analy_fieldCityBirth;
    public @FXML DatePicker analy_fieldDateBirthday;
    public @FXML DatePicker analy_dateVisit;
    public @FXML CheckBox analy_bloodAnalysis;
    public @FXML CheckBox analy_urinAnalysis;
    public @FXML CheckBox analy_frcalAnalysis;
    public @FXML CheckBox analy_smearAnalysis;
    public @FXML CheckBox analy_DNAAnalysis;
    public @FXML CheckBox analy_paterAnalysis;
    public @FXML Label lab_nameDoc;

    //add new user from visit
    public @FXML Label addNewVisit_filedData;
    private String firstName;
    private String secondName;
    private String cityOfBirthday;
    private String dateOfBirthday;

    private IService messageService;
    private SendMessage sendMessage;

    public void shutdown() {
        //System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.messageService = new MyServerDoctor(this);
            //this.getMessage = new GetMessage(this.messageService,this);
            this.sendMessage = new SendMessage(this.messageService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //button sign in
    public void button_signIn(ActionEvent actionEvent) {
        sendMessage.checkLogin(textField_login.getText(),testField_pass.getText());
    }

    //buttons service
    public void add_button_addNewDoctor(ActionEvent actionEvent) {
        addNewUser("Doctor");
    }

    public void add_button_addNewLab(ActionEvent actionEvent) {
        addNewUser("Lab");
    }

    private void addNewUser(String typeUser) {
        sendMessage.addNewUser(typeUser,
                add_field_login.getText(),
                add_field_pass.getText(),
                add_field_firstName.getText(),
                add_field_secondName.getText(),
                add_field_profName.getText());
    }

    public void add_buttonBack(ActionEvent actionEvent) throws IOException {
        ChangeStage.changeStageDo((Stage) this.add_field_firstName.getScene().getWindow(),
                "resources/loginInterface.fxml","Welcome PC");
    }

    //buttons Doc work
    public void docWork_buttonLooking(ActionEvent actionEvent) {
        clientData = FXCollections.observableArrayList();

        sendMessage.lookingForClient(CommandSecond.LOOKING_FOR_CLIENT,
                docWork_fieldFirstName.getText(),
                docWork_fieldSecondName.getText(),
                docWork_fieldCityBirth.getText(),
                docWork_dateBirthday.getEditor().getText());
    }

    public void docWork_buttonAddNewClient(ActionEvent actionEvent) {
        sendMessage.lookingForClient(CommandSecond.ADD_NEW_CLIENT,
                docWork_fieldFirstName.getText(),
                docWork_fieldSecondName.getText(),
                docWork_fieldCityBirth.getText(),
                docWork_dateBirthday.getEditor().getText());
    }

    public void docWork_buttonAddNewVisit(ActionEvent actionEvent) throws IOException {
        ChangeStage.changeStageDoWithoutClose((Stage) this.docWork_fieldCityBirth.getScene().getWindow(),
                "resources/analysisInterface.fxml","Visit PC");
    }

    public void docWork_buttonShowResult(ActionEvent actionEvent) {

    }

    public void docWork_buttonBack(ActionEvent actionEvent) throws IOException {
        ChangeStage.changeStageDo((Stage) this.docWork_fieldCityBirth.getScene().getWindow(),
                "resources/loginInterface.fxml","Welcome PC");
    }


    public void analy_send(ActionEvent actionEvent) {
        sendMessage.sendAnalysis(
                analy_fieldFirstName.getText(),
                analy_fieldSecondName.getText(),
                analy_fieldDiagnose.getText(),
                analy_dateVisit.getEditor().getText(),
                analy_fieldCityBirth.getText(),
                analy_fieldDateBirthday.getEditor().getText(),
                String.valueOf(analy_bloodAnalysis.isSelected()),
                String.valueOf(analy_urinAnalysis.isSelected()),
                String.valueOf(analy_frcalAnalysis.isSelected()),
                String.valueOf(analy_smearAnalysis.isSelected()),
                String.valueOf(analy_DNAAnalysis.isSelected()),
                String.valueOf(analy_paterAnalysis.isSelected()));
    }

    public void analy_back(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) analy_bloodAnalysis.getScene().getWindow();
        stage.close();
        //ChangeStage.changeStageDo((Stage) analy_bloodAnalysis.getScene().getWindow(),
        //        "resources/workInterface.fxml", "DoctorPC ");
    }


    //add new user from visit
    public void addNewVisit_no(ActionEvent actionEvent) {
        Stage stage = (Stage) addNewVisit_filedData.getScene().getWindow();
        stage.close();
    }

    public void addNewVisit_yes(ActionEvent actionEvent) {
        Stage stage = (Stage) addNewVisit_filedData.getScene().getWindow();
        stage.close();

        sendMessage.lookingForClient(CommandSecond.ADD_NEW_CLIENT,
                analy_fieldFirstName.getText(),
                analy_fieldSecondName.getText(),
                analy_fieldCityBirth.getText(),
                analy_fieldDateBirthday.getEditor().getText());
    }

    public void sendMessegeForAddNew(Visit visit) {
        firstName = visit.firstName;
        secondName = visit.secondName;
        cityOfBirthday = visit.cityBirthday;
        dateOfBirthday = visit.dateBirthday;
    }
}
