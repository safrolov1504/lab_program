package doctorPC.iterfaceDocktor;

import doctorPC.networkCommunication.IService;
import doctorPC.networkCommunication.MyServerDoctor;
import doctorPC.workWithMessage.GetMessage;
import doctorPC.workWithMessage.SendMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Login window
    public @FXML AnchorPane loginWindow;
    public @FXML TextField textField_login;
    public @FXML PasswordField testField_pass;
    public @FXML Button button_signIn;

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
        sendMessage.sendLogin("test");
    }
}
