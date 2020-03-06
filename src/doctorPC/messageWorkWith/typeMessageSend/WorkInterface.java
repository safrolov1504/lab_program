package doctorPC.messageWorkWith.typeMessageSend;

import doctorPC.networkCommunication.IService;

public class WorkInterface {
    private IService messageService;

    public WorkInterface(IService messageService) {
        this.messageService = messageService;
    }
}
