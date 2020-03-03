package server.workWithMessage.typeMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;
import server.workWithSQL.User;

import java.sql.SQLException;

public class ServiceInterfaceMessage implements DifferentTypeMessage {

    private RequirementSQL requirementSQL;
    private SendMessage sendMessage;

    private AuthMessage authMessage;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String role;
    private String profession;

    public ServiceInterfaceMessage(SendMessage sendMessage, RequirementSQL requirementSQL) {
        this.requirementSQL = requirementSQL;
        this.sendMessage = sendMessage;
    }

    @Override
    public void getIntormation(Message message) throws SQLException {
        //this.message = message;
        switch (message.commandSecond){
            case ADD_NEW:
                addNewUser(message);
                break;
        }
    }

    private void addNewUser(Message message) throws SQLException {
        authMessage = message.authMessage;
        login = authMessage.login;
        password = authMessage.password;
        firstName = authMessage.firstName;
        secondName  = authMessage.secondName;
        role = authMessage.role;
        profession = authMessage.profession;
        if(login == "" || password == "" || firstName == "" || secondName == "" || role == "" || profession == ""){
            authMessage.message="Fill all fields";
            sendMessage.sendNewUser(authMessage);
        } else {
            sendMessage.sendNewUser(requirementSQL.addLogin(login, password, firstName, secondName, role, profession));
        }
    }
}
