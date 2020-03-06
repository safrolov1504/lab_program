package messageCommons.variosOfMessage;

public class Client {
    public String firstName;
    public String secondName;
    public String cityOfBirthday;
    public String dateOfBirthday;
    public String message;
    public String dateVisit;

    public Client() {
    }

    public Client(String firstName, String secondName, String cityOfBirthday, String dateOfBirthday, String dateVisit) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.cityOfBirthday = cityOfBirthday;
        this.dateOfBirthday = dateOfBirthday;
        this.dateVisit = dateVisit;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setCityOfBirthday(String cityOfBirthday) {
        this.cityOfBirthday = cityOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateVisit(String dateVisit) {
        this.dateVisit = dateVisit;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getCityOfBirthday() {
        return cityOfBirthday;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public String getMessage() {
        return message;
    }

    public String getDateVisit() {
        return dateVisit;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", cityOfBirthday='" + cityOfBirthday + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
