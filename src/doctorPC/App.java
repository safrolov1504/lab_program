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
        ChangeStage.setStage(primaryStage, "resources/loginInterface.fxml","Welcome PC");

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("resources/serviceInterface.fxml"));
//
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Doctor PC");
//        primaryStage.setScene(scene);
//
//        Controller primaryController = loader.getController();
//        primaryStage.setOnHidden(e -> primaryController.shutdown());
//
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
