package doctorPC.messageWorkWith;

import doctorPC.iterfaceDocktor.Controller;
import doctorPC.messageWorkWith.typeMessage.AnalysInterfaceMessage;
import doctorPC.messageWorkWith.typeMessage.LoginInterfaceMessage;
import doctorPC.messageWorkWith.typeMessage.ServiceInterfaceMessage;
import doctorPC.messageWorkWith.typeMessage.WorkingInterfaceMessage;
import doctorPC.networkCommunication.IService;
import messageCommons.Message;

public class GetMessage {
    private IService messageService;
    private Controller controller;
    private LoginInterfaceMessage loginInterfaceMessage;
    private ServiceInterfaceMessage serviceInterfaceMessage;
    private WorkingInterfaceMessage workingInterfaceMessage;
    private AnalysInterfaceMessage labInterfaceMessage;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public GetMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
        this.loginInterfaceMessage = new LoginInterfaceMessage(controller);
        this.serviceInterfaceMessage = new ServiceInterfaceMessage(controller);
        this.workingInterfaceMessage = new WorkingInterfaceMessage(controller);
        this.labInterfaceMessage = new AnalysInterfaceMessage(controller);
    }

    public void sendMessageToWorkWith(String messageIn) {
        System.out.println(messageIn);
        Message message = Message.fromJson(messageIn);
        switch (message.commandFirst){
            case LOGIN:
                loginInterfaceMessage.getInformation(message);
                break;
            case DOC:
                workingInterfaceMessage.getInformation(message);
                break;
            case LAB:
                labInterfaceMessage.getInformation(message);
                break;
            case SERVICE:
                serviceInterfaceMessage.getInformation(message);
                break;
        }


    }
}
