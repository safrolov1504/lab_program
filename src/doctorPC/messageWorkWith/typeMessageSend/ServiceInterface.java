package doctorPC.messageWorkWith.typeMessageSend;

import doctorPC.networkCommunication.IService;

public class ServiceInterface {
    private IService messageService;

    public ServiceInterface(IService messageService) {
        this.messageService = messageService;
    }

}
