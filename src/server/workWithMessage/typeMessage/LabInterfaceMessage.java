package server.workWithMessage.typeMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.Lab;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabInterfaceMessage implements DifferentTypeMessage{
    private SendMessage sendMessage;
    private RequirementSQL requirementSQL;
    private String nameAnalyses1;
    private String analyses1;
    private String nameAnalyses2;
    private String analyses2;
    private Lab lab;
    private String idAnalyses;


    public LabInterfaceMessage(SendMessage sendMessage, RequirementSQL requirementSQL) {
        this.requirementSQL = requirementSQL;
        this.sendMessage = sendMessage;
    }


    @Override
    public void getInformation(Message message) throws SQLException {
        Lab lab = message.lab;
        ResultSet resultSet;
        nameAnalyses1 = lab.nameAnalyses1;
        nameAnalyses2 = lab.nameAnalyses2;
        switch (message.commandSecond){
            case LAB_SEND:
                resultSet = requirementSQL.checkNewAnalyses(nameAnalyses1, nameAnalyses2);
                sendMessage.sendResultAnalyses(lab, message.commandSecond, resultSet);
                break;
            case LAB_READY:
                resultSet = requirementSQL.checkReadyAnalyses(nameAnalyses1, nameAnalyses2);
                sendMessage.sendResultAnalyses(lab,message.commandSecond, resultSet);
                break;
            case LAB_UPDATE:
                idAnalyses = lab.idAnalyses;
                analyses1 = lab.analyses1;
                analyses2 = lab.analyses2;
                requirementSQL.updateAnalyses(idAnalyses, nameAnalyses1,analyses1,nameAnalyses2,analyses2);
                //отправить ответ в случае удачного обновления
                break;
        }
    }

}
