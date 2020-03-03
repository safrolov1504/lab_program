package doctorPC.iterfaceDocktor;

import doctorPC.messageWorkWith.SendMessage;
import doctorPC.networkCommunication.IService;
import doctorPC.networkCommunication.MyServerDoctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerWorkDoctor implements Initializable {

    public @FXML AnchorPane window;
    //Login window
    public @FXML VBox loginBox;
    public @FXML TextField textField_login;
    public @FXML PasswordField testField_pass;
    public @FXML Button button_signIn;


    //Working window
    public @FXML GridPane workBox;


    private IService messageService;
    //private GetMessage getMessage;
    private SendMessage sendMessage;

    public void shutdown() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void button_signIn(ActionEvent actionEvent) {
        sendMessage.checkLogin(textField_login.getText(),testField_pass.getText());
    }
}
