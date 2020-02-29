package doctorPC.workWithMessage;

import doctorPC.iterfaceDocktor.Controller;
import doctorPC.networkCommunication.IService;

public class SendMessage {
    private IService messageService;
    private Controller controller;

    public SendMessage(IService messageService, Controller controller) {
        this.messageService = messageService;
        this.controller = controller;
    }


    public void sendLogin(String test) {
        messageService.sendMessage(test);
    }
}
