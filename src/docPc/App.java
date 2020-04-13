package docPc;


import docPc.controllers.ChangeStage;
import docPc.controllers.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static Controller controller;

    public static Controller getController() {
        return controller;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        controller = new Controller();

        ChangeStage.changeStageDo(primaryStage, "/docPc/resources/loginInterface.fxml","Welcome PC",
                false,true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
