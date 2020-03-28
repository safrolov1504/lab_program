package docPc.controllers;


import docPc.messageWork.getMessage.*;
import docPc.networkCommunication.IService;
import docPc.networkCommunication.MyServerDoctor;
import javafx.scene.control.TableView;
import messageCommons.Message;
import messageCommons.variosOfMessage.Client;

public class Controller {
    //connection
    private IService messageService;
    public IService getMessageService() {
    return messageService;
}
    public Controller() {
        this.messageService = new MyServerDoctor();
    }


    //get message
    private Get_login get_login;
    private Get_service get_service;
    //private Get_doc get_doc;
    private GetInterface get_doc;
    private Get_analyse get_analyse;
    private Get_lab get_lab;

    public void setGet_login(Get_login get_login) {
        this.get_login = get_login;
    }
    public void setGet_service(Get_service get_service) {
        this.get_service = get_service;
    }
    public void setGet_doc(GetInterface get_doc) {
        this.get_doc = get_doc;
    }
    public void setGet_analyse(Get_analyse get_analyse) {
        this.get_analyse = get_analyse;
    }
    public void setGet_lab(Get_lab get_lab) {
        this.get_lab = get_lab;
    }

    //для передачи имени врача
    private String firstName;
    private String secondName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    //для передачи сообщения о результатах
    private Message message;
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }


    //для передачи данных клиента в результаты
    private Client client;
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    //для передачи выделеной информации в таблице
    private TableView.TableViewSelectionModel<Client> selectionModel;
    public TableView.TableViewSelectionModel<Client> getSelectionModel() {
        return selectionModel;
    }
    public void setSelectionModel(TableView.TableViewSelectionModel<Client> selectionModel) {
        this.selectionModel = selectionModel;
    }

    public void shutdown() {
        System.exit(0);
    }

    public void sendMessageToWorkWith(String messageIn) {
        System.out.println(messageIn);
        Message message = Message.fromJson(messageIn);
        switch (message.commandFirst){
            case LOGIN:
                get_login.getInformation(message);
                break;
            case DOC:
                get_doc.getInformation(message);
                break;
            case ANALYSE:
                get_analyse.getInformation(message);
                break;
            case SERVICE:
                get_service.getInformation(message);
                break;
            case LAB:
                get_lab.getInformation(message);
                break;
            case RESULT:
                get_doc.getInformation(message);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message.commandFirst);
        }


    }
}
