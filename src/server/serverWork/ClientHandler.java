package server.serverWork;

import server.workWithMessage.GetMessage;
import server.workWithMessage.SendMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Socket socket;
    private final MyServer myServer;
    private final DataInputStream in;
    private final DataOutputStream out;

    private GetMessage getMessage;
    private SendMessage sendMessage;

    public ClientHandler(Socket socket, MyServer myServer) throws IOException {

        this.socket = socket;
        this.myServer = myServer;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.getMessage = new GetMessage(this);
        this.sendMessage = new SendMessage(this);

        new Thread(() -> {
            try {
                String clientMessage = in.readUTF();
                //ниже тест!!!!
                getMessage.workWithInformation(clientMessage);
                sendMessage.sendTest();
                //System.out.println(clientMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(String message)  {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            //MyServer.admin.error("Failed to send message to user " + clientName + ": "+ message);
            //System.err.println("Failed to send message to user " + clientName + ": "+ message);
            e.printStackTrace();
        }
    }

    private void CloseConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
