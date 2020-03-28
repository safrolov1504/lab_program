package messageCommons.variosOfMessage;

public class Lab {
    public String idAnalyses;
    public String idClient;
    public String dateVisit;
    public String analyses1;
    public String nameAnalyses1;
    public String analyses2;
    public String nameAnalyses2;
    public String message;

    public Lab(String idAnalyses, String idClient, String dateVisit, String analyses1, String analyses2) {
        this.idAnalyses = idAnalyses;
        this.idClient = idClient;
        this.dateVisit = dateVisit;
        this.analyses1 = analyses1;
        this.analyses2 = analyses2;
    }

    public Lab() {
    }

    public String getIdAnalyses() {
        return idAnalyses;
    }

    public void setIdAnalyses(String idAnalyses) {
        this.idAnalyses = idAnalyses;
    }

    public String getNameAnalyses1() {
        return nameAnalyses1;
    }

    public void setNameAnalyses1(String nameAnalyses1) {
        this.nameAnalyses1 = nameAnalyses1;
    }

    public String getNameAnalyses2() {
        return nameAnalyses2;
    }

    public void setNameAnalyses2(String nameAnalyses2) {
        this.nameAnalyses2 = nameAnalyses2;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(String dateVisit) {
        this.dateVisit = dateVisit;
    }

    public String getAnalyses1() {
        return analyses1;
    }

    public void setAnalyses1(String analyses1) {
        this.analyses1 = analyses1;
    }

    public String getAnalyses2() {
        return analyses2;
    }

    public void setAnalyses2(String analyses2) {
        this.analyses2 = analyses2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "idAnalyses='" + idAnalyses + '\'' +
                ", idClient='" + idClient + '\'' +
                ", dateVisit='" + dateVisit + '\'' +
                ", analyses1='" + analyses1 + '\'' +
                ", nameAnalyses1='" + nameAnalyses1 + '\'' +
                ", analyses2='" + analyses2 + '\'' +
                ", nameAnalyses2='" + nameAnalyses2 + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
