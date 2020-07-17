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

    public Fmdb db = new Fmdb();

    // false = edit, true = new movie
    public Boolean movieCreationState = false;
    public Boolean userCreationState = false;

    // GUI methods
    public void openScreen(String path, int width, int height, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    public void renderUserDropdown() {
        for (int i = 0; i < db.getPeople().size(); i += 1) {
            userDropdown.getItems().add(db.getPeople().get(i));
        }

        userDropdown.getItems().add("-- New User --");
        userDropdown.getSelectionModel().select(0);
    }

    public void choseUserItem(int index) {
        userDropdown.getSelectionModel().select(index);

        if (
                userDropdown.getSelectionModel().getSelectedIndex() ==
                        userDropdown.getItems().size() - 1
        ) {
            userDropdownButton.setText("New");
            userCreationState = true;
        } else {
            userDropdownButton.setText("Edit");
            userCreationState = false;
        }
    }

    public void renderMovieDropdown() {
        for (int i = 0; i < db.getMovies().size(); i += 1) {
            movieDropdown.getItems().add(db.getMovies().get(i));
        }

        movieDropdown.getItems().add("-- New Movie --");
        movieDropdown.getSelectionModel().select(0);
    }

    public void choseMovieItem(int index) {
        movieDropdown.getSelectionModel().select(index);

        if (
                movieDropdown.getSelectionModel().getSelectedIndex() ==
                        movieDropdown.getItems().size() - 1
        ) {
            movieDropdownButton.setText("New");
            movieCreationState = true;
        } else {
            movieDropdownButton.setText("Edit");
            movieCreationState = false;
        }
    }

    // events
    public void initialize() {
        renderMovieDropdown();
        renderUserDropdown();
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
