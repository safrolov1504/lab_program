package docPc.controllers.controllerInterface;


import docPc.messageWork.getMessage.Get_login;
import docPc.messageWork.sendMessage.Send_login;
import docPc.networkCommunication.IService;
import docPc.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin extends ControllerInterface implements Initializable {

    public @FXML VBox loginBox;
    public @FXML TextField textField_login;
    public @FXML PasswordField testField_pass;
    public @FXML Button button_signIn;

    //private IService messageService;
    private Send_login send__loginInterfaceMessage;
    private Get_login get_login;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize login");
        messageService = App.getController().getMessageService();

        this.get_login = new Get_login(this);
        this.send__loginInterfaceMessage = new Send_login(messageService);

        App.getController().setGet_login(this.get_login);

//        stage = (Stage) this.button_signIn.getScene().getWindow();
//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                System.exit(0);
//            }
//        });
    }

    //button sign in
    public void button_signIn(ActionEvent actionEvent) {
        System.out.println(textField_login.getText()+testField_pass.getText());
        send__loginInterfaceMessage.checkLogin(textField_login.getText(),testField_pass.getText());

    }

}
