package server.serverWork;

import messageCommons.CommandFirst;
import messageCommons.Message;
import server.workWithMessage.GetMessage;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;
import server.workWithSQL.SQLServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private final Socket socket;
    private final MyServer myServer;
    private final DataInputStream in;
    private final DataOutputStream out;

    private GetMessage getMessage;
    private SendMessage sendMessage;

    public ClientHandler(Socket socket, MyServer myServer, SQLServer sqlServer) throws IOException {
        //creat the connection
        this.socket = socket;
        this.myServer = myServer;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        this.sendMessage = new SendMessage(this);
        this.getMessage = new GetMessage(this,sqlServer,sendMessage);


        //connection was created. Start to work with getting message

        getMessage();
    }

    public void getMessage(){
        new Thread(() -> {
            try {
                while (true) {
                    String clientMessage = in.readUTF();
                    if(!getMessage.workWithInformation(clientMessage)){
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(String message)  {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
