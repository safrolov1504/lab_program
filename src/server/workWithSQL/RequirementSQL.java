package server.workWithSQL;

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

    //sign in checking
    public User checkLogin(String login, String password) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT Login, Password, FirstName, SecondName FROM login_doctors where Login = ?");
        preparedStatement.setString(1,login);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        User user = new User(null, null, null, null);
        if(rs.next()){
            if(rs.getString("Login").equals(login) && rs.getString("Password").equals(password))
                user = new User(rs.getString("Login"),rs.getString("Password"),
                        rs.getString("FirstName"),rs.getString("SecondName"));
        }
        return user;
    }
}
