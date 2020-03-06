package doctorPC.messageWorkWith.typeMessage;

import doctorPC.iterfaceDocktor.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import messageCommons.Message;
import messageCommons.variosOfMessage.Client;

public class WorkingInterfaceMessage implements DifferentTypeMessage{
    private Controller controller;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    //private ObservableList<Client> clientData;

    private String firstName;
    private String secondName;
    private String cityOfBirthday;
    private String dateOfBirthday;
    private String dateVisit;

    public WorkingInterfaceMessage(Controller controller) {
        //clientData = FXCollections.observableArrayList();
        this.controller = controller;
    }

    @Override
    public void getIntormation(Message message) {
        switch (message.commandSecond){
            case ADD_CLIENT_OK:
                alert.setHeaderText("Adding new client is ok");
                alert.setContentText("New user " + message.client.firstName+" "+message.client.secondName + " was added");
                controller.docWork_fieldCityBirth.clear();
                controller.docWork_fieldFirstName.clear();
                controller.docWork_fieldSecondName.clear();
                controller.docWork_dateBirthday.getEditor().clear();
                alert.showAndWait();
                break;

            case ADD_CLIENT_NOK:
                alert.setHeaderText("Adding new user is not ok");
                alert.setContentText(message.client.message);
                alert.showAndWait();
                break;

            case LOOKING_FOR_CLIENT_OK:
                this.firstName = message.client.firstName;
                this.secondName = message.client.secondName;
                this.cityOfBirthday = message.client.cityOfBirthday;
                this.dateOfBirthday = message.client.dateOfBirthday;
                this.dateVisit = "11";

                setColumns();

                Client client = new Client(firstName,secondName,cityOfBirthday, dateOfBirthday,dateVisit);
                controller.clientData.add(client);

                controller.docWork_table.setItems(controller.clientData);
                break;

            case LOOKING_FOR_CLIENT_NOK:
                alert.setHeaderText("Problem with search");
                alert.setContentText(message.client.message);
                alert.showAndWait();
                break;
        }
    }

    private void setColumns(){
        controller.docWork_table_firstName.setCellValueFactory(new PropertyValueFactory<Client,String>("firstName"));
        controller.docWork_table_secondName.setCellValueFactory(new PropertyValueFactory<Client,String>("secondName"));
        controller.docWork_table_city.setCellValueFactory(new PropertyValueFactory<Client,String>("cityOfBirthday"));
        controller.docWork_table_dateBirth.setCellValueFactory(new PropertyValueFactory<Client,String>("dateOfBirthday"));
        controller.docWork_table_dateVisit.setCellValueFactory(new PropertyValueFactory<Client,String>("dateVisit"));
    }
}
