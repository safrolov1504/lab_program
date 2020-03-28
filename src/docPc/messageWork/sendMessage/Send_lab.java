package docPc.messageWork.sendMessage;

import docPc.networkCommunication.IService;
import javafx.scene.control.Alert;
import messageCommons.Message;
import messageCommons.variosOfMessage.Lab;

import java.util.ArrayList;

public class Send_lab extends SendInterface{
    private IService messageService;
    private Alert alert;
    private String analyses1;
    private String analyses2;

    public Send_lab(IService messageService, String analyses1, String analyses2) {
        this.messageService = messageService;
        this.analyses1 = analyses1;
        this.analyses2 = analyses2;
    }

    public void sendRequestAddNewAnalyses() {
        Lab lab = new Lab();
        lab.nameAnalyses1  = analyses1;
        lab.nameAnalyses2 = analyses2;
        Message message = Message.creatLabRequest(lab);
        messageService.sendMessage(message.toJson());
    }

    public void sendRequestAddReadyAnalyses() {
        Lab lab = new Lab();
        lab.nameAnalyses1 = analyses1;
        lab.nameAnalyses2 = analyses2;
        Message message = Message.creatLabReadyAnalyses(lab);
        messageService.sendMessage(message.toJson());
    }

    public void sendUpdate(ArrayList<Lab> labs) {
        //Lab lab = new Lab();
        Message message;
        for (Lab lab:labs) {
            lab.nameAnalyses1 = analyses1;
            lab.nameAnalyses2 = analyses2;
            message = Message.creatLabUpdate(lab);
            messageService.sendMessage(message.toJson());
            //System.out.println("here: " + message.toJson());
        }
    }
}
