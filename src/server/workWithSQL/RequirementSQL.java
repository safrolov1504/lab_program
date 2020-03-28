package server.workWithSQL;

import messageCommons.variosOfMessage.AuthMessage;
import messageCommons.variosOfMessage.Visit;
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
                ("SELECT Login, Password, FirstName, SecondName, Role, Profession FROM login_staff where Login = ?");
        preparedStatement.setString(1,login);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return rs;
    }

    //sign in checking
    public User checkLogin(String login, String password) throws SQLException {
        User user = new User(null, null, null, null,null,null,
                null,null);
        ResultSet rs = findUserByLogin(login);
        String role;
        String profession;
        String analyses1 = null;
        String analyses2 = null;
        if(rs.next()){
            if(rs.getString("Login").equals(login) && rs.getString("Password").equals(password)) {
                role = rs.getString("Role");
                profession = rs.getString("Profession");
                if (role.equals("lab")) {
                    ResultSet rs2 = findAnalysesForLab(profession);
                    analyses1 = rs2.getString("special_1");
                    analyses2 = rs2.getString("special_2");
                }
                user = new User(rs.getString("Login"), rs.getString("Password"),
                        rs.getString("FirstName"), rs.getString("SecondName"),
                        role, profession, analyses1, analyses2);
            }
        }
        return user;
    }

    private ResultSet findAnalysesForLab(String profession) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT special_1, special_2 FROM lab " +
                        "JOIN login_staff ON id_lab = Number " +
                        "WHERE Profession = ?");
        preparedStatement.setString(1,profession);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return rs;
    }

    //add a new user
    public AuthMessage addLogin(String login, String password, String firstName, String secondName, String role, String profession) throws SQLException {
        AuthMessage authMessage = new AuthMessage();
        ResultSet rs = findUserByLogin(login);
        if(!rs.next()){
            //написать добавление пользователя
            preparedStatement = sqlServer.connection.prepareStatement(
                    "INSERT INTO login_staff (Login, Password, FirstName, SecondName, Role, Profession) " +
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



    public ResultSet lookingForClint(String firstName, String secondName, String cityBirthday, String datBirthday) throws SQLException {
        if(cityBirthday.equals("") && datBirthday.equals("")){
            preparedStatement = sqlServer.connection.prepareStatement
                    ("SELECT clients.FirstName, clients.SecondName, clients.CityOfBirthday, clients.DateOfBirthday, visits.dateVisit " +
                            "FROM clients LEFT JOIN visits ON clients.Number = visits.id_name " +
                            "where FirstName = ? AND SecondName = ?");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,secondName);
        } else if(cityBirthday.equals("")){
            preparedStatement = sqlServer.connection.prepareStatement
                    ("SELECT clients.FirstName, clients.SecondName, clients.CityOfBirthday, clients.DateOfBirthday, visits.dateVisit " +
                            "FROM clients LEFT JOIN visits ON clients.Number = visits.id_name " +
                            "where FirstName = ? AND SecondName = ? AND DateOfBirthday = ?");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,secondName);
            preparedStatement.setString(3,datBirthday);
        } else if(datBirthday.equals("")){
            preparedStatement = sqlServer.connection.prepareStatement
                    ("SELECT clients.FirstName, clients.SecondName, clients.CityOfBirthday, clients.DateOfBirthday, visits.dateVisit " +
                            "FROM clients LEFT JOIN visits ON clients.Number = visits.id_name " +
                            "where FirstName = ? AND SecondName = ? AND CityOfBirthday = ?");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,secondName);
            preparedStatement.setString(3,cityBirthday);
        } else {
            preparedStatement = sqlServer.connection.prepareStatement
                    ("SELECT clients.FirstName, clients.SecondName, clients.CityOfBirthday, clients.DateOfBirthday, visits.dateVisit " +
                            "FROM clients LEFT JOIN visits ON clients.Number = visits.id_name " +
                            "where FirstName = ? AND SecondName = ? AND CityOfBirthday = ? AND DateOfBirthday = ?");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,secondName);
            preparedStatement.setString(3,cityBirthday);
            preparedStatement.setString(4,datBirthday);
        }

        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return rs;        
    }

    public ResultSet lookingForClintAll() throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT clients.FirstName, clients.SecondName, clients.CityOfBirthday, clients.DateOfBirthday, visits.dateVisit " +
                        "FROM clients LEFT JOIN visits ON clients.Number = visits.id_name");
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return rs;
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

    public void addNewVisit(String firstName, String secondName, String cityBirthday, String dateBirthday,
                            String diagnose, String dateVisit,
                            String docFirstName, String docSecondName,
                            String bloodAnalysis, String urinAnalysis, String frcalAnalysis,
                            String smearAnalysis, String dNAAnalysis, String paterAnalysis) throws SQLException {
        String idClient = getIdClietnFromDB(firstName,secondName,cityBirthday,dateBirthday);
        String idDoc = getIdDocFromDB(docFirstName,docSecondName);

        preparedStatement = sqlServer.connection.prepareStatement
                ("INSERT INTO visits (ID_NAME, DIAGNOSES, DATEVISIT, BLOODANALYSE, URINAANALYSE, FICALANALYSE, " +
                        "SMEARANALYSE, DNAANALYSE, PATERANALYSE, ID_DOCTOR) VALUES" +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1,idClient);
        preparedStatement.setString(2,diagnose);
        preparedStatement.setString(3,dateVisit);
        preparedStatement.setString(4,bloodAnalysis);
        preparedStatement.setString(5,urinAnalysis);
        preparedStatement.setString(6,frcalAnalysis);
        preparedStatement.setString(7,smearAnalysis);
        preparedStatement.setString(8,dNAAnalysis);
        preparedStatement.setString(9,paterAnalysis);
        preparedStatement.setString(10,idDoc);
        preparedStatement.executeUpdate();
        preparedStatement.addBatch();
    }

    private String getIdDocFromDB(String firstName, String secondName) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT Number FROM login_staff " +
                        "where FirstName = ? AND SecondName = ? ");
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,secondName);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();

        return rs.getString("Number");
    }

    private String getIdClietnFromDB(String firstName, String secondName, String cityBirthday, String dateBirthday) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT Number FROM clients " +
                        "where FirstName = ? AND SecondName = ? AND CityOfBirthday = ? AND DateOfBirthday = ?");
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,secondName);
        preparedStatement.setString(3,cityBirthday);
        preparedStatement.setString(4,dateBirthday);
        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();

        return rs.getString("Number");
    }

    public ResultSet getVisit(String firstName, String secondName, String cityBirthday,
                          String dateBirthday, String dateVisit) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement
                ("SELECT clients.FirstName AS 'clients.FirstName', clients.SecondName as 'clients.SecondName', clients.CityOfBirthday, clients.DateOfBirthday, " +
                        "visits.Diagnoses, visits.dateVisit, " +
                        "login_staff.FirstName AS 'login_staff.FirstName', login_staff.SecondName AS 'login_staff.SecondName', " +
                        "visits.bloodAnalyse, visits.urinaAnalyse, visits.ficalAnalyse, " +
                        "visits.smearAnalyse, visits.DNAAnalyse, visits.paterAnalyse " +
                        "FROM visits " +
                        "JOIN clients ON visits.id_name = clients.Number " +
                        "JOIN login_staff ON visits.id_doctor = login_staff.Number " +
                        "WHERE clients.FirstName = ? AND clients.SecondName = ? " +
                        "AND clients.CityOfBirthday = ? AND clients.DateOfBirthday = ? " +
                        "AND visits.dateVisit = ?");
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,secondName);
        preparedStatement.setString(3,cityBirthday);
        preparedStatement.setString(4,dateBirthday);
        preparedStatement.setString(5,dateVisit);

        ResultSet rs = preparedStatement.executeQuery();
        preparedStatement.addBatch();

        return rs;
    }

    public ResultSet checkNewAnalyses(String analyses1, String analyses2) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement("SELECT id, id_name, dateVisit, " +analyses1+ " AS 'analyses1', "+analyses2+" AS 'analyses2' " +
                "FROM visits WHERE "+analyses1+" = 'true' OR "+analyses2+" = 'true'");
        ResultSet resultSet = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return resultSet;
    }

    public ResultSet checkReadyAnalyses(String analyses1, String analyses2) throws SQLException {
        preparedStatement = sqlServer.connection.prepareStatement("SELECT id, id_name, dateVisit, " +analyses1+ " AS 'analyses1', "+analyses2+" AS 'analyses2' " +
                "FROM visits WHERE "+analyses1+" <> 'true' AND "+analyses2+" <> 'true'");
        ResultSet resultSet = preparedStatement.executeQuery();
        preparedStatement.addBatch();
        return resultSet;
    }

    public void updateAnalyses(String idAnalyses, String nameAnalyses1, String analyses1, String nameAnalyses2, String analyses2) throws SQLException {
        updateIn(idAnalyses,nameAnalyses1,analyses1);
        updateIn(idAnalyses,nameAnalyses2,analyses2);
    }

    private void updateIn(String idAnalyses, String nameAnalyses, String analyses) throws SQLException {
        if(!analyses.equals("not need")){
            preparedStatement = sqlServer.connection.prepareStatement("UPDATE visits SET "+nameAnalyses+" = ? WHERE id = ?");
            preparedStatement.setString(1,analyses);
            preparedStatement.setString(2,idAnalyses);
            preparedStatement.executeUpdate();
            preparedStatement.addBatch();
        }
    }


}
