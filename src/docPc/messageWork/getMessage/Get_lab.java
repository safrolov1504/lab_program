package docPc.messageWork.getMessage;

import docPc.controllers.controllerInterface.ControllerDoc;
import docPc.controllers.controllerInterface.ControllerLab;
import javafx.scene.control.Alert;
import messageCommons.Message;
import messageCommons.Text;
import messageCommons.variosOfMessage.Client;
import messageCommons.variosOfMessage.Lab;

public class Get_lab implements GetInterface{
    private ControllerLab controllerLab;
    private Alert alert;
    private String idClient;
    private String idAnalysis;
    private String dateVisit;
    private String analyses1;
    private String analyses2;

    public Get_lab(ControllerLab controllerLab, String analyses1, String analyses2) {
        this.controllerLab = controllerLab;
    }

    @Override
    public void getInformation(Message message) {
        //System.out.println(message.toJson());
        this.idAnalysis = message.lab.idAnalyses;
        this.idClient = message.lab.idClient;
        this.dateVisit = message.lab.dateVisit;
        this.analyses1 = message.lab.analyses1;
        this.analyses2 = message.lab.analyses2;
        if(!(analyses1.equals("false") && analyses2.equals("false"))){
            if(analyses1.equals("false")){
                analyses1 = "not need";
            }

            if(analyses2.equals("false")){
                analyses2 = "not need";
            }

            //setColumns();

            Lab lab = new Lab(idAnalysis,idClient,dateVisit,analyses1,analyses2);
            //System.out.println(lab.toString());
            controllerLab.resultData.add(lab);
            controllerLab.lab_table.setItems(controllerLab.resultData);
        }
    }

}
