package doctorPC;

import doctorPC.iterfaceDocktor.Controller;
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

        Controller primaryController = loader.getController();
        primaryStage.setOnHidden(e -> primaryController.shutdown());

        primaryStage.show();
    }

    public static void  changeStageDo(Stage primaryStage,String resources, String nameWindow){
        // do what you have to do
        primaryStage.close();
        Stage stage = new Stage();
        try {
            setStage(stage, resources, nameWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void  changeStageDoWithoutClose(Stage primaryStage,String resources, String nameWindow){
        // do what you have to do
        Stage stage = new Stage();
        try {
            setStage(stage, resources, nameWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
