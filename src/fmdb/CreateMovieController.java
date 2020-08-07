package fmdb;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static fmdb.Main.db;
import static fmdb.Main.fmdbController;

public class CreateMovieController {
    public TextField imdbInput;
    public TextField nameInput;
    public TextField yearInput;
    public TextField genreInput;
    public TextField mpcrInput;

    public Boolean movieCreationState;
    public Button submitButton;

    public Movie movie;

    public void initialize() {

        // get movieCreationState from main controller
        movieCreationState = fmdbController.movieCreationState;

        // if editing
        if (!movieCreationState) {

            // get movie
            movie = db.getMovies().get(fmdbController.movieDropdown.getSelectionModel().getSelectedIndex());

            // set inputs
            nameInput.setText(movie.getName());
            imdbInput.setText(movie.getImdbUrl());
            yearInput.setText(movie.getYear());
            genreInput.setText(movie.getGenre());
            mpcrInput.setText(movie.getMpcr());

        // if creating
        } else {
            movie = new Movie("");
        }
    }

    public void submitButtonClicked(ActionEvent actionEvent) throws IOException {
        // save values to movie

        movie.update(
                nameInput.getText(),
                imdbInput.getText(),
                mpcrInput.getText(),
                yearInput.getText(),
                genreInput.getText()
        );

        // add movie to db if creating new movie
        if (movieCreationState) db.addMovie(movie);

        // render
        fmdbController.renderMovieDropdown();

        // write to file
        db.write();

        // close
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
