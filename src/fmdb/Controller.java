package fmdb;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// get db
import static fmdb.Main.db;

public class Controller {
    public ListView movieRatingList;
    public Button movieDropdownButton;
    public ChoiceBox movieDropdown;

    public ListView userRatingList;
    public Button userDropdownButton;
    public ChoiceBox userDropdown;
    public Label userAverageRatingField;
    public ListView userTopList;

    public Button ratingButton;

    // false = edit, true = new movie
    public Boolean movieCreationState = false;
    public Boolean userCreationState = false;

    public Label movieAverageRatingField;
    public Label movieYearField;
    public Label movieGenreField;
    public Label movieMpcrField;
    public Label movieImdbUrlField;

    // GUI methods

    // openScreen opens a screen from an fxml file with a given width, height, and title
    public void openScreen(String path, int width, int height, String title) throws IOException {
        Stage stage = new Stage();

        // load
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    // open screen to create movie
    public void openMovieScreen() throws IOException {
        openScreen("movie.fxml", 275, 250, "FMDB Movie");
    }

    // open screen to create user
    public void openUserScreen() throws IOException {
        openScreen("user.fxml", 275, 100, "FMDB User");
    }

    // user methods

    // renders the user dropdown properly (adds -- New User --, etc.)
    public void renderUserDropdown() {

        // old selected index
        int oldIndex = userDropdown.getSelectionModel().getSelectedIndex();

        // clear
        userDropdown.getItems().clear();

        // add items to dropdown
        for (int i = 0; i < db.getPeople().size(); i++) {
            userDropdown.getItems().add(db.getPeople().get(i));
        }

        // add new user item
        userDropdown.getItems().add("-- New User --");

        // select correct item
        choseUserItem(oldIndex < 0 ? 0 : oldIndex);
    }

    // renders user data (average rating, top 5)
    public void renderUserData() {
        userTopList.getItems().clear();
        userAverageRatingField.setText("");

        // make sure index is in bounds
        int selectedIndex = userDropdown.getSelectionModel().getSelectedIndex();
        if (
                userDropdown.getItems().size() < 2 ||
                        selectedIndex == userDropdown.getItems().size() - 1
        ) return;

        // select user and add top rated items to list
        Person selectedUser = db.getPeople().get(selectedIndex);
        userTopList.getItems().addAll(selectedUser.getTopRated());

        // add average rating to field
        userAverageRatingField.setText(String.valueOf(
                (double) Math.round(selectedUser.getAverageRating() * 10) / 10
        ));
    }

    // selects an item from the dropdown
    public void choseUserItem(int index) {

        // select
        userDropdown.getSelectionModel().select(index);

        // get index
        int movieIndex = movieDropdown.getSelectionModel().getSelectedIndex();

        // deal with enable/disable rating
        if (index == userDropdown.getItems().size() - 1) ratingButton.setDisable(true);
        else if (movieIndex != movieDropdown.getItems().size() - 1) ratingButton.setDisable(false);

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

    // movie methods

    // renders movie dropdown
    public void renderMovieDropdown() {

        // get old index
        int oldIndex = movieDropdown.getSelectionModel().getSelectedIndex();
        movieDropdown.getItems().clear();

        // add items
        for (int i = 0; i < db.getMovies().size(); i++) {
            movieDropdown.getItems().add(db.getMovies().get(i));
        }

        // add new movie item, and select old index
        movieDropdown.getItems().add("-- New Movie --");
        choseMovieItem(oldIndex < 0 ? 0 : oldIndex);
    }


    // renders movie data
    public void renderMovieData() {
        movieGenreField.setText("");
        movieYearField.setText("");
        movieMpcrField.setText("");
        movieImdbUrlField.setText("");
        movieAverageRatingField.setText("");

        int selectedIndex = movieDropdown.getSelectionModel().getSelectedIndex();

        if (
                movieDropdown.getItems().size() > 1 &&
                        selectedIndex != movieDropdown.getItems().size() - 1
        ) {

            Movie selectedMovie = db.getMovies().get(selectedIndex);

            movieGenreField.setText(selectedMovie.getGenre());
            movieYearField.setText(String.valueOf(selectedMovie.getYear()));
            movieMpcrField.setText(selectedMovie.getMpcr());
            movieImdbUrlField.setText(selectedMovie.getImdbUrl());


            movieAverageRatingField.setText(String.valueOf(
                    (double) Math.round(selectedMovie.getAverageRating() * 10) / 10)
            );
        }
    }

    public void choseMovieItem(int index) {

        // select item
        movieDropdown.getSelectionModel().select(index);

        // get user index
        int userIndex = userDropdown.getSelectionModel().getSelectedIndex();

        // deal with disable/enable rating
        if (index == movieDropdown.getItems().size() - 1) ratingButton.setDisable(true);
        else if (userIndex != userDropdown.getItems().size() - 1) ratingButton.setDisable(false);

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

    // ratings
    public void renderRatingLists() {
        renderUserData();
        renderMovieData();

        // user rating list
        // clear
        userRatingList.getItems().clear();

        if (userDropdown.getSelectionModel().getSelectedIndex() != userDropdown.getItems().size() - 1) {

            // if not --new user-- item
            if (db.getPeople().size() != userRatingList.getItems().size()) {

                // get ratings
                int userIndex = userDropdown.getSelectionModel().getSelectedIndex();
                Person selectedPerson = db.getPeople().get(userIndex == -1 ? 0 : userIndex);
                ArrayList<Rating> personRatings = selectedPerson.getRatings();

                // add ratings to list
                for (int i = 0; i < personRatings.size(); i++) {
                    userRatingList.getItems().add(personRatings.get(i));
                }
            }
        }

        // movie rating list
        // clear
        movieRatingList.getItems().clear();

        if (movieDropdown.getSelectionModel().getSelectedIndex() != movieDropdown.getItems().size() - 1) {

            // if not --new movie-- item
            if (db.getMovies().size() != movieRatingList.getItems().size()) {

                // get ratings
                int movieIndex = movieDropdown.getSelectionModel().getSelectedIndex();
                Movie selectedMovie = db.getMovies().get(movieIndex == -1 ? 0 : movieIndex);
                ArrayList<Rating> movieRatings = selectedMovie.getRatings();

                // add ratings to list
                for (int i = 0; i < movieRatings.size(); i++) {
                    movieRatingList.getItems().add(movieRatings.get(i));
                }
            }
        }
    }

    // events
    public void initialize() {

        // dropdown onchange listeners
        movieDropdown.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldVal, Object newVal) {
                choseMovieItem(movieDropdown.getSelectionModel().getSelectedIndex());
                renderRatingLists();
            }
        });

        userDropdown.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldVal, Object newVal) {
                choseUserItem(userDropdown.getSelectionModel().getSelectedIndex());
                renderRatingLists();
            }
        });

        // initial render
        renderMovieDropdown();
        renderUserDropdown();
        renderRatingLists();
    }

    // User pane
    public void userDropdownButtonClicked(ActionEvent actionEvent) throws IOException {
       openUserScreen();
    }

    // Movie pane
    public void movieDropdownButtonClicked(ActionEvent actionEvent) throws IOException {
        openMovieScreen();
    }

    // Rating Button
    public void ratingButtonClicked(ActionEvent actionEvent) throws IOException {
        openScreen("rate.fxml", 300, 150, "FMDB Rating");
    }
}
