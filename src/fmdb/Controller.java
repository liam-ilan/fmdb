package fmdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public ListView movieRatingList;
    public Button movieDropdownButton;
    public ChoiceBox movieDropdown;
    public Label imdbUrlField;

    public ListView userRatingList;
    public Button userDropdownButton;
    public ChoiceBox userDropdown;
    public Label ageField;

    public Button ratingButton;

    // GUI methods
    public void openScreen(String path, int width, int height, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    // User pane
    public void userRatingListClicked(MouseEvent mouseEvent) {
    }

    public void userDropdownButtonClicked(ActionEvent actionEvent) throws IOException {
        openScreen("create-user.fxml", 300, 150, "FMDB User");
    }

    // Movie pane
    public void movieRatingListClicked(MouseEvent mouseEvent) {
    }

    public void movieDropdownButtonClicked(ActionEvent actionEvent) throws IOException {
        openScreen("create-movie.fxml", 300, 150, "FMDB Movie");
    }

    // Rating Button
    public void ratingButtonClicked(ActionEvent actionEvent) throws IOException {
        openScreen("rate.fxml", 300, 150, "FMDB Rating");
    }
}
