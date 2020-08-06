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

    public void initialize() {

        // populate dropdown
        for (double i = 0; i < 10.5; i += 0.5) {
            scoreDropdown.getItems().add(i);
        }

        // note: selection is by index, not by value
        scoreDropdown.getSelectionModel().select(15);

        // person
        selectedPerson = db.getPeople().get(fmdbController.userDropdown.getSelectionModel().getSelectedIndex());

        // movie
        selectedMovie = db.getMovies().get(fmdbController.movieDropdown.getSelectionModel().getSelectedIndex());

        // fill name and movie
        name.setText(selectedPerson.getName());
        movie.setText(selectedMovie.getName());
    }

    public void submitRating(ActionEvent actionEvent) throws IOException {
        boolean personRatedMovie = false;
        int i;

        for (i = 0; i < selectedPerson.getRatings().size(); i++) {
            personRatedMovie = selectedPerson.getRatings().get(i).getMovie() == selectedMovie;
            if (personRatedMovie) break;
        }

        if (!personRatedMovie) {
            new Rating(selectedPerson, (Double) scoreDropdown.getSelectionModel().getSelectedItem(), selectedMovie);
        } else {
            selectedPerson.getRatings().get(i).setScore((Double) scoreDropdown.getSelectionModel().getSelectedItem());
        }

        fmdbController.renderRatingLists();

        // write to file
        db.write();

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
