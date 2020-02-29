package server.serverWork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Socket socket;
    private final MyServer myServer;
    private final DataInputStream in;
    private final DataOutputStream out;

    public ClientHandler(Socket socket, MyServer myServer) throws IOException {

        this.socket = socket;
        this.myServer = myServer;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                String clientMessage = in.readUTF();
                System.out.println(clientMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void CloseConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
