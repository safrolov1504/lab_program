package server.workWithSQL;

import messageCommons.variosOfMessage.AuthMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequirementSQL {
    private BaseSQLServer sqlServer;
    private static PreparedStatement preparedStatement;

    public RequirementSQL(BaseSQLServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    private ResultSet findUserByLogin(String login) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT Login, Password, FirstName, SecondName, Role, Profession FROM login_doctors where Login = ?");
        preparedStatement.setString(1,login);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return rs;
    }

    //sign in checking
    public User checkLogin(String login, String password) throws SQLException {
        User user = new User(null, null, null, null,null,null);
        ResultSet rs = findUserByLogin(login);
        if(rs.next()){
            if(rs.getString("Login").equals(login) && rs.getString("Password").equals(password))
                user = new User(rs.getString("Login"),rs.getString("Password"),
                        rs.getString("FirstName"),rs.getString("SecondName"),
                        rs.getString("Role"),rs.getString("Profession"));
        }
        return user;
    }

    public AuthMessage addLogin(String login, String password, String firstName, String secondName, String role, String profession) throws SQLException {
        AuthMessage authMessage = new AuthMessage();
        ResultSet rs = findUserByLogin(login);
        if(!rs.next()){
            //написать добавление пользователя
            preparedStatement = sqlServer.connection.prepareStatement(
                    "INSERT INTO login_doctors (Login, Password, FirstName, SecondName, Role, Profession) " +
                            "VALUES ('"+login+"','"+password+"','"+firstName+"','"+secondName+"','"+role+"','"+profession+"');");
            preparedStatement.executeUpdate();
            preparedStatement.addBatch();

            authMessage.firstName = firstName;
            authMessage.secondName = secondName;
            authMessage.message = "A new user successfully added";
        } else {
            authMessage.message = "User with this data is already exist";
        }
        return authMessage;
    }
}
