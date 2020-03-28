package server.workWithMessage.typeMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.Client;
import messageCommons.variosOfMessage.Visit;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultInterfaceMessage implements DifferentTypeMessage {
    private SendMessage sendMessage;
    private RequirementSQL requirementSQL;

    private Client client;
    private Visit visit;
    private String firstName;
    private String secondName;
    private String cityBirthday;
    private String dateBirthday;
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


    public ResultInterfaceMessage(SendMessage sendMessage, RequirementSQL requirementSQL) {
        this.requirementSQL =requirementSQL;
        this.sendMessage = sendMessage;
    }

    @Override
    public void getInformation(Message message) throws SQLException {
        client = message.client;
        firstName = client.firstName;
        secondName = client.secondName;
        cityBirthday = client.cityOfBirthday;
        dateBirthday = client.dateOfBirthday;
        dateVisit = client.dateVisit;


        ResultSet resultSet = requirementSQL.getVisit(firstName,secondName,cityBirthday,dateBirthday, dateVisit);
        visit = new Visit();

        visit.firstName = resultSet.getString("clients.FirstName");
        visit.secondName = resultSet.getString("clients.SecondName");
        visit.cityBirthday = resultSet.getString("CityOfBirthday");
        visit.dateBirthday = resultSet.getString("DateOfBirthday");
        visit.diagnose = resultSet.getString("Diagnoses");
        visit.dateVisit = resultSet.getString("dateVisit");

        visit.docFirstName = resultSet.getString("login_staff.FirstName");
        visit.docSecondName = resultSet.getString("login_staff.SecondName");

        visit.bloodAnalysis = resultSet.getString("bloodAnalyse");
        visit.urinAnalysis = resultSet.getString("urinaAnalyse");
        visit.frcalAnalysis = resultSet.getString("ficalAnalyse");
        visit.smearAnalysis = resultSet.getString("smearAnalyse");
        visit.dNAAnalysis = resultSet.getString("DNAAnalyse");
        visit.paterAnalysis = resultSet.getString("paterAnalyse");

        //добавить проверку готовы анализы или нет
        if(visit.bloodAnalysis.equals("true")||visit.urinAnalysis.equals("true")||
                visit.frcalAnalysis.equals("true")||visit.smearAnalysis.equals("true")||
                visit.dNAAnalysis.equals("true")||visit.paterAnalysis.equals("true")){
            visit.message = "Analyses are not ready";
            message = Message.creatResultNOk(visit);
            sendMessage.sendResult(message.toJson());
        } else {
            message = Message.creatResultOk(visit);
            sendMessage.sendResult(message.toJson());
            //System.out.println(message.toJson());
        }
    }
}
