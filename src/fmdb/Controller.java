package fmdb;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller {
    public ListView movieRatingList;
    public Button movieDropdownButton;
    public ChoiceBox movieDropdown;
    public Label imdbUrlField;

    public ListView userRatingList;
    public Button userDropdownButton;
    public ChoiceBox userDropdown;

    public Button ratingButton;

    // Rating pane
    public Label name;
    public Label movie;
    public ChoiceBox scoreDropdown;
    public Button submitButton;

    // User pane
    public void userRatingListClicked(MouseEvent mouseEvent) {
    }

    public void userDropdownButtonClicked(ActionEvent actionEvent) {
    }

    // Movie pane
    public void movieRatingListClicked(MouseEvent mouseEvent) {
    }

    public void movieDropdownButtonClicked(ActionEvent actionEvent) {
    }

    // Rating Button
    public void ratingButtonClicked(ActionEvent actionEvent) {
    }

    // rating pane
    public void submitRating(ActionEvent actionEvent) {
    }
}
