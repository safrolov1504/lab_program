package server.workWithMessage.typeMessage;

import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import server.workWithMessage.SendMessage;
import server.workWithSQL.RequirementSQL;

import java.sql.SQLException;

public class LoginInterfaceMessage implements DifferentTypeMessage {

    private Message message;
    private SendMessage sendMessage;
    private RequirementSQL requirementSQL;

    private AuthMessage authMessage = new AuthMessage();
    private String login = new String();
    private String password = new String();

    public LoginInterfaceMessage(SendMessage sendMessage, RequirementSQL requirementSQL) {
        this.sendMessage = sendMessage;
        this.requirementSQL = requirementSQL;
    }

    @Override
    public void getInformation(Message message) throws SQLException {
        switch (message.commandSecond){
            case AUTH_MESSAGE:
                checkAuth(message);
                break;
        }
    }

    private void checkAuth(Message message) throws SQLException {
        authMessage = message.authMessage;
        login = authMessage.login;
        password = authMessage.password;
        //requirementSQL.checkLogin(login,password);
        sendMessage.sendAuthor(requirementSQL.checkLogin(login,password));
    }


}
