package docPc.controllers.controllerInterface;

import docPc.controllers.ChangeStage;
import docPc.messageWork.getMessage.Get_doc;
import docPc.messageWork.sendMessage.Send_doc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import docPc.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import messageCommons.CommandSecond;
import messageCommons.variosOfMessage.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDoc extends ControllerInterface implements Initializable {
//    private IService messageService;
//    private Get_doc getInterface;
//    private Send_doc sendInterface;

    public @FXML Label docLabel;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize doc");
        messageService = App.getController().getMessageService();

        this.getInterface = new Get_doc(this);
        this.sendInterface = new Send_doc(messageService);

        App.getController().setGet_doc(getInterface);
        docLabel.setText("Doctor: " + App.getController().getFirstName()+" " + App.getController().getSecondName());
        setColumns();
    }


    //buttons Doc work
    public void docWork_buttonLooking(ActionEvent actionEvent) {
        clientData = FXCollections.observableArrayList();

        sendInterface.lookingForClient(CommandSecond.LOOKING_FOR_CLIENT,
                docWork_fieldFirstName.getText().toLowerCase(),
                docWork_fieldSecondName.getText().toLowerCase(),
                docWork_fieldCityBirth.getText().toLowerCase(),
                docWork_dateBirthday.getEditor().getText().toLowerCase());
    }

    public void docWork_buttonShowAllVisits(ActionEvent actionEvent) {
        sendInterface.lookingForClientAll();
    }

    public void docWork_buttonAddNewClient(ActionEvent actionEvent) {
        sendInterface.lookingForClient(CommandSecond.ADD_NEW_CLIENT,
                docWork_fieldFirstName.getText().toLowerCase(),
                docWork_fieldSecondName.getText().toLowerCase(),
                docWork_fieldCityBirth.getText().toLowerCase(),
                docWork_dateBirthday.getEditor().getText().toLowerCase());
    }

    public void docWork_buttonAddNewVisit(ActionEvent actionEvent) {
        Client client = docWork_table.getSelectionModel().getSelectedItem();
        App.getController().setClient(client);
        App.getController().setSelectionModel(docWork_table.getSelectionModel());
        ChangeStage.changeStageDo((Stage) this.docWork_fieldCityBirth.getScene().getWindow(),
                    "/docPc/resources/analysisInterface.fxml","Visit PC",
                    false,false);
    }

    public void docWork_buttonShowResult(ActionEvent actionEvent) {
        Client client = docWork_table.getSelectionModel().getSelectedItem();
        client.firstName = client.firstName.toLowerCase();
        client.secondName = client.secondName.toLowerCase();
        client.cityOfBirthday = client.cityOfBirthday.toLowerCase();
        sendInterface.getResult(client);
    }

    public void docWork_buttonClear(ActionEvent actionEvent) {
        clientData.clear();
        docWork_table.setItems(clientData);
    }

    public void docWork_buttonBack(ActionEvent actionEvent) {
        ChangeStage.changeStageDo((Stage) this.docWork_fieldCityBirth.getScene().getWindow(),
                    "/docPc/resources/loginInterface.fxml","Welcome PC",
                    true,true);
    }

    private void setColumns(){
        this.docWork_table_firstName.setCellValueFactory(new PropertyValueFactory<Client,String>("firstName"));
        this.docWork_table_secondName.setCellValueFactory(new PropertyValueFactory<Client,String>("secondName"));
        this.docWork_table_city.setCellValueFactory(new PropertyValueFactory<Client,String>("cityOfBirthday"));
        this.docWork_table_dateBirth.setCellValueFactory(new PropertyValueFactory<Client,String>("dateOfBirthday"));
        this.docWork_table_dateVisit.setCellValueFactory(new PropertyValueFactory<Client,String>("dateVisit"));
    }



}
