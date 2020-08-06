package fmdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // read file and overwrite db
        // if it throws error, do nothing
        // (keeps db as an empty fmdb, as initialized)
        try {
            db = Fmdb.read();
        } catch (Exception e) {}

        launch(args);
    }
}
