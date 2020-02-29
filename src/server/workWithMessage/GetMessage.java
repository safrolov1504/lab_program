package server.workWithMessage;

import server.serverWork.ClientHandler;


public class GetMessage {
    private ClientHandler clientHandler;

    public GetMessage(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void workWithInformation(String clientMessage) {
        System.out.println(clientMessage);
    }
}
