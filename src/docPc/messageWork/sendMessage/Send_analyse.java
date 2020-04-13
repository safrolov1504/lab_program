package docPc.messageWork.sendMessage;

import docPc.networkCommunication.IService;
import javafx.scene.control.Alert;
import messageCommons.CommandSecond;
import messageCommons.Message;
import messageCommons.variosOfMessage.Client;
import messageCommons.variosOfMessage.Visit;

public class Send_analyse extends SendInterface{
    private IService messageService;
    private Alert alert;

    public Send_analyse(IService messageService) {
        this.messageService = messageService;
    }

    public void sendAnalysis(String firstName, String secondName, String docFirstName, String docSecondName,
                             String diagnose, String dateVisit,
                             String cityBirthday, String dateBirthday,
                             String bloodAnalysis, String urinAnalysis, String frcalAnalysis,
                             String smearAnalysis, String dNAAnalysis, String paterAnalysis) {
        if(firstName.equals("") || secondName.equals("") || diagnose.equals("") || dateBirthday.equals("") ||
                cityBirthday.equals("") ||dateVisit.equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error. Cant send to server");
            alert.setContentText("First name, second name, diagnose, visit date, city of birthday and date of birthday should be filled");
            alert.showAndWait();
        } else if (bloodAnalysis.equals("false") && urinAnalysis.equals("false") && frcalAnalysis.equals("false") &&
                smearAnalysis.equals("false") && dNAAnalysis.equals("false") && paterAnalysis.equals("false")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error. Cant send to server");
            alert.setContentText("Choose at list one of analysis");
            alert.showAndWait();
        } else {
            Visit visit = new Visit();
            visit.firstName = firstName;
            visit.secondName = secondName;
            visit.docFirstName = docFirstName;
            visit.docSecondName = docSecondName;
            visit.diagnose = diagnose;
            visit.dateVisit = dateVisit;
            visit.cityBirthday = cityBirthday;
            visit.dateBirthday = dateBirthday;
            visit.bloodAnalysis = bloodAnalysis;
            visit.urinAnalysis = urinAnalysis;
            visit.frcalAnalysis = frcalAnalysis;
            visit.smearAnalysis = smearAnalysis;
            visit.dNAAnalysis = dNAAnalysis;
            visit.paterAnalysis = paterAnalysis;

            Message message = Message.creatVisit(visit);
            messageService.sendMessage(message.toJson());
        }
    }

    public void sendAnalysisAndClient(Visit visit){
        Client client = new Client();
        Message message;
        client.firstName = visit.firstName;
        client.secondName = visit.secondName;
        client.cityOfBirthday = visit.cityBirthday;
        client.dateOfBirthday = visit.dateBirthday;
        message = Message.creatAddClient(client);
        messageService.sendMessage(message.toJson());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        message = Message.creatVisit(visit);
        messageService.sendMessage(message.toJson());
    }

}
