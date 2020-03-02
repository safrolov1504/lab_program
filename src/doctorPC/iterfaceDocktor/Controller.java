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


    //Working window
    public @FXML GridPane workBox;


    private IService messageService;
    //private GetMessage getMessage;
    private SendMessage sendMessage;

    public void shutdown() {

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

    public void button_signIn(ActionEvent actionEvent) {
        sendMessage.checkLogin(textField_login.getText(),testField_pass.getText());
    }
}
