package doctorPC.iterfaceDocktor;

import doctorPC.networkCommunication.IService;
import doctorPC.networkCommunication.MyServerDoctor;
import doctorPC.messageWorkWith.SendMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
    public @FXML GridPane workBox;



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
            this.sendMessage = new SendMessage(this.messageService,this);
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


}
