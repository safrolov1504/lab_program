package server.workWithSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseSQLServer implements SQLServer {
    private static Connection connection;
    private static final String URL_SQL = "jdbc:sqlite:/Users/safrolov/Documents/university/java_program/lab_program/src/server/resources/bd_hospital.db";

    @Override
    public void start() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(URL_SQL);
    }

    @Override
    public void stop() throws SQLException {
        connection.close();
    }
}
