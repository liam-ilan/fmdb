package fmdb;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static fmdb.Main.db;
import static fmdb.Main.fmdbController;

public class RateController {
    public Label name;
    public Label movie;
    public ChoiceBox scoreDropdown;
    public Button submitButton;

    public Person selectedPerson;
    public Movie selectedMovie;

    boolean personRatedMovie = false;

    public int ratingIndex;

    public void initialize() {

        // populate dropdown
        for (double i = 0; i < 10.5; i += 0.5) {
            scoreDropdown.getItems().add(i);
        }

        // person
        selectedPerson = db.getPeople().get(fmdbController.userDropdown.getSelectionModel().getSelectedIndex());

        // movie
        selectedMovie = db.getMovies().get(fmdbController.movieDropdown.getSelectionModel().getSelectedIndex());

        // check if movie has already been rated by this person, in which case, we are editing
        // also, set ratingIndex to the index of the edited rating in person, if editing
        for (ratingIndex = 0; ratingIndex < selectedPerson.getRatings().size(); ratingIndex++) {
            personRatedMovie = selectedPerson.getRatings().get(ratingIndex).getMovie() == selectedMovie;
            if (personRatedMovie) break;
        }

        // note: selection is by index, not by value
        if (personRatedMovie) {
            scoreDropdown.getSelectionModel().select(
                    (int) selectedPerson.getRatings().get(ratingIndex).getScore() * 2 + 1
            );
        } else {
            // default, 7.5
            scoreDropdown.getSelectionModel().select(15);
        }

        // fill name and movie
        name.setText(selectedPerson.getName());
        movie.setText(selectedMovie.getName());
    }

    public void submitRating(ActionEvent actionEvent) throws IOException {

        // if new rating
        if (!personRatedMovie) {
            new Rating(selectedPerson, (Double) scoreDropdown.getSelectionModel().getSelectedItem(), selectedMovie);
        } else {
            // if editing
            selectedPerson.getRatings().get(ratingIndex).setScore((Double) scoreDropdown.getSelectionModel().getSelectedItem());
        }

        // render
        fmdbController.renderRatingLists();

        // write to file
        db.write();

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
