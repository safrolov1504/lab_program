package doctorPC.messageWorkWith;

import doctorPC.ChangeStage;
import doctorPC.iterfaceDocktor.Controller;
import doctorPC.messageWorkWith.typeMessage.LoginInterfaceMessage;
import doctorPC.messageWorkWith.typeMessage.ServiceInterfaceMessage;
import doctorPC.messageWorkWith.typeMessage.WorkingInterfaceMessage;
import doctorPC.networkCommunication.IService;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;

public class GetMessage {
    private IService messageService;
    private Controller controller;
    private LoginInterfaceMessage loginInterfaceMessage;
    private ServiceInterfaceMessage serviceInterfaceMessage;
    private WorkingInterfaceMessage workingInterfaceMessage;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public GetMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
        this.loginInterfaceMessage = new LoginInterfaceMessage(controller);
        this.serviceInterfaceMessage = new ServiceInterfaceMessage(controller);
        this.workingInterfaceMessage = new WorkingInterfaceMessage(controller);
    }

    public void sendMessageToWorkWith(String messageIn) {
        System.out.println(messageIn);
        Message message = Message.fromJson(messageIn);
        switch (message.commandFirst){
            case LOGIN:
                loginInterfaceMessage.getIntormation(message);
                break;
            case DOC:
                workingInterfaceMessage.getIntormation(message);
                break;
            case LAB:

                break;
            case SERVICE:
                serviceInterfaceMessage.getIntormation(message);
                break;
        }


    }
}
