package fmdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static public Fmdb db = new Fmdb();
    static public Controller fmdbController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fmdb.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Family Movie Database");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        fmdbController = loader.getController();
    }

    public static void main(String[] args) { launch(args); }
}
