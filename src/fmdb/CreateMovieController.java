package fmdb;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static fmdb.Main.db;
import static fmdb.Main.fmdbController;

public class CreateMovieController {
    public TextField imdbInput;
    public TextField nameInput;
    public Boolean movieCreationState;
    public Button submitButton;

    // remains undefined if creating new movie
    public Movie movie;

    public void initialize() {
        movieCreationState = fmdbController.movieCreationState;

        if (!movieCreationState) {
            movie = db.getMovies().get(fmdbController.movieDropdown.getSelectionModel().getSelectedIndex());
            nameInput.setText(movie.getName());
        };
    }
    public void createMovie() {
        db.addMovie(new Movie(nameInput.getText()));
        fmdbController.renderMovieDropdown();
    }

    public void editMovie() {
        movie.setName(nameInput.getText());
        fmdbController.renderMovieDropdown();
    }

    public void submitButtonClicked(ActionEvent actionEvent) {
        if (movieCreationState) createMovie();
        else editMovie();

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
