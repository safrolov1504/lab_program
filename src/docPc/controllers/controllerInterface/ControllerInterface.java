package docPc.controllers.controllerInterface;

import docPc.messageWork.getMessage.GetInterface;
import docPc.messageWork.sendMessage.SendInterface;
import docPc.networkCommunication.IService;

import java.io.IOException;

public abstract class ControllerInterface {
    protected IService messageService;
    protected GetInterface getInterface;
    protected SendInterface sendInterface;

    public SendInterface getSendInterface() {
        return sendInterface;
    }

    public void shutdown() {
        try {
            messageService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
