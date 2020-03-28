package server.workWithSQL;

public class User {
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String role;
    private String profession;
    private String lab_analyses1;
    private String lab_analyses2;

    public User(String login, String password, String firstName, String secondName, String role, String profession, String lab_analyses1, String lab_analyses2) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
        this.profession = profession;
        this.lab_analyses1 = lab_analyses1;
        this.lab_analyses2 = lab_analyses2;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getRole() {
        return role;
    }

    public String getProfession() {
        return profession;
    }

    public String getLab_analyses1() {
        return lab_analyses1;
    }

    public String getLab_analyses2() {
        return lab_analyses2;
    }
}
