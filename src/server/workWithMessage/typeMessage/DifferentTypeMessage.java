package server.workWithMessage.typeMessage;

import messageCommons.Message;

import java.sql.SQLException;

public interface DifferentTypeMessage {

    public void getInformation(Message message) throws SQLException;
}
