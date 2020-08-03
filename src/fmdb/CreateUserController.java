package fmdb;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static fmdb.Main.db;
import static fmdb.Main.fmdbController;

public class CreateUserController {
    public TextField ageInput;
    public TextField nameInput;
    public Boolean userCreationState;
    public Button submitButton;

    // remains undefined if creating new movie
    Person user;

    public void initialize() {
        userCreationState = fmdbController.userCreationState;

        if (!userCreationState) {
            user = db.getPeople().get(fmdbController.userDropdown.getSelectionModel().getSelectedIndex());
            nameInput.setText(user.getName());
        };
    }
    public void createUser() {
        db.addPerson(new Person(nameInput.getText()));
        fmdbController.renderUserDropdown();
    }

    public void editUser() {
        user.setName(nameInput.getText());
        fmdbController.renderUserDropdown();
    }

    public void submitButtonClicked(ActionEvent actionEvent) {
        if (userCreationState) createUser();
        else editUser();

        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
