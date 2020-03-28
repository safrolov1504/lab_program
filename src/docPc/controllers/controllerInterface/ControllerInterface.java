package docPc.controllers.controllerInterface;

import docPc.messageWork.getMessage.GetInterface;
import docPc.messageWork.sendMessage.SendInterface;
import docPc.networkCommunication.IService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public abstract class ControllerInterface {
    protected IService messageService;
    protected GetInterface getInterface;
    protected SendInterface sendInterface;

    public void shutdown() {
        System.exit(0);
    }

}
