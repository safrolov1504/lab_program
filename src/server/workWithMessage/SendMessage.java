package server.workWithMessage;

import server.serverWork.ClientHandler;

public class SendMessage {
    private ClientHandler clientHandler;

    public SendMessage(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void sendTest(){
        clientHandler.sendMessage("test from service to client");
    }
}
