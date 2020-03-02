package doctorPC.messageWorkWith;

import doctorPC.iterfaceDocktor.Controller;
import doctorPC.networkCommunication.IService;

public class GetMessage {
    private IService messageService;
    private Controller controller;

    public GetMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
    }

    public void sendMessageToWorkWith(String message) {
        System.out.println(message);
    }
}
