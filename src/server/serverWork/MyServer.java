package server.serverWork;

import server.serverWork.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private static final int PORT = 8189;

    public MyServer() {
        System.out.println("Server is running");
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            //здесь стартует база данных с логином
            //!!!!!!!

            while (true){
                System.out.println("Waiting for client!!");
                Socket socket = serverSocket.accept();
                System.out.println("Waiting is connected");
                new ClientHandler(socket,this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
