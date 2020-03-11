package doctorPC.messageWorkWith;

import doctorPC.iterfaceDocktor.Controller;
import doctorPC.networkCommunication.IService;
import messageCommons.CommandSecond;
import messageCommons.Message;
import messageCommons.variosOfMessage.AuthMessage;
import messageCommons.variosOfMessage.Client;
import messageCommons.variosOfMessage.Visit;

public class SendMessage {
    private IService messageService;

    public SendMessage(IService messageService) {
        this.messageService = messageService;
    }

    public void checkLogin(String login, String password) {
        AuthMessage authMessage = new AuthMessage();
        authMessage.login = login;
        authMessage.password = password;
        Message auth = Message.creatAuth(authMessage);

        messageService.sendMessage(auth.toJson());
    }

    public void addNewUser(String role, String login, String password, String firstName, String secondName, String profession) {
        AuthMessage authMessage = new AuthMessage();
        authMessage.login = login;
        authMessage.password = password;
        authMessage.firstName = firstName;
        authMessage.secondName = secondName;
        authMessage.role = role;
        authMessage.profession = profession;

        Message message = Message.creatNewUser(authMessage);
        messageService.sendMessage(message.toJson());
    }


    public void lookingForClient(CommandSecond commandSecond,String firstName, String secondName, String cityOfBirthday, String dataOfBirthday) {
        Client client = new Client();
        client.firstName = firstName;
        client.secondName = secondName;
        client.cityOfBirthday = cityOfBirthday;
        client.dateOfBirthday = dataOfBirthday;

        Message message = new Message();
        if(commandSecond == CommandSecond.LOOKING_FOR_CLIENT){
            message = Message.creatLookingClient(client);
        } else if(commandSecond == CommandSecond.ADD_NEW_CLIENT){
           message = Message.creatAddClient(client);
        }
        messageService.sendMessage(message.toJson());
    }

    public void sendAnalysis(String firstName, String secondName, String diagnose, String dateVisit,
                             String cityBirthday, String dateBirthday,
                             String bloodAnalysis, String urinAnalysis, String frcalAnalysis,
                             String smearAnalysis, String dNAAnalysis, String paterAnalysis) {
        Visit visit = new Visit();
        visit.firstName = firstName;
        visit.secondName = secondName;
        visit.diagnose = diagnose;
        visit.dateVisit = dateVisit;
        visit.cityBirthday = cityBirthday;
        visit.dateBirthday = dateBirthday;
        visit.bloodAnalysis = bloodAnalysis;
        visit.urinAnalysis = urinAnalysis;
        visit.frcalAnalysis = frcalAnalysis;
        visit.smearAnalysis = smearAnalysis;
        visit.dNAAnalysis = dNAAnalysis;
        visit.paterAnalysis = paterAnalysis;

        Message message = Message.creatVisit(visit);
        messageService.sendMessage(message.toJson());
    }
}
