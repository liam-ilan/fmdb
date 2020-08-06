package fmdb;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static fmdb.Main.db;
import static fmdb.Main.fmdbController;

public class CreateUserController {
    public TextField nameInput;
    public Boolean userCreationState;
    public Button submitButton;

    Person user;

    public void initialize() {

        // get creation state from main controller
        userCreationState = fmdbController.userCreationState;

        // if editing
        if (!userCreationState) {

            // get user
            user = db.getPeople().get(fmdbController.userDropdown.getSelectionModel().getSelectedIndex());

            // update name input
            nameInput.setText(user.getName());
        } else {

            // make new user if not editing
            user = new Person("");
        }
    }


    public void submitButtonClicked(ActionEvent actionEvent) throws IOException {

        // update user data
        user.setName(nameInput.getText());

        // add to db if creating new user
        if (userCreationState) db.addPerson(user);

        // render
        fmdbController.renderUserDropdown();

        // write to file
        db.write();

        // close
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
