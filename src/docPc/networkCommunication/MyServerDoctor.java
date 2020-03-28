package docPc.networkCommunication;

import docPc.*;
import java.io.InputStream;
import java.util.Properties;

public class MyServerDoctor implements IService {
    //for communication with server
    private static final String HOST_ADDRESS_PROP = "server.address";
    private static final String HOST_PORT_PROP = "server.port";
    private String hostAddress;
    private int hostPort;

    private Network network;

    public MyServerDoctor() {
        //есть какая то камуникация с контроллером 
        initialise();
    }


    private void initialise() {
        readProperties();
        startConnectionToServer();
        //this.getMessage = new GetMessage(this,controller);
    }

    private void readProperties() {
//        Properties serverProperty = new Properties();
//        try(InputStream inputStream = getClass().getResourceAsStream("/Users/safrolov/Documents/university/java_program/lab_program/src/docPc/resources/application.properties")){
//            serverProperty.load(inputStream);
            hostAddress = "localhost";
            hostPort = 8189;
//        } catch (IOException e) {
//            new RuntimeException("Failed to read application.properties file", e);
//        }
    }

    private void startConnectionToServer() {
        this.network = new Network(hostAddress, hostPort, this);
    }

    @Override
    public void sendMessage(String message) {
        network.sendMessage(message);
    }

    @Override
    public void processRetrievedMessage(String message) {
        App.getController().sendMessageToWorkWith(message);
        //controller.sendMessageToWorkWith(message);
    }
}
