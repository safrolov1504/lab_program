package docPc.controllers;

import docPc.controllers.controllerInterface.ControllerInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeStage {

    private static void setStage(Stage primaryStage, String resources, String nameWindow) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ChangeStage.class.getResource(resources));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle(nameWindow);
        primaryStage.setScene(scene);

//        ControllerInterface primaryStage = loader.getController();
//        primaryStage.setOnHidden(e -> primaryStage.shutdown());

        primaryStage.show();
    }

    public static void  changeStageDo(Stage primaryStage,String resources, String nameWindow){
        primaryStage.close();
        Stage stage = new Stage();
        try {
            setStage(stage, resources, nameWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void  changeStageDoWithoutClose(Stage primaryStage,String resources, String nameWindow){
        Stage stage = new Stage();
        try {
            setStage(stage, resources, nameWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
