package server.workWithSQL;

import messageCommons.variosOfMessage.AuthMessage;
import server.workWithMessage.SendMessage;

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

    //add a new user
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

    public boolean addClient(String firstName, String secondName, String cityOfBirthday, String dateOfBirthday) throws SQLException {
        ResultSet rs = checkClient(firstName,secondName,cityOfBirthday,dateOfBirthday);
        if(rs.next()){
            return false;
        } else {
            preparedStatement = sqlServer.connection.prepareStatement
                    ("INSERT INTO clients (FirstName, SecondName, CityOfBirthday, DateOfBirthday) VALUES" +
                            "(?, ?, ?, ?)");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,secondName);
            preparedStatement.setString(3,cityOfBirthday);
            preparedStatement.setString(4,dateOfBirthday);
            preparedStatement.executeUpdate();
            preparedStatement.addBatch();
            return true;
        }
    }

    public ResultSet checkClient(String firstName, String secondName, String cityOfBirthday, String dateOfBirthday) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT FirstName, SecondName, CityOfBirthday, DateOfBirthday FROM clients " +
                        "where FirstName = ? AND SecondName = ? AND CityOfBirthday = ? AND DateOfBirthday = ?");
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,secondName);
        preparedStatement.setString(3,cityOfBirthday);
        preparedStatement.setString(4,dateOfBirthday);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return rs;
    }

    public ResultSet lookingForClint(String firstName, String secondName) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT FirstName, SecondName, CityOfBirthday, DateOfBirthday FROM clients " +
                        "where FirstName = ? AND SecondName = ?");
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,secondName);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return rs;        
    }
}
