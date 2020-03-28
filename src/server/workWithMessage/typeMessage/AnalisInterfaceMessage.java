package server.workWithMessage.typeMessage;

import messageCommons.CommandSecond;
import messageCommons.Message;
import messageCommons.variosOfMessage.Visit;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalisInterfaceMessage implements DifferentTypeMessage {
    private RequirementSQL requirementSQL;
    private SendMessage sendMessage;


    private Visit visit;
    private String firstName;
    private String secondName;
    private String docFirstName;
    private String docSecondName;
    private String diagnose;
    private String dateVisit;
    private String bloodAnalysis;
    private String urinAnalysis;
    private String frcalAnalysis;
    private String smearAnalysis;
    private String dNAAnalysis;
    private String paterAnalysis;
    private String cityBirthday;
    private String dateBirthday;

    public AnalisInterfaceMessage(SendMessage sendMessage, RequirementSQL requirementSQL) {
        this.requirementSQL = requirementSQL;
        this.sendMessage = sendMessage;
    }

    @Override
    public void getInformation(Message message) throws SQLException {
        switch (message.commandSecond){
            case VISIT:
                startVisit(message);
                break;
        }
    }

    private void startVisit(Message message) throws SQLException {
        visit = message.visit;
        firstName = visit.firstName;
        secondName = visit.secondName;
        docFirstName = visit.docFirstName;
        docSecondName = visit.docSecondName;
        diagnose = visit.diagnose;
        dateVisit = visit.dateVisit;
        bloodAnalysis = visit.bloodAnalysis;
        urinAnalysis = visit.urinAnalysis;
        frcalAnalysis = visit.frcalAnalysis;
        smearAnalysis = visit.smearAnalysis;
        dNAAnalysis = visit.dNAAnalysis;
        paterAnalysis = visit.paterAnalysis;
        cityBirthday = visit.cityBirthday;
        dateBirthday = visit.dateBirthday;

            ResultSet rs = requirementSQL.checkClient(firstName, secondName,cityBirthday,dateBirthday);
            if (rs.isBeforeFirst()) {
                visit.message = "Analysis was sent to lab";
                sendMessage.sendVisit(visit, CommandSecond.VISIT_OK);
                requirementSQL.addNewVisit(firstName,secondName,cityBirthday,dateBirthday, diagnose, dateVisit,
                        docFirstName,docSecondName,
                        bloodAnalysis,urinAnalysis,frcalAnalysis,smearAnalysis,dNAAnalysis,paterAnalysis);
            } else {
                visit.message = "There is no this client";
                sendMessage.sendVisit(visit, CommandSecond.VISIT_ADDNEW);
            }
//        }


    }
}
