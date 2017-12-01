
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static java.awt.SystemColor.window;

public class LoginController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setPromptText("User");
        password.setPromptText("password");
        //loginButton.setOnAction(event -> loginValidation());

    }


    private void handleInvalidUser() {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Invalid Username", ButtonType.OK);
        alertBox.setContentText("Username Is invalid. \n Please verify username and try again.");
        alertBox.showAndWait();
        if (alertBox.getResult() == ButtonType.OK) {
            alertBox.close();
        }
    }

    private void handleInvalidPassword() {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION, "Invalid Password", ButtonType.OK, ButtonType.CANCEL);
        alertBox.setContentText("You have entered an incorrect password for this user account.\n Please verify username and password and try again.");
        alertBox.showAndWait();
        if (alertBox.getResult() == ButtonType.OK) {
            alertBox.close();
        }
    }
}
