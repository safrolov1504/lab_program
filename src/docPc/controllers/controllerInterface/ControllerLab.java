package docPc.controllers.controllerInterface;

import docPc.App;
import docPc.controllers.ChangeStage;
import docPc.messageWork.getMessage.Get_lab;
import docPc.messageWork.sendMessage.Send_lab;
import docPc.networkCommunication.IService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.variosOfMessage.Lab;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerLab extends ControllerInterface implements Initializable {
//    private IService messageService;
    private Get_lab get_lab;
    private Send_lab send_lab;
    private Message message;
    private String analyses1;
    private String analyses2;
    private ArrayList<Lab> labs = new ArrayList<>();
    private ArrayList<Lab> labsSend = new ArrayList<>();
    private Random random = new Random();

    public @FXML Label labLabel;
    public @FXML TableView<Lab> lab_table;
    public @FXML TableColumn<Lab,String> lab_table_clientNumber;
    public @FXML TableColumn<Lab,String> lab_table_dateVisit;
    public @FXML TableColumn<Lab,String> lab_analyses1;
    public @FXML TableColumn<Lab,String> lab_analyses2;
    public @FXML TableColumn<Lab,String> lab_table_analysisNumber;
    private Alert alert;

    public ObservableList<Lab> resultData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize doc");
        messageService = App.getController().getMessageService();

        this.message = App.getController().getMessage();
        labLabel.setText("Lab: " + App.getController().getFirstName()+" " + App.getController().getSecondName());
        //System.out.println(message.toJson());
        analyses1 = message.authMessage.lab_analyses1;
        analyses2 = message.authMessage.lab_analyses2;

        this.get_lab = new Get_lab(this, analyses1,analyses2);
        this.send_lab = new Send_lab(messageService,analyses1,analyses2);
        App.getController().setGet_lab(get_lab);

        setColumns();
    }

    public void labCheckNewAnalyses(ActionEvent actionEvent) {
        resultData.clear();
        send_lab.sendRequestAddNewAnalyses();
    }

    public void labShowAllResult(ActionEvent actionEvent) {
        resultData.clear();
        send_lab.sendRequestAddReadyAnalyses();
    }

    public void labSendAllAnalyses(ActionEvent actionEvent) {
        labs.clear();
        labs.addAll(resultData);
        if (labs.isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error");
            alert.setContentText("Choose at least one analyses");
            alert.showAndWait();
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            workWithListResult();

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Result is ready");
            alert.setContentText("Analyses were done successfully");
            alert.showAndWait();
        }
    }



    public void labSendChosenAnalyses(ActionEvent actionEvent) {
        //send_lab.
        labs.clear();
        Lab lab = lab_table.getSelectionModel().getSelectedItem();
        if (lab == null) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error");
            alert.setContentText("Choose at least one analyses");
            alert.showAndWait();
        } else {
            labs.add(lab);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Result is ready");
            alert.setContentText("Analyses were done successfully");
            alert.showAndWait();

            workWithListResult();
        }
    }

    private void workWithListResult() {
        for (Lab lab:labs) {
             if (!lab.analyses1.equals("true") && !lab.analyses2.equals("true")) {
                continue;
            } else {
                if (lab.analyses1.equals("true")) {
                    lab.analyses1 = String.valueOf(random.nextInt(100+1));
                }
                if (lab.analyses2.equals("true")) {
                    lab.analyses2 = String.valueOf(random.nextInt(100+1));
                }
            }
        }
        send_lab.sendUpdate(labs);
    }

    public void labButtonClean(ActionEvent actionEvent) {
        resultData.clear();
        lab_table.setItems(resultData);
    }

    public void labButtonBack(ActionEvent actionEvent) {
        ChangeStage.changeStageDo((Stage) this.lab_table.getScene().getWindow(),
                "/docPc/resources/loginInterface.fxml","Welcome PC");
    }

    private void setColumns(){
        //this.lab_table = new TableView<>(FXCollections.observableArrayList());
        //this.lab_analyses1.
        this.lab_table_analysisNumber.setCellValueFactory(new  PropertyValueFactory<Lab,String>("idAnalyses"));
        this.lab_table_clientNumber.setCellValueFactory(new PropertyValueFactory<Lab,String>("idClient"));
        this.lab_table_dateVisit.setCellValueFactory(new PropertyValueFactory<Lab,String>("dateVisit"));

        //this.lab_analyses1 = new TableColumn<>(message.authMessage.lab_analyses1);
        this.lab_analyses1.setCellValueFactory(new PropertyValueFactory<Lab,String>("analyses1"));
        //this.lab_analyses2 = new TableColumn<>(message.authMessage.lab_analyses2);
        this.lab_analyses2.setCellValueFactory(new PropertyValueFactory<Lab,String>("analyses2"));
        //this.lab_table.getColumns().addAll(this.lab_table_clientNumber, this.lab_table_dateVisit,this.lab_analyses1,this.lab_analyses2);
    }


}
