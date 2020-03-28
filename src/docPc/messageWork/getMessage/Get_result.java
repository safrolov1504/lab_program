package docPc.messageWork.getMessage;

import docPc.controllers.controllerInterface.ControllerResult;
import messageCommons.Message;

public class Get_result {
    private ControllerResult controllerResult;

    public Get_result(ControllerResult controllerResult) {
        this.controllerResult = controllerResult;
    }

    public void getInformation(Message message) {
        System.out.println(message);
    }
}
