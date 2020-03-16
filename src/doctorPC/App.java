package doctorPC;

import doctorPC.iterfaceDocktor.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
//        ChangeStage.changeStageDo(primaryStage, "resources/loginInterface.fxml","Welcome PC");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("resources/loginInterface.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Welcome PC");
        primaryStage.setScene(scene);

        Controller primaryController = loader.getController();
        //primaryController.initialize();
        primaryStage.setOnHidden(e -> primaryController.shutdown());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
