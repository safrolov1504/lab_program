package docPc.controllers;

import docPc.controllers.controllerInterface.ControllerDoc;
import docPc.controllers.controllerInterface.ControllerInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeStage {

//    private static void setStage(Stage primaryStage, String resources, String nameWindow) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(ChangeStage.class.getResource(resources));
//
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setTitle(nameWindow);
//        primaryStage.setScene(scene);
//
//        ControllerInterface primaryController = loader.getController();
//        primaryStage.setOnCloseRequest(e -> primaryController.shutdown());
//
//        primaryStage.show();
//    }
//
//    public static void  changeStageDo(Stage primaryStage,String resources, String nameWindow){
//        primaryStage.close();
//        Stage stage = new Stage();
//        try {
//            setStage(stage, resources, nameWindow);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void  changeStageDoWithoutClose(Stage primaryStage,String resources, String nameWindow){
//        Stage stage = new Stage();
//        try {
//            setStage(stage, resources, nameWindow);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void  changeStageDo(Stage primaryStage,String resources, String nameWindow,
                                      boolean closeTheWindowAfterChange, boolean closeTheWindowByRequest) {
        //if the previous window need to be closed after changing
        if(closeTheWindowAfterChange){
            primaryStage.close();
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ChangeStage.class.getResource(resources));

        try {
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle(nameWindow);
            stage.setScene(scene);

            //if the window can close the whole app by close request
            if(closeTheWindowByRequest) {
                ControllerInterface primaryController = loader.getController();
                stage.setOnCloseRequest(e -> primaryController.shutdown());
            }
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
