package doctorPC.iterfaceDocktor;

import doctorPC.messageWorkWith.SendMessage;
import doctorPC.networkCommunication.IService;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControllerMain implements Initializable {
    private IService messageService;
    private SendMessage sendMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}
