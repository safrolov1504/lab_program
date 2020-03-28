package docPc.controllers.controllerInterface;

import docPc.controllers.ChangeStage;
import docPc.messageWork.getMessage.Get_service;
import docPc.messageWork.sendMessage.Send_service;
import docPc.networkCommunication.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import docPc.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerService extends ControllerInterface implements Initializable {
    public @FXML Button add_button_addNewDoctor;
    public @FXML Button add_button_addNewLab;

    public @FXML TextField add_field_login;
    public @FXML PasswordField add_field_pass;
    public @FXML TextField add_field_firstName;
    public @FXML TextField add_field_secondName;
    public @FXML TextField add_field_profName;

//    private IService messageService;
    private Send_service send_service;
    private Get_service get_service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize service");
        messageService = App.getController().getMessageService();

        this.get_service = new Get_service(this);
        this.send_service = new Send_service(messageService);

        App.getController().setGet_service(get_service);
    }

    //buttons service
    public void add_button_addNewDoctor(ActionEvent actionEvent) {
        addNewUser("doctor");
    }

    public void add_button_addNewLab(ActionEvent actionEvent) {
        addNewUser("lab");
    }

    private void addNewUser(String typeUser) {
        send_service.addNewUser(typeUser,
                add_field_login.getText(),
                add_field_pass.getText(),
                add_field_firstName.getText().toLowerCase(),
                add_field_secondName.getText().toLowerCase(),
                add_field_profName.getText().toLowerCase());
    }

    public void add_buttonBack(ActionEvent actionEvent) throws IOException {
        ChangeStage.changeStageDo((Stage) this.add_field_firstName.getScene().getWindow(),
                "/docPc/resources/loginInterface.fxml","Welcome PC");
    }
}
